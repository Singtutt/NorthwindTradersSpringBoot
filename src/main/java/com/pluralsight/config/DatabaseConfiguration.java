package com.pluralsight.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {
    @Value("${spring.datasource.url}")
    private String connectURL;
    @Value("${spring.datasource.username}")
    private String USER;
    @Value("${spring.datasource.password}")
    private String PASS;

    @Bean
    public DataSource ds() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(connectURL);
        ds.setUsername(USER);
        ds.setPassword(PASS);
        return ds;
    }
}
