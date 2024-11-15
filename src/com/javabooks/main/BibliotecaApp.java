package com.javabooks.main;

import com.javabooks.model.Livro;
import com.javabooks.model.Usuario;
import com.javabooks.service.EmprestimoService;
import com.javabooks.service.LivroService;
import com.javabooks.service.UsuarioService;

public class BibliotecaApp {
    public static void main(String[] args) {
        LivroService livroService = new LivroService();
        UsuarioService usuarioService = new UsuarioService();
        EmprestimoService emprestimoService = new EmprestimoService(livroService, usuarioService);

        // Teste: Criando livros
        Livro livro1 = new Livro(1L, "Dom Quixote", "Miguel de Cervantes", "12345", true);
        Livro livro2 = new Livro(2L, "O Senhor dos Anéis", "J.R.R. Tolkien", "67890", true);
        livroService.adicionarLivro(livro1);
        livroService.adicionarLivro(livro2);

        // Teste: Criando usuários
        Usuario usuario1 = new Usuario(1L, "Ana Silva", "ana@email.com", "senha123");
        Usuario usuario2 = new Usuario(2L, "Carlos Souza", "carlos@email.com", "senha123");
        usuarioService.adicionarUsuario(usuario1);
        usuarioService.adicionarUsuario(usuario2);

        // Teste:  Realizando empréstimo com prazo de 7 dias
        System.out.println("\nRealizando empréstimo do livro 'Dom Quixote' para o usuário 'Ana Silva' com prazo de 7 dias...");
        emprestimoService.realizarEmprestimo(1L, 1L, 7);

        // Teste:  Devolvendo o livro após 10 dias para simular atraso
        System.out.println("\nDevolvendo o livro 'Dom Quixote' (com atraso de 3 dias)...");
        emprestimoService.devolverLivro(1L);
    }
}
