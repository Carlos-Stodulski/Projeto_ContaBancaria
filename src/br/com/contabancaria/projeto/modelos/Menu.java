package br.com.contabancaria.projeto.modelos;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Menu extends Operacoes {
    protected double escolheOpcao;
    protected List<Double> depositos = new ArrayList<>();
    protected List<Double> transferencias = new ArrayList<>();
    protected String dataInicioSessao;

    public void exibirMenu() {
        System.out.println("Qual operação você deseja realizar?");
        System.out.println("(1) - Consultar saldo");
        System.out.println("(2) - Depósito");
        System.out.println("(3) - Transferência");
        System.out.println("(4) - Encerrar sessão");
        System.out.println("Digite o número da opção desejada: \n");
    }

    public void exibirDadosIniciais() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss (dd/MM/yyyy) ");
        LocalDateTime agora = LocalDateTime.now();
        dataInicioSessao = agora.format(formatter);
        System.out.println("****************************************");
        System.out.println("Bem-vindo, " + getTitular() + "!");
        System.out.println("Número da conta: " + getNumeroDaConta());
        System.out.println("Inicio da sessão: " + dataInicioSessao);
        System.out.println("***************************************\n");
    }

    public void realizarOperacoesMenu() {
        exibirDadosIniciais();
        do {
            exibirMenu();
            try {
                String valorDigitado = leitura.nextLine();
                escolheOpcao = Double.parseDouble(valorDigitado);

                if (escolheOpcao == 1) {
                    consultarSaldo();

                } else if (escolheOpcao == 2) {
                    System.out.println("Qual o valor do depósito?");
                    double valorDeposito = lerNumero();
                    saldo = fazerDeposito(valorDeposito);
                    depositos.add(valorDeposito);

                } else if (escolheOpcao == 3) {
                    System.out.println("Qual o valor da transferência?");
                    double valorTransferencia = lerNumero();
                    saldo = fazerTransferencia(valorTransferencia);
                    transferencias.add(valorTransferencia);

                } else if (escolheOpcao == 4) {
                    encerrarSessao();

                } else {
                    System.out.println("*****************************************************");
                    System.out.println("* OPERAÇÃO CANCELADA: Digite apenas opções válidas. *");
                    System.out.println("*****************************************************");
                }
            } catch (NumberFormatException e) {
                System.out.println("*****************************************************");
                System.out.println("* OPERAÇÃO CANCELADA: Digite apenas números válidos.*");
                System.out.println("*****************************************************");
            }
        } while (escolheOpcao != 4);
    }

    public double fazerDeposito(double valor) {
        if (valor <= 0) {
            System.out.println("******************************************");
            System.out.println("* OPERAÇÃO CANCELADA: Valor inválido     *");
            System.out.println("******************************************\n");
        } else {
            saldo += valor;
            System.out.println("********************************");
            System.out.println("* Valor recebido com sucesso.  *");
            System.out.println("********************************");
        }
        return saldo;
    }

    public void encerrarSessao() {
        super.encerrarSessao();
        try {
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String nomeArquivo = "comprovante_movimentacoes" + random.nextInt(999) + ".txt";

            FileWriter arquivoEscrita = new FileWriter(nomeArquivo);
            BufferedWriter escritor = new BufferedWriter(arquivoEscrita);
            escritor.write("********************************************\n");
            escritor.write("* Histórico de movimentações nesta sessão: *\n");
            escritor.write("* Sessão iniciada em " + dataInicioSessao+"*\n" );
            escritor.write("********************************************\n\n");

            for (Double valor : depositos) {
                escritor.write("● Depósito no valor de R$" + String.format("%.2f", valor) + "\n");
            }

            for (Double valor : transferencias) {
                escritor.write("● Transferência no valor de R$" + String.format("%.2f", valor));
            }

            escritor.write("\nSaldo final: R$" + String.format("%.2f", saldo));
            escritor.close();
            System.out.println("Informações salvas em arquivo: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao salvar as informações em arquivo.");
            e.printStackTrace();
        }
    }
}