package com.javabooks.service;

import com.javabooks.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    private List<Usuario> usuarios = new ArrayList<>();  // Simulando um "banco de dados" de usuários

    // Adicionar um novo usuário
    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    // Buscar usuário por ID
    public Usuario buscarUsuarioPorId(Long id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;  // Se o usuário não for encontrado
    }

    // Listar todos os usuários
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }
}
