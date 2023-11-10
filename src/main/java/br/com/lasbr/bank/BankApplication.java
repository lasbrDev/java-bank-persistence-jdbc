package br.com.lasbr.bank;


import java.util.Scanner;

    public class BankApplication {

        private  static Scanner sc = new Scanner(System.in).useDelimiter("\n");
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
                        case 5:
                            realizarSaque();
                            break;
                        case 6:
                            realizarDeposito();
                            break;
                    }
                } catch (RegraDeNegocioException e) {

                }
            }
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