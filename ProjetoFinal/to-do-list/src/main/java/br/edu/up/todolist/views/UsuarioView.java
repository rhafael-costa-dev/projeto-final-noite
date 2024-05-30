package br.edu.up.todolist.views;

import br.edu.up.todolist.controllers.UsuarioController;
import br.edu.up.todolist.models.Usuario;

public abstract class UsuarioView {

    public static void exibirUsuarios() {
        var usuarios = UsuarioController.listar();
        System.out.println("###########################");
        usuarios.forEach(u -> {
            print(u);
            System.out.println("------------------------");
        });
        System.out.println("###########################");
    }

    private static void print(Usuario u) {
        System.out.println("UUID: " + u.getUuid());
        System.out.println("Nome: " + u.getName());
    }
}
