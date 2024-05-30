package br.edu.up.todolist.daos;

import br.edu.up.todolist.models.FormataDado;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public abstract class BaseDao<T> {
    private static final Logger logger = LogManager.getLogger(BaseDao.class);
    public static void escrever(String fileName, FormataDado objeto) {
        logger.info("Abrindo o arquivo de .txt referente a Tarefas.");
        try (BufferedWriter br = new BufferedWriter(new FileWriter(fileName, true))) {
            logger.info("Salvando os dados de uma nova tarefa.");
            br.write(objeto.formatacao());
            br.newLine();
        } catch (IOException ex) {
            logger.error("Ocorreu um erro ao tentar salvar os dados da tarefa no arquivo.", ex);
        }
    }
    public abstract List<T> listar(String fileName);
}
