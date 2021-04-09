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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "tecasheEntityManagerFactory", basePackages = {
        "com.example.springback.repository.tecashe" }, transactionManagerRef = "tecasheTransactionManager")
public class ConfigTeCasheDB {

    @Primary
    @Bean(name = "tecasheDatasource")
    @ConfigurationProperties(prefix = "spring.tecashe.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "tecasheEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
            @Qualifier("tecasheDatasource") DataSource dataSource) {
        Map<String, Object> properties = new HashMap<>();
        // properties.put("hibernate.ddl.auto", "none");
        properties.put("hibernate.hbm2ddl.auto", "none");
        // properties.put("hibernate.dialect",
        // "org.hibernate.dialect.SQLServerDialect");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        return builder.dataSource(dataSource).properties(properties).packages("com.example.springback.entity.tecashe")
                .persistenceUnit("Tecashe").build();
    }

    @Primary
    @Bean(name = "tecasheTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("tecasheEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
