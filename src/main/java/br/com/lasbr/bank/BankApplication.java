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
            while (opcao != 8) {
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
                        case 5:
                            realizarSaque();
                            break;
                        case 6:
                            realizarDeposito();
                            break;
                        case 7:
                            realizarTransferencia();
                                            }
                } catch (RegraDeNegocioException e) {
                    System.out.println("Erro: " +e.getMessage());
                    System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu.");
                    sc.next();
                }
                opcao = exibirMenu();
            }
        }

        private static void realizarTransferencia() {
            System.out.println("Digite o número da conta de origem:");
            var numeroDaContaOrigem = sc.nextInt();

            System.out.println("Digite o número da conta de destino:");
            var numeroDaContaDestino = sc.nextInt();

            System.out.println("Digite o valor a ser transferido:");
            var valor = sc.nextBigDecimal();

            service.realizarTransferencia(numeroDaContaOrigem, numeroDaContaDestino, valor);
            System.out.println("Transferência realizada com sucesso!");
            System.out.println("Pressione qualquer tecla e3 de Enter para voltar oa menu.");
        }

        private static void realizarDeposito() {
            System.out.println("Digite o número da conta:");
            var numeroDaConta = sc.nextInt();

            System.out.println("Digite o valor do depósito:");
            var valor = sc.nextBigDecimal();

            service.realizarDeposito(numeroDaConta, valor);

            System.out.println("Depósito realizado com sucesso");
            System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal.");
            sc.next();
        }

        private static void realizarSaque() {
            System.out.println("Digite o número da conta:");
            var numeroDaConta = sc.nextInt();

            System.out.println("Digite o valor do saque:");
            var valor = sc.nextBigDecimal();

            service.realizarSaque(numeroDaConta, valor);
            System.out.println("Saque realizado com sucesso!");
            System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal.");
            sc.next();
        }

        private static void consultarSaldo() {
            System.out.println("Digite o número da conta:");
            var numeroDaConta = sc.nextInt();
            var saldo = service.consultarSaldo(numeroDaConta);
            System.out.println("Saldo da conta: " +saldo);

            System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal.");
            sc.next();
        }

        private static void encerrarConta() {
            System.out.println("Digite o número da conta:");
            var numeroDaConta = sc.nextInt();

            service.encerrar(numeroDaConta);
            System.out.println("Conta encerrada com sucesso!");
            System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal.");
            sc.next();
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
            System.out.println("Conta aberta com sucesso!");
            System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal.");
            sc.next();
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
                    7 -> Realizar Transferência
                    8 -> Sair
                    """);

            return sc.nextInt();
        }
    }