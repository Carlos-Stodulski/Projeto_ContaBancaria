package br.com.contabancaria.projeto.main;
import br.com.contabancaria.projeto.modelos.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.criaUsuario();
        menu.realizarOperacoesMenu();
    }
}