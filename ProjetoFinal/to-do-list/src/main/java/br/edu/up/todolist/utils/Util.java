package br.edu.up.todolist.utils;

import br.edu.up.todolist.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Util {
    private static final Logger logger = LogManager.getLogger(Util.class);

    /**
     * Método responsável por realizar a leitura do input de inteiros
     * @param scanner
     * @return
     */
    public static int lerInputInteiro(Scanner scanner) {
        logger.info("Inicializando o processo de leitura de número inteiro!");
        try {
            int numero = scanner.nextInt();
            return numero;
        } catch (InputMismatchException ex) {
            logger.error("Ocorreu um erro ao tentar ler um núemro inteiro", ex);
            return 99;
        } finally {
            scanner.nextLine();
        }
    }
}
