package br.com.contabancaria.projeto.modelos;
import java.util.Scanner;
import java.text.DecimalFormat;
public class Operacoes extends br.com.contabancaria.projeto.modelos.Conta {

    protected Scanner leitura = new Scanner(System.in);

    public void consultarSaldo() {
        DecimalFormat formatoMoeda = new DecimalFormat("¤#,##0.00");
        String saldoFormatado = formatoMoeda.format(saldo);
        System.out.println("************************");
        System.out.println("Saldo atual: " + saldoFormatado);
        System.out.println("************************\n");
    }
    public double fazerDeposito(double valor) {
        System.out.println("Qual o valor de depósito?");
        double recebeValor = lerNumero();

        if (recebeValor <= 0) {
            System.out.println("******************************************");
            System.out.println("* OPERAÇÃO CANCELADA: Valor inválido     *");
            System.out.println("******************************************\n");
        } else {
            saldo += recebeValor;
            System.out.println("********************************");
            System.out.println("* Valor recebido com sucesso.  *");
            System.out.println("********************************");
        }
        return saldo;
    }

    public double fazerTransferencia(double valor) {
        if (saldo < valor) {
            System.out.println("******************************************");
            System.out.println("* OPERAÇÃO CANCELADA: Saldo insuficiente *");
            System.out.println("******************************************\n");
        } else {
            saldo -= valor;
            System.out.println("****************************************");
            System.out.println("* Transferência realizada com sucesso. *");
            System.out.println("****************************************\n");
        }
        return saldo;
    }
    public void encerrarSessao() {
        System.out.println("********************************************************************************************");
        System.out.println("*                                 SESSÃO FINALIZADA                                        *");
        System.out.println("* Aguarde enquanto geramos seu comprovante .txt das movimentações realizadas nesta sessão. *");
        System.out.println("* Obrigado pela preferência.                                                               *");
        System.out.println("* Volte sempre!                                                                            *");
        System.out.println("*********************************************************************************************");
    }

    protected double lerNumero() {
        String input = leitura.nextLine();
        input = input.replace(',', '.');
        return Double.parseDouble(input);
    }
}