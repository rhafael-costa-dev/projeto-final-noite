package br.edu.up.todolist.controllers;

import br.edu.up.todolist.daos.TarefaDao;
import br.edu.up.todolist.exceptions.TarefaNotFoundException;
import br.edu.up.todolist.models.FormatacaoEscrita;
import br.edu.up.todolist.models.Tarefa;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ToDoListController {
    private static final Logger logger = LogManager.getLogger(ToDoListController.class);
    private static final String FILE_NAME = "tarefas.txt";

    /**
     * Método responsável por listar as tarefas
     * @return
     */
    public static List<Tarefa> listar() {
        return TarefaDao.listar(FILE_NAME);
    }

    /**
     * Método responsável por buscar a tarefa por uuid
     * @param uuid
     * @return
     */
    public static Tarefa buscarTarefaPorUuid(UUID uuid) throws TarefaNotFoundException {
        var listaTarefas =  listar();
        Optional<Tarefa> tarefa =  listaTarefas.stream()
                                               .filter(t -> t.getUuid().equals(uuid))
                                               .findFirst();
        if (tarefa.isEmpty()) {
            throw new TarefaNotFoundException("Nenhuma tarefa encontrada com o UUID: " + uuid);
        }
        return tarefa.get();
    }

    /**
     *
     * @param tarefa
     */
    public static void cadastrar(Tarefa tarefa) {
        TarefaDao.escrever(FILE_NAME, List.of(tarefa), true);
    }

    public static void atualizar(UUID uuid, Tarefa tarefa) throws TarefaNotFoundException {
        var t = buscarTarefaPorUuid(uuid);
        // TODO: Alterar essa linha para lançar exc exception
        if (t == null) {
            return;
        }
        // Atualiza a tarefa a ser atualziada
        t.atualizarDados(tarefa);

        var listaAtualizada =  removerTarefaLista(t.getUuid());
        listaAtualizada.add(t);

        TarefaDao.escrever(FILE_NAME, listaAtualizada, false);
    }

    /**
     * Método responsável por remover um tarefa
     * @param uuid
     */
    public static void remover(UUID uuid) throws TarefaNotFoundException {
        var tarefa = buscarTarefaPorUuid(uuid);

        var listaAtualizada =  removerTarefaLista(tarefa.getUuid());
        TarefaDao.escrever(FILE_NAME, listaAtualizada, false);
    }

    private static List<FormatacaoEscrita> removerTarefaLista(UUID uuid) {
        // Busca todas as tarefas
        var tarefas = listar();

        List<FormatacaoEscrita> listaAtualizada =  new ArrayList<>();
        tarefas.forEach(t -> {
            // Verifica qual objeto será removido
            if (!t.getUuid().equals(uuid)) {
                listaAtualizada.add(t);
            }
        });
        return listaAtualizada;
    }

}
