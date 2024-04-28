package br.com.contabancaria.projeto.modelos;
import java.util.Random;
import java.util.Scanner;
public class Conta {
    protected double saldo;
    protected String titular;
    protected int numeroDaConta;
    Scanner pegaDados = new Scanner(System.in);
    Random random = new Random();

    public String getTitular() {
        return titular;
    }

    public int getNumeroDaConta() {
        return numeroDaConta;
    }

    public void criaUsuario() {
        System.out.println("Olá!\nDigite seu nome para iniciar a sessão:");
        boolean nomeValido = false;
        while (!nomeValido) {
            titular = pegaDados.nextLine();
            // Verifica se o nome contém apenas letras
            if (titular.matches("[a-zA-Z]+")) {
                nomeValido = true;
            } else {
                System.out.println("Por favor, insira apenas letras. Digite seu nome novamente:");
            }
        }
        numeroDaConta = random.nextInt(1000);
    }
}