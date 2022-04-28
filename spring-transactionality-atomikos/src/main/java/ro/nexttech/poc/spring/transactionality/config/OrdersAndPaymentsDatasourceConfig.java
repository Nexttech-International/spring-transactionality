package ro.nexttech.poc.spring.transactionality.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        basePackages = "ro.nexttech.poc.spring.transactionality.repository.ordersandpayments",
        entityManagerFactoryRef = "ordersAndPaymentsEntityManagerFactory",
        transactionManagerRef = "ordersAndPaymentsTransactionManager"
)
public class OrdersAndPaymentsDatasourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.ordersandpayments")
    public DataSourceProperties ordersAndPaymentsDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource ordersAndPaymentsDataSource() {
        return ordersAndPaymentsDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean
    public DataSourceInitializer ordersAndPaymentsDataSourceInitializer() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("schema-ordersandpayments.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(ordersAndPaymentsDataSource());
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);

        return dataSourceInitializer;
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean ordersAndPaymentsEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(ordersAndPaymentsDataSource())
                .packages("ro.nexttech.poc.spring.transactionality.entity.ordersandpayments")
                .persistenceUnit("ordersandpayments")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager ordersAndPaymentsTransactionManager(@Qualifier("ordersAndPaymentsEntityManagerFactory") LocalContainerEntityManagerFactoryBean ordersAndPaymentsEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(ordersAndPaymentsEntityManagerFactory.getObject()));
    }
}
