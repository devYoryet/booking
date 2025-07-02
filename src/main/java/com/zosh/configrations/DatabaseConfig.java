package com.zosh.configrations;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    @ConditionalOnProperty(name = "spring.jpa.database-platform", havingValue = "org.hibernate.dialect.OracleDialect")
    public OracleSequenceGenerator oracleSequenceGenerator(DataSource dataSource) {
        return new OracleSequenceGenerator(dataSource);
    }
}