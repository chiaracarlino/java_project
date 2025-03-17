package com.epf.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DBConfig {

    // Déclaration du Bean DataSource
    @Bean
    public DataSource initDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/pvz"); // Remplace avec ton URL
        dataSource.setUsername("root");  // Remplace avec ton utilisateur
        dataSource.setPassword("password"); // Remplace avec ton mot de passe
        return dataSource;
    }

    // Déclaration du Bean JdbcTemplate
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
