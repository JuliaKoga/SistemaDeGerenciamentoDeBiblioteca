package model;

import java.time.LocalDate;

public class Emprestimo {
    private Pessoa pessoa;
    private Livro livro;
    private LocalDate devolucao;

    public Emprestimo(Pessoa pessoa, Livro livro) {
        this.pessoa = pessoa;
        this.livro = livro;
        this.devolucao = LocalDate.now().plusDays(30);
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDevolucao() {
        return devolucao;
    }

    public void setDevolucao(LocalDate devolucao) {
        this.devolucao = devolucao;
    }



    @Override
    public String toString() {
        return "Emprestimo{" +
                "pessoa=" + pessoa +
                ", livro=" + livro +
                ", devolucao=" + devolucao +
                '}';
    }
}
