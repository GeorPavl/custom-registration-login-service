package com.custom_login_example.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;



/**
 * a. Configure Spring Data JPA
 * @EnableJpaRepositories(basePackages={"${spring.data.jpa.repository.packages}"})
 *
 * This tells the app that we are using JPA repositories defined in the given package. The package name is read from the application.properties file.
 *
 * spring.data.jpa.repository.packages=com.custom_login_example.repository
 *
 * In this case, the package name is: com.custom_login_example.repository, so Spring Data JPA will scan for JPA repositories in this package. Spring Data JPA makes use of a entity manager factory bean and transaction manager.
 * By default it will use a bean named, "entityManagerFactory". We manually configure this bean in this class. Also, by default, Spring Data JPA will use a bean named "transactionManager". The "transactionManager" bean is autoconfigured by Spring Boot.
 * **/
@Configuration
@EnableJpaRepositories(basePackages={"${spring.data.jpa.repository.packages}"})
public class DemoDataSourceConfig {


    /**
     * b. Configure application DataSource
     * This code creates a datasource. This datasource is for our main application data. It will read data "employee" data from the database. This datasource is configured with the following:
     *
     *    @ConfigurationProperties(prefix="app.datasource")
     *
     * The @ConfigurationProperties will read properties from the config file (application.properties). It will read the properties from the file with the prefix: "app.datasource". So it will read the following:
     *
     * app.datasource.jdbc-url=jdbc:mysql://localhost:3306/login_example?useSSL=false&serverTimezone=UTC
     * app.datasource.username=root
     * app.datasource.password=1234
     * **/
    @Primary
    @Bean
    @ConfigurationProperties(prefix="app.datasource")
    public DataSource appDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * Configure EntityManagerFactory
     *
     * The entity manager factory tells Spring Data JPA which packages to scan for JPA entities. The @ConfigurationProperties will read properties from the config file (application.properties). It will read the properties from the file with the prefix: "spring.data.jpa.entity". So it will read the following:
     *
     * spring.data.jpa.entity.packages-to-scan=com.custom_login_example.entity
     * **/
    @Bean
    @ConfigurationProperties(prefix="spring.data.jpa.entity")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource appDataSource) {
        return builder
                .dataSource(appDataSource)
                .build();
    }

    /**
     * d. Configure Data Source for Security
     * Here we configure the datasource to access the security database. By default, Spring Security makes use of regular JDBC (no JPA). As a result, we only need a datasource so the configuration is a bit simpler.
     *
     * The @ConfigurationProperties will read properties from the config file (application.properties). It will read the properties from the file with the prefix: "security.datasource". So it will read the following:
     *
     * security.datasource.jdbc-url=jdbc:mysql://localhost:3306/login_example?useSSL=false&serverTimezone=UTC
     * security.datasource.username=root
     * security.datasource.password=1234
     * **/
    @Bean
    @ConfigurationProperties(prefix="security.datasource")
    public DataSource securityDataSource() {
        return DataSourceBuilder.create().build();
    }
}
