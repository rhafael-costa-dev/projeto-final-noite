package br.edu.up.todolist.daos;

import br.edu.up.todolist.controllers.UsuarioController;
import br.edu.up.todolist.models.Tarefa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class TarefaDao extends BaseDao<Tarefa> {

    public static List<Tarefa> listar(String fileName) {
        List<Tarefa> tarefas = new ArrayList<>();
        try (BufferedReader buffRead = new BufferedReader(new FileReader(fileName))) {
            String linha = buffRead.readLine();
            while (true) {
                if (linha == "" || linha == null)
                    break;

                var dados = linha.split(";");
                var uuid = dados[4].toString();
                var usuario = UsuarioController.buscarUsuarioPorUUID(UUID.fromString(uuid));
                var tarefa = new Tarefa(dados[1].toString(),
                                        dados[2].toString(), dados[3].toString(), usuario);

                tarefas.add(tarefa);
                linha = buffRead.readLine();
            }
            return tarefas;
        } catch (IOException ex) {
           return null;
        }
    }
}
