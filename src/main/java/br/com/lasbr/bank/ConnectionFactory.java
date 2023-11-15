package br.com.lasbr.bank;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class ConnectionFactory {
        public Connection recuperarConexao() {
            try {
                return dataSource().getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        private HikariDataSource dataSource() {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:mysql://localhost:3308/bank_db");
            config.setUsername("root");
            config.setPassword("lasBr01");
            config.setMaximumPoolSize(10);

            return new HikariDataSource(config);
        }
    }
