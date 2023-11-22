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
            var conta = new Conta(dadosDaConta.numero(), BigDecimal.ZERO, cliente);

            String sql = " INSERT INTO conta (numero, saldo, cliente_nome, cliente_cpf, cliente_email) " +
                    " VALUES (?, ?, ?, ?, ?) ";

            try {
                PreparedStatement statement = conn.prepareStatement(sql);

                statement.setInt(1,conta.getNumero());
                statement.setBigDecimal(2, BigDecimal.ZERO);
                statement.setString(3, dadosDaConta.dadosCliente().nome());
                statement.setString(4,dadosDaConta.dadosCliente().cpf());
                statement.setString(5,dadosDaConta.dadosCliente().email());
                statement.execute();
                statement.close();
                conn.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public Set<Conta> listar() {
            PreparedStatement statement;
            ResultSet resultSet;
            Set<Conta> contas = new HashSet<>();

            String sql = " SELECT * FROM conta ";


            try {
                statement = conn.prepareStatement(sql);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Integer numero = resultSet.getInt(1);
                    BigDecimal saldo = resultSet.getBigDecimal(2);
                    String nome = resultSet.getNString(3);
                    String cpf = resultSet.getNString(4);
                    String email = resultSet.getNString(5);
                    DadosCadastroCliente dados = new DadosCadastroCliente(nome, cpf, email);
                    Cliente cliente = new Cliente(dados);
                    contas.add(new Conta(numero, saldo, cliente));
                }
                statement.close();
                resultSet.close();
                conn.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return contas;
        }

        public Conta listarPorNumero(Integer numero) {
            String sql = " SELECT * FROM conta WHERE numero = ? ";

            PreparedStatement statement;
            ResultSet resultSet;
            Conta conta = null;
            try {
                statement = conn.prepareStatement(sql);
                statement.setInt(1, numero);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Integer numeroRecuperado = resultSet.getInt(1);
                    BigDecimal saldo = resultSet.getBigDecimal(2);
                    String nome = resultSet.getString(3);
                    String cpf = resultSet.getNString(4);;
                    String email = resultSet.getNString(5);

                    DadosCadastroCliente dados = new DadosCadastroCliente(nome, cpf, email);
                    Cliente cliente = new Cliente(dados);
                    conta = new Conta(numeroRecuperado, saldo, cliente);
                }
                resultSet.close();
                statement.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return conta;
        }

        public void alterar(Integer numero, BigDecimal valor) {
            PreparedStatement  statement;

            String sql = "UPDATE conta SET saldo = saldo + ? WHERE numero = ? ";



            try {
                statement = conn.prepareStatement(sql);
                statement.setBigDecimal(1, valor);
                statement.setInt(2, numero);
                statement.execute();
                statement.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
