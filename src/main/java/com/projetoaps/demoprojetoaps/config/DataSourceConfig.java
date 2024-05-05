package com.projetoaps.demoprojetoaps.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Autowired
    private Dotenv dotenv;

    @Bean
    public DataSource dataSource() {
        String databaseUrl = dotenv.get("DATABASE_URL");
        String dbusername = dotenv.get("DBUSERNAME");
        String dbpassword = dotenv.get("DBPASSWORD");

        // DriverManagerDataSource, que é uma implementação de DataSource
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(dbusername);
        dataSource.setPassword(dbpassword);

        // retorna o DataSource configurado, que o Spring Boot usará para criar a conexão com o banco de dados.
        return dataSource;
    }
}
