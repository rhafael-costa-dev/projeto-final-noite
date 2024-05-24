package br.edu.up.todolist.views;

import br.edu.up.todolist.controllers.ToDoListController;
import br.edu.up.todolist.models.Tarefa;
import br.edu.up.todolist.utils.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;


public class ToDoListView {
    private static final Logger logger = LogManager.getLogger(ToDoListView.class);
    public static void exibirMenu(Scanner scanner) {
        int op;
        do {
            System.out.println("###   MENU TO-DO-LIST   ###");
            System.out.println("0 - Sair");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Alterar");
            System.out.println("3 - Remover");
            System.out.println("4 - Listar");

            op = Util.lerInputInteiro(scanner);
        } while (op != 0);
    }

    private static void exibirMenu(Scanner scanner, int op) {
        switch (op) {
            case 0:
                break;
            case 1:
                Tarefa t = new Tarefa();
                ToDoListController.cadastrar(t);
                break;

            default:
                System.out.println("Opção invalida! Escolha uma opção valida de acordo com o Menu");
                break;
        }
    }
}
