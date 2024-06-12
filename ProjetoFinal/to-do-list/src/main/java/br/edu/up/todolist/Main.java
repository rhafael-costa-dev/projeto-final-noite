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
            exibirMenu();
            op = Util.lerInputInteiro(scanner);
            exibirMenu(scanner, op);
        } while (op != 0);
        scanner.close();
    }

    /**
     * Método responsável por delegar as escolha do usuário
     * @param scanner
     * @param op
     */
    private static void exibirMenu(Scanner scanner, int op) {
        switch (op) {
            case 0 -> System.out.println("Tchauuuu Obrigado!");
            case 1 -> ToDoListView.iniciar(scanner);
            case 99 -> Util.exibirFeedbackMessage("Opção invalida. Tente novamente!");
            default -> Util.exibirFeedbackMessage("Opção invalida! Escolha uma opção valida de acordo com o Menu");
        }
    }

    /**
     * Método responsável por exibir o menu principal
     */
    private static void exibirMenu() {
        System.out.println("###   MENU PRINCIPAL   ###");
        System.out.println("0 - Sair");
        System.out.println("1 - TO DO LIST");
    }

}