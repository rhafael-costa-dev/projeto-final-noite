package br.edu.up.todolist.views;

import br.edu.up.todolist.controllers.ToDoListController;
import br.edu.up.todolist.controllers.UsuarioController;
import br.edu.up.todolist.exceptions.TarefaNotFoundException;
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

    /**
     * Método responsável por lidar com a escolha do usuário
     * @param scanner
     * @param op
     */
    private static void exibirEscolhaUsuario(Scanner scanner, int op) {
        switch (op) {
            case 0 -> Util.exibirFeedbackMessage("");
            case 1 -> cadastrar(scanner);
            case 2 -> atualizar(scanner);
            case 3 -> remover(scanner);
            case 4 -> listar();
            case 5 -> detalharTarefa(scanner);
            default -> Util.exibirFeedbackMessage("Opção invalida! Escolha uma opção valida de acordo com o Menu");
        }
    }

    /**
     * Método responsável por listar os dados da tarefa
     */
    public static void listar() {
        var tarefas = ToDoListController.listar();
        System.out.println("###########################");
        tarefas.forEach(t -> {
            exibirDadosTarefa(t, false);
        });
        System.out.println("###########################");
    }

    /**
     * Método responsável por exibir os detalhes da tarefa
     * @param scanner
     */
    public static void detalharTarefa(Scanner scanner) {
        try {
            listar();
            System.out.println("Escolha uma tarefa.");
            var uuid = scanner.nextLine();

            var tarefa = ToDoListController.buscarTarefaPorUuid(UUID.fromString(uuid));
            exibirDadosTarefa(tarefa, true);
        } catch (TarefaNotFoundException ex) {
            Util.exibirFeedbackMessage(ex.getMessage());
            logger.error("Ocorreu um erro ao tentar buscar os dados da tarefa", ex);
        }
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
        } catch (TarefaNotFoundException ex) {
            Util.exibirFeedbackMessage(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        } catch (Exception ex) {
            var message = "Ocorreu um erro ao tentar atualizar a tarefa";
            Util.exibirFeedbackMessage(message);
            logger.error(message, ex);
        }
    }

    /**
     *
     * @param scanner
     */
    private static void remover(Scanner scanner) {
        try {
            listar();
            System.out.println("Qual tarefa deseja remover?");
            var uuid = scanner.nextLine();

            ToDoListController.remover(UUID.fromString(uuid));
        } catch (TarefaNotFoundException ex) {
            Util.exibirFeedbackMessage(ex.getMessage());
            logger.error("Ocorreu um erro ao tentar remover uma tarefa", ex);
        }
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
        System.out.println("5 - Exibir Detalhes da Tarefa");
    }

    private static void exibirDadosTarefa(Tarefa tarefa, boolean exibirDetalhes) {
        System.out.println("UUID: " + tarefa.getUuid());
        System.out.println("Titulo: " + tarefa.getTitulo());
        if (exibirDetalhes) {
            System.out.println("Descrição: " + tarefa.getDescricao());
            System.out.println("Prioridade: " + tarefa.getPrioridade());
            System.out.println("Usuário: " + tarefa.getUsuario().getName());
        }
        System.out.println("------------------------");
    }
}
