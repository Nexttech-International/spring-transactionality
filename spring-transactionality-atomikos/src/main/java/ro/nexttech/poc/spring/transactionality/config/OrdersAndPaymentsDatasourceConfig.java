package ro.nexttech.poc.spring.transactionality.config;

import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "ro.nexttech.poc.spring.transactionality.repository.ordersandpayments",
        entityManagerFactoryRef = "ordersAndPaymentsEntityManagerFactory"
)
public class OrdersAndPaymentsDatasourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.ordersandpayments")
    public DataSource ordersAndPaymentsDataSource() {
        AtomikosNonXADataSourceBean atomikosNonXADataSourceBean = new AtomikosNonXADataSourceBean();
        atomikosNonXADataSourceBean.setUniqueResourceName("ordersandpayments");
        return atomikosNonXADataSourceBean;
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
}
