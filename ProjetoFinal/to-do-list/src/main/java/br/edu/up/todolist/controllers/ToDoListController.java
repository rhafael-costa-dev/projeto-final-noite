package br.edu.up.todolist.controllers;

import br.edu.up.todolist.daos.TarefaDao;
import br.edu.up.todolist.models.Tarefa;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ToDoListController {
    private static final Logger logger = LogManager.getLogger(ToDoListController.class);
    private static final String fileName = "C:\\Users\\autologon\\Documents\\Projetos\\projeto-final-noite\\dados.txt";
    public static void cadastrar(Tarefa tarefa) {
        logger.info("Cadastrando tarefa");
        TarefaDao.escrever(fileName, tarefa);
        if (tarefa.getUsuario() == null) {
            // lançar exceção
        }

        if (tarefa.getTitulo() != null && tarefa.getTitulo().length() > 10) {
            // lançar exceção
        }
    }
    public static List<Tarefa> listar() {
        return TarefaDao.listar(fileName);
    }

}
