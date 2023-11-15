package br.com.lasbr.bank.domain.account;

import br.com.lasbr.bank.domain.costumer.Cliente;
import br.com.lasbr.bank.domain.costumer.DadosCadastroCliente;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

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

        public Set<Conta> listar() {
            Set<Conta> contas = new HashSet<>();

            String sql = "SELECT * FROM conta";


            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet resultSet = ps.executeQuery();

                while (resultSet.next()) {
                    Integer numero = resultSet.getInt(1);
                    BigDecimal saldo = resultSet.getBigDecimal(2);
                    String nome = resultSet.getNString(3);
                    String cpf = resultSet.getNString(4);
                    String email = resultSet.getNString(5);
                    DadosCadastroCliente dados = new DadosCadastroCliente(nome, cpf, email);
                    Cliente cliente = new Cliente(dados);
                    contas.add(new Conta(numero, cliente));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return contas;
        }
    }
