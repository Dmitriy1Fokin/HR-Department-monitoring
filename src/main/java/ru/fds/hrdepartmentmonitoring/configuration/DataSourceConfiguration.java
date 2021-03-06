package ru.fds.hrdepartmentmonitoring.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    private static final String DB_USER_NAME = "postgres";
    private static final String DB_USER_PASSWORD = "postgres";


    @Bean
    @Primary
    @Qualifier
    public DataSource jobRepoDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:batch_db");
        dataSourceBuilder.username("SA");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }

    @Bean
    @Qualifier
    public DataSource businessDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/cat_db");
        dataSourceBuilder.username(DB_USER_NAME);
        dataSourceBuilder.password(DB_USER_PASSWORD);
        return dataSourceBuilder.build();
    }

    @Bean
    @Qualifier
    public DataSource carDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/cars_db");
        dataSourceBuilder.username(DB_USER_NAME);
        dataSourceBuilder.password(DB_USER_PASSWORD);
        return dataSourceBuilder.build();
    }
}
