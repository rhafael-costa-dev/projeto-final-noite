package br.edu.up.todolist.daos;

import br.edu.up.todolist.models.Tarefa;

import java.util.List;

public abstract class TarefaDao extends BaseDao<Tarefa> {

    @Override
    public List<Tarefa> listar(String fileName) {
        return null;
    }
}
