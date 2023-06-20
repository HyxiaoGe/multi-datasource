package org.example.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.second-datasource")
    public DataSource jdbcTemplateDatasource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("jdbcTemplateDatasource") DataSource jdbcTemplateDataSource){
        return new JdbcTemplate(jdbcTemplateDataSource);
    }

}
