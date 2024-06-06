package br.edu.up.todolist.views;

import br.edu.up.todolist.controllers.ToDoListController;
import br.edu.up.todolist.controllers.UsuarioController;
import br.edu.up.todolist.models.Tarefa;
import br.edu.up.todolist.utils.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import java.util.UUID;


public class ToDoListView {
    private static final Logger logger = LogManager.getLogger(ToDoListView.class);

    public static void iniciar(Scanner scanner) {
        int op;
        do {
            exibirMenuOpcoes();
            op = Util.lerInputInteiro(scanner);
            exibirEscolhaUsuario(scanner, op);
        } while (op != 0);
    }

    private static void exibirEscolhaUsuario(Scanner scanner, int op) {
        switch (op) {
            case 0:
                break;
            case 1:
                cadastrar(scanner);
                break;
            case 2:
                atualizar(scanner);
                break;
            case 3:
                remover(scanner);
                break;
            case 4:
                listar();
                break;

            default:
                System.out.println("Opção invalida! Escolha uma opção valida de acordo com o Menu");
                break;
        }
    }

    public static void listar() {
        var tarefas = ToDoListController.listar();
        System.out.println("###########################");
        tarefas.forEach(t -> {
            System.out.println("UUID: " + t.getUuid());
            System.out.println("Titulo: " + t.getTitulo());
            System.out.println("------------------------");
        });
        System.out.println("###########################");
    }

    private static void cadastrar(Scanner scanner) {
        try {
            System.out.println("Digite o titulo: ");
            var titulo = scanner.nextLine();

            System.out.println("Digite o descrição: ");
            var descricao = scanner.nextLine();

            System.out.println("Digite o prioridade: ");
            var prioridade = scanner.nextLine();

            UsuarioView.exibirUsuarios();
            System.out.println("Digite o UUID do usuário: ");
            var uuid = scanner.nextLine();
            var usuario = UsuarioController.buscarUsuarioPorUUID(UUID.fromString(uuid));

            var tarefa = new Tarefa(titulo, descricao, prioridade, usuario);
            ToDoListController.cadastrar(tarefa);
        } catch (Exception ex) {

        }
    }

    private static void atualizar(Scanner scanner) {
        try {
            // chama  a listar tarefas
            listar();
            System.out.println("Qual tarefa deseja atualizar?");
            var uuid = scanner.nextLine();

            System.out.println("Digite o descrição: ");
            var descricao = scanner.nextLine();

            System.out.println("Digite o prioridade: ");
            var prioridade = scanner.nextLine();

            var tarefa = new Tarefa();
            tarefa.setDescricao(descricao);
            tarefa.setPrioridade(prioridade);

            ToDoListController.atualizar(UUID.fromString(uuid), tarefa);
        } catch (Exception ex) {

        }
    }

    /**
     *
     * @param scanner
     */
    private static void remover(Scanner scanner) {
        listar();
        System.out.println("Qual tarefa deseja remover?");
        var uuid = scanner.nextLine();

        ToDoListController.remover(UUID.fromString(uuid));
    }

    /**
     *
     */
    private static void exibirMenuOpcoes() {
        System.out.println("###   MENU TO-DO-LIST   ###");
        System.out.println("0 - Sair");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Alterar");
        System.out.println("3 - Remover");
        System.out.println("4 - Listar");
    }
}
