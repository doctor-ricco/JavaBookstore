package com.javabooks.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Emprestimo {
    private Long id;
    private Livro livro;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;  // Data de devolução pode iniciar a null
    private LocalDate prazoDevolucao;



    public Emprestimo(Long id, Livro livro, Usuario usuario, LocalDate dataEmprestimo, int diasDePrazo) {
        this.id = id;
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.prazoDevolucao = dataEmprestimo.plusDays(diasDePrazo);
        this.dataDevolucao = null;  // A data de devolução será preenchida após o livro ser devolvido
    }

    // Verificar se a entrega está atrasada
    public boolean estaAtrasado() {
        if (dataDevolucao != null) {
            return dataDevolucao.isAfter(prazoDevolucao); // Se a devolução foi feita após o prazo
        } else {
            return LocalDate.now().isAfter(prazoDevolucao); // Se o prazo já passou e ainda não foi devolvido
        }
    }


    // Sessão de Getters e Setters
    public LocalDate getPrazoDevolucao() {
        return prazoDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }


    // Método para calcular dias de atraso (se houver)
    public long diasDeAtraso() {
        if (estaAtrasado()) {
            return ChronoUnit.DAYS.between(prazoDevolucao, LocalDate.now());
        }
        return 0;
    }
    @Override
    public String toString() {
        return "Emprestimo{" +
                "id=" + id +
                ", livro=" + livro +
                ", usuario=" + usuario +
                ", dataEmprestimo=" + dataEmprestimo +
                ", prazoDevolucao=" + prazoDevolucao +
                ", dataDevolucao=" + dataDevolucao +
                '}';
    }
}
