package MiniBanco;
//projeto de estudo
    //Conceito Praticados:
        //Livro: Think Java 2ed.
            // - Variáveis e tipos primitivos
            // - Entrada de dados com Scanner
            // - Métodos void e com retorno
            // - Condicionais (if - else - else if)
            // - Booleanos e validações
            // - Iterações com for, do while e while
            // - Escopo de Variáveis
//@author: SEU NOME
//@version: 1.0

import java.util.Scanner;
public class MiniBanco {
        //CONSTANTES:
        static final double LIMITE_SAQUE = 1000.00; // Valor limite de saque
        static final double TAXA_SAQUE = 0.02; // Taxa de saque de 2%

        static void exibirExtrato(String[] extrato, int totalLinhas){
                System.out.println("\n===EXTRATO===");
                if(totalLinhas == 0){
                    System.out.println("\nNenhum movimentação registrada.");
            }else {
                for (int i = 0; i < totalLinhas;i++){
                    System.out.println("" + extrato[i]);
                }
            }
            System.out.println
            ("==================================");
        }

        static int registrar(String[] extrato, int totalLinhas, String linha){
            extrato[totalLinhas] = linha;
            return totalLinhas + 1;
        }

        static double sacar(double saldo, double valor){
            return saldo - calcularTotalSaque(valor);
        }

        static double calcularTotalSaque(double valor){
            return valor + (valor * TAXA_SAQUE);
        }

        static boolean dentroDoLimite(double valor){
            return valor <= LIMITE_SAQUE;
        }
        
        static boolean saldoSuficiente(double saldo, double valor){
            return saldo >= calcularTotalSaque(valor);
        }

        static boolean valorEhValido(double valor){
            return valor > 0;
        }

        static double depositar(double saldo, double valor){
            return  saldo + valor;
        }

        static void exibirSaldo(double saldo){
            System.out.printf(" Saldo atual: R$ %.2f%n", saldo);
        }

        static void exibirMenu() {
            System.out.println("=== MINI BANCO ===");
            System.out.println("1 - Depositar");
            System.out.println("2 - Sacar");
            System.out.println("3 - Consultar saldo");
            System.out.println("4 - Ver extrato");
            System.out.println("0 - Sair");
            System.out.println("Digite uma das opções: ");
        }
        public static void main(String[] args) {
        
    
            //Objeto para entrada de dados:
            Scanner scanner = new Scanner(System.in);

            double saldo = 0.0;//O saldo inicial é sempre 0.

            int opcao = 1;//opção do menu
            
            String[] extrato = new String[50];
            int totalLinhas = 0;

            //Boas vindas:
            System.out.print("Digite o seu nome: ");
            String nome = scanner.nextLine();

            //System.out.printf("Olá, %s! Saldo inicial: R$%.2f%n", nome, saldo);

            while(opcao != 0){

                exibirMenu();
                opcao = scanner.nextInt();

                if(opcao == 1){
                    //FLUXO DEPOSITAR:
                    //System.out.println("Depositar - Em breve");
                    System.out.print("Valor a depositar: R$ ");
                    double valor = scanner.nextDouble();

                    if(!valorEhValido(valor)){
                        System.out.println("Valor inválido! Deve ser maior que zero!");
                    }else{
                        saldo = depositar(saldo, valor);
                        System.out.println("Deposito realizado com sucesso!");
                        exibirSaldo(saldo);
                        totalLinhas = registrar(extrato, totalLinhas,
                        String.format("DEPOSITO + R$ %.2f -> Saldo: R$ %.2f", valor, saldo));
                    }

                }else if (opcao == 2){
                    //FLUXO SACAR:
                    //System.out.println("Sacar - Em breve");

                    System.out.print("Valor a sacar: R$ ");
                    double valorSaque = scanner.nextDouble();

                    if(!valorEhValido(valorSaque)){
                        System.out.printf("Valor inválido");
                    }else if (!dentroDoLimite(valorSaque)){
                        System.out.printf("Limite Execido. Maximo: R$%.2f%n", LIMITE_SAQUE);
                    }else if(!saldoSuficiente(saldo, valorSaque)){
                        System.out.printf("Saldo insuficiente, Necessario: R$ %.2f%n",calcularTotalSaque(valorSaque));
                    }else {
                        double taxa = valorSaque * TAXA_SAQUE;
                        saldo = sacar(saldo, valorSaque);
                        System.out.printf("Saque realizado. Taxa cobrada: R$%.2f%n", taxa);
                        exibirSaldo(saldo);
                        totalLinhas = registrar(extrato, totalLinhas,String.format("SAQUE -R$ %.2f -> Saldo: R$%.2f",valorSaque, saldo));
                    }

                }else if (opcao == 3){
                    //System.out.println("Consultar saldo - Em breve");
                    exibirSaldo(saldo);
                }else if (opcao == 4){
                    //System.out.println("Extrato - Em breve");
                    exibirExtrato(extrato, totalLinhas);
                }else if (opcao == 0){
                    exibirExtrato(extrato, totalLinhas);
                    System.out.println("Até logo " + nome + "!");
                }else {
                    System.out.println("Opção Inválida. Tente novamente.");
                }
            }
            
            scanner.close();
    }

}
