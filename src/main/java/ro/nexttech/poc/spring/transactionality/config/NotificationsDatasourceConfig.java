package ro.nexttech.poc.spring.transactionality.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(
        basePackages = "ro.nexttech.poc.spring.transactionality.repository.notifications",
        entityManagerFactoryRef = "notificationsEntityManagerFactory",
        transactionManagerRef = "notificationsTransactionManager"
)
public class NotificationsDatasourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.notifications")
    public DataSourceProperties notificationsDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource notificationsDataSource() {
        return notificationsDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public DataSourceInitializer notificationsDataSourceInitializer() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("schema-notifications.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(notificationsDataSource());
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);

        return dataSourceInitializer;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean notificationsEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(notificationsDataSource())
                .packages("ro.nexttech.poc.spring.transactionality.entity.notifications")
                .persistenceUnit("notifications")
                .build();
    }

    @Bean
    public PlatformTransactionManager notificationsTransactionManager(@Qualifier("notificationsEntityManagerFactory") LocalContainerEntityManagerFactoryBean notificationsEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(notificationsEntityManagerFactory.getObject()));
    }
}
