package br.com.lasbr.bank.domain.account;

import br.com.lasbr.bank.domain.costumer.Cliente;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContaDAO {

        private Connection conn;

        ContaDAO(Connection connection) {
            this.conn = connection;
        }

        public void salvar(DadosAberturaConta dadosDaConta) {
            var cliente = new Cliente(dadosDaConta.dadosCliente());
            var conta = new Conta(dadosDaConta.numero(), cliente);

            String sql = "INSERT INTO conta (numero, saldo, cliente_nome, cliente_cpf, cliente_email) " +
                    " VALUES (?, ?, ?, ?, ?) ";

            try {
                PreparedStatement statement = conn.prepareStatement(sql);

                statement.setInt(1,conta.getNumero());
                statement.setBigDecimal(2, BigDecimal.ZERO);
                statement.setString(3, dadosDaConta.dadosCliente().nome());
                statement.setString(4,dadosDaConta.dadosCliente().cpf());
                statement.setString(5,dadosDaConta.dadosCliente().email());
                statement.execute();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
