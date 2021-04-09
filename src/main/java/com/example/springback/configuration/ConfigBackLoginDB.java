package com.example.springback.configuration;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "backloginEntityManagerFactory", basePackages = {
        "com.example.springback.repository.backlogin" }, transactionManagerRef = "backloginTransactionManager")
public class ConfigBackLoginDB {

    @Bean(name = "backloginDatasource")
    @ConfigurationProperties(prefix = "spring.backlogin.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "backloginEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
            @Qualifier("backloginDatasource") DataSource dataSource) {
        Map<String, Object> properties = new HashMap<>();
        // properties.put("hibernate.ddl.auto", "none");
        properties.put("hibernate.hbm2ddl.auto", "none");
        // properties.put("hibernate.dialect",
        // "org.hibernate.dialect.SQLServerDialect");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        return builder.dataSource(dataSource).properties(properties).packages("com.example.springback.entity.backlogin")
                .persistenceUnit("Backlogin").build();
    }

    @Bean(name = "backloginTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("backloginEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
