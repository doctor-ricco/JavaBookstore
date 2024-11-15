package com.javabooks.service;

import com.javabooks.model.Emprestimo;
import com.javabooks.model.Livro;
import com.javabooks.model.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoService {
    private List<Emprestimo> emprestimos = new ArrayList<>();
    private LivroService livroService;
    private UsuarioService usuarioService;

    public EmprestimoService(LivroService livroService, UsuarioService usuarioService) {
        this.livroService = livroService;
        this.usuarioService = usuarioService;
    }

    // Realizar empréstimo com prazo definido
    public Emprestimo realizarEmprestimo(Long livroId, Long usuarioId, int diasDePrazo) {
        Livro livro = livroService.buscarLivroPorId(livroId);
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);

        if (livro == null || !livro.isDisponivel()) {
            throw new IllegalArgumentException("Livro indisponível ou não encontrado.");
        }

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        Emprestimo emprestimo = new Emprestimo(
                (long) (emprestimos.size() + 1),
                livro,
                usuario,
                LocalDate.now(),
                diasDePrazo // Definindo o prazo
        );

        emprestimos.add(emprestimo);
        livroService.atualizarDisponibilidade(livroId, false);  // Marca o livro como indisponível
        return emprestimo;
    }

    // Método para devolver livro e validar atraso
    public void devolverLivro(Long emprestimoId) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getId().equals(emprestimoId)) {
                emprestimo.setDataDevolucao(LocalDate.now());
                livroService.atualizarDisponibilidade(emprestimo.getLivro().getId(), true);  // Marca o livro como disponível
                if (emprestimo.estaAtrasado()) {
                    System.out.println("Atenção: o empréstimo está atrasado em " + emprestimo.diasDeAtraso() + " dias.");
                } else {
                    System.out.println("Devolução dentro do prazo.");
                }
                return;
            }
        }
        throw new IllegalArgumentException("Empréstimo não encontrado.");
    }

    // Listar empréstimos
    public List<Emprestimo> listarEmprestimos() {
        return emprestimos;
    }
}
