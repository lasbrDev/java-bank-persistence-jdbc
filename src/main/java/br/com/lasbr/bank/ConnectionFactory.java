package br.com.lasbr.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class ConnectionFactory {
        public Connection recuperarConexao() {
            try {
                return DriverManager
                        .getConnection("jdbc:mysql://localhost:3308/bank_db?user=root&password=lasBr01");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
