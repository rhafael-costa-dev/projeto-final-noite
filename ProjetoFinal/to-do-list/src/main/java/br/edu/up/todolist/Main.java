package br.edu.up.todolist;

import br.edu.up.todolist.utils.Util;
import br.edu.up.todolist.views.ToDoListView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        int op;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("###   MENU PRINCIPAL   ###");
            System.out.println("0 - Sair");
            System.out.println("1 - Menu TO-DO-LIST");
            System.out.println("2 - Menu Usuarios");

            op = Util.lerInputInteiro(scanner);
            exibirMenu(scanner, op);
        } while (op != 0);
        scanner.close();
    }

    private static void exibirMenu(Scanner scanner, int op) {
        switch (op) {
            case 0:
                System.out.println("Tchauuuu Obrigado!");
                break;
            case 1:
                ToDoListView.exibirMenu(scanner);
                break;
            case 2:
                ToDoListView.exibirMenu(scanner);
                break;
            case 99:
                System.out.println("Opção invalida. Tente novamente!");
                break;

            default:
                System.out.println("Opção invalida! Escolha uma opção valida de acordo com o Menu");
                break;
        }
    }

}