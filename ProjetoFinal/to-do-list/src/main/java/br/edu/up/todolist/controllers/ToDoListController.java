package br.edu.up.todolist.controllers;

import br.edu.up.todolist.models.Tarefa;

public class ToDoListController {

    public static void cadastrar(Tarefa tarefa) {
        if (tarefa.getUsuario() == null) {
            // lançar exceção
        }

        if (tarefa.getTitulo() != null && tarefa.getTitulo().length() > 10) {
            // lançar exceção
        }

    }

}
