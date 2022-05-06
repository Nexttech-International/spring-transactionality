package ro.nexttech.poc.spring.transactionality.config;

import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.engine.transaction.jta.platform.internal.AtomikosJtaPlatform;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "ro.nexttech.poc.spring.transactionality.repository.notifications",
        entityManagerFactoryRef = "notificationsEntityManagerFactory"
)
public class NotificationsDatasourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.notifications")
    public DataSource notificationsDataSource() {
        AtomikosNonXADataSourceBean atomikosNonXADataSourceBean = new AtomikosNonXADataSourceBean();
        atomikosNonXADataSourceBean.setUniqueResourceName("notifications");
        return atomikosNonXADataSourceBean;
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
                .properties(Map.of(
                        AvailableSettings.JTA_PLATFORM, AtomikosJtaPlatform.class,
                        AvailableSettings.JPA_TRANSACTION_TYPE, "JTA"
                ))
                .build();
    }
}
