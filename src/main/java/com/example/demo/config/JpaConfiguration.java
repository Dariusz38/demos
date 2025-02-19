package com.example.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = {"com/example/demo/customer/repository", "com/example/demo/book/repository"})
/**
 * Enables Spring's annotation-driven transaction management capability. Requires JDBC DataSource which is configured in
 * dataSource() method and used in transactionManager bean. The bean creates PlatformTransactionManager
 */
@EnableTransactionManagement
public class JpaConfiguration {

    @Autowired
    private Environment environment;

    @Value("${datasource.maxPoolSize:10}")
    private int maxPoolSize;

    /**
     * Populate SpringBoot DataSourceProperties object directly from application.yml
     * based on prefix. Thanks to .yml, Hierarchical data is mapped out of the box with matching-name
     * properties of DataSourceProperties object.
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * Configure HikariCP pooled DataSource.
     */
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        final DataSourceProperties dataSourceProperties = dataSourceProperties();
        final HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder
                .create(dataSourceProperties.getClassLoader())
                .driverClassName(dataSourceProperties.getDriverClassName())
                .url(dataSourceProperties.getUrl())
                .username(dataSourceProperties.getUsername())
                .password(dataSourceProperties.getPassword())
                .type(HikariDataSource.class)
                .build();
        dataSource.setMaximumPoolSize(maxPoolSize);

        return dataSource;
    }

    /**
     * Entity Manager Factory setup.
     *
     * @return LocalContainerEntityManagerFactoryBean
     * @throws {@link NamingException}
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
        final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan(new String[]{"com/example/demo/customer/model", "com/example/demo/book/model"});
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setJpaProperties(jpaProperties());
        return factoryBean;
    }

    /**
     * Provider hibernate jpa adapter. Which is used in Entity manager factory bean.
     *
     * @return JPA vendor adapter as HibernateJpaVendorAdapter
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        final HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        return hibernateJpaVendorAdapter;
    }

    /**
     * Here you can specify any provider specific properties.
     * The properties are taken from application.yml. and user in entity manager factory.
     */
    private Properties jpaProperties() {
        final Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("datasource.hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("datasource.hibernate.hbm2ddl.method"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("datasource.hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("datasource.hibernate.format_sql"));
        if (StringUtils.isNotEmpty(environment.getRequiredProperty("datasource.defaultSchema"))) {
            properties.put("hibernate.default_schema", environment.getRequiredProperty("datasource.defaultSchema"));
        }
        return properties;
    }
}
