package com.javabooks.service;

import com.javabooks.model.Livro;
import java.util.ArrayList;
import java.util.List;

public class LivroService {
    private List<Livro> livros = new ArrayList<>();  // Lista que simula a "base de dados"

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }


    public List<Livro> listarLivros() {
        return livros;
    }

    public List<Livro> listarLivrosDisponiveis() {
        List<Livro> disponiveis = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                disponiveis.add(livro);
            }
        }
        return disponiveis;
    }

    public Livro buscarLivroPorId(Long id) {
        for (Livro livro : livros) {
            if (livro.getId().equals(id)) {
                return livro;
            }
        }
        return null;  // Se não encontrar, retorna null
    }

    public void atualizarDisponibilidade(Long id, boolean disponivel) {
        Livro livro = buscarLivroPorId(id);
        if (livro != null) {
            livro.setDisponivel(disponivel);
        }
    }
}
