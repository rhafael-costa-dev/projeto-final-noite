package br.edu.up.todolist.daos;

import br.edu.up.todolist.controllers.UsuarioController;
import br.edu.up.todolist.models.Tarefa;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class TarefaDao extends BaseDao {
    private static final Logger logger = LogManager.getLogger(TarefaDao.class);

    /**
     * Método responsável por listar as informações das tarefas
     * @param fileName
     * @return
     */
    public static List<Tarefa> listar(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // declaração de variavéis
            String linha = null;
            List<Tarefa> tarefas = new ArrayList<>();
            // Laço de repetição para leitura do arquivo de tarefas
            while ( (linha = reader.readLine()) != null) {
                var tarefa = parse(linha);
                tarefas.add(tarefa);
            }
            return tarefas;
        } catch (IOException ex) {
            logger.error("Ocorreu um erro ao tentar listar as tarefas.", ex);
           return null;
        }
    }

    /**
     * Método responsável por fazer o parse da linha do arquivo para o objeto Tarefa
     * @param objeto
     * @return
     */
    private static Tarefa parse(String objeto) {
        // Array de dados da tarefa
        var dados = objeto.split(";");

        var uuid = UUID.fromString(dados[0]);
        var usuarioUUID = UUID.fromString(dados[4]);

        var usuario = UsuarioController.buscarUsuarioPorUUID(usuarioUUID);
        var tarefa = new Tarefa(dados[1], dados[2], dados[3], usuario);
        tarefa.setUuid(uuid);

        return tarefa;
    }

}
