package com.drf.Springbootpostgres.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class postgreswdatasoruce {

    @Bean
    @ConfigurationProperties("app.datasource")
    public HikariDataSource hikariDataSoruce() {
        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();

    }
}
