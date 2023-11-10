package br.com.lasbr.bank;


import br.com.lasbr.bank.domain.RegraDeNegocioException;
import br.com.lasbr.bank.domain.account.Conta;
import br.com.lasbr.bank.domain.account.ContaService;
import br.com.lasbr.bank.domain.account.DadosAberturaConta;
import br.com.lasbr.bank.domain.costumer.DadosCadastroCliente;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

    public class BankApplication {

        private  static Scanner sc = new Scanner(System.in).useDelimiter("\n");
        private static ContaService service = new ContaService();

        public static void main(String[] args) {

            var opcao = exibirMenu();
            while (opcao != 7) {
                try {
                    switch (opcao) {
                        case 1:
                            listarContas();
                            break;
                        case 2:
                            abrirConta();
                            break;
                        case 3:
                            encerrarConta();
                            break;
                        case 4:
                            consultarSaldo();
                            break;
//                        case 5:
//                            realizarSaque();
//                            break;
//                        case 6:
//                            realizarDeposito();
//                            break;
                    }
                } catch (RegraDeNegocioException e) {
                    System.out.println("Erro: " +e.getMessage());
                    System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu.");
                    sc.next();
                }
                opcao = exibirMenu();
            }
        }

        private static void encerrarConta() {
            System.out.println("Digite o número da conta:");
            var numeroDaConta = sc.nextInt();

            service.encerrar(numeroDaConta);
            System.out.println("Conta encerrada com sucesso!");
            System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal.");
            sc.close();
        }

        private static void abrirConta() {
            System.out.println("Digite o número da conta");
            var numeroDaConta = sc.nextInt();
            System.out.println("Digite o nome do cliente");
            var nome = sc.next();
            System.out.println("Digite o cpf do cliente");
            var cpf = sc.next();
            System.out.println("Digite o email do cliente");
            var email = sc.next();

            service.abrir(new DadosAberturaConta(numeroDaConta, new DadosCadastroCliente(nome, cpf, email)));
        }

        private static void listarContas() {
            System.out.println("Contas Cadastradas:");
            var contas = service.listarContasAbertas();
            contas.stream().forEach(System.out::println);

            System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal.");
            sc.next();
        }

        private static int exibirMenu() {
            System.out.println("""
                    BANK - ESCOLHA UMA OPÇÃO:
                    1 -> Listar contas abertas
                    2 -> Abertura de conta
                    3 -> Encerramento de conta
                    4 -> Consultar saldo de uma conta
                    5 -> Realizar saque em uma conta
                    6 -> Realizar depósito em uma conta
                    7 -> Sair
                    """);
            return sc.nextInt();
        }
    }