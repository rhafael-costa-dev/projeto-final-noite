package br.edu.up.todolist.controllers;

import br.edu.up.todolist.models.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class UsuarioController {
    private static List<Usuario> usuarios = List.of(
        new Usuario(UUID.fromString("fa1ec932-e667-4566-a4e8-2111d874c9ec"), "João"),
        new Usuario(UUID.fromString("2290b3af-e438-4fd4-befd-082dc77eb4da"), "Anna")
    );
    public static List<Usuario> listar() {
        return usuarios;
    }
    public static Usuario buscarUsuarioPorUUID(UUID uuid) {
        Optional<Usuario> usuario = usuarios.stream()
                                            .filter(u -> u.getUuid().equals(uuid))
                                            .findFirst();
        return usuario.isPresent() ? usuario.get() : null;
    }
    public static List<Usuario> buscarUsuarioPorNome(String nome) {
        List<Usuario> lista = usuarios.stream()
                                         .filter(u -> u.getName().equals(nome))
                                         .toList();
        return  lista;
    }
}
