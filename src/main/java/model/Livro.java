package model;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    private String nome;
    private String autor;
    private Boolean disponivel;
    private List<Emprestimo> emprestimos = new ArrayList<>();


    public Livro(String nome, String autor) {
        this.nome = nome;
        this.autor = autor;
        this.disponivel = true;
    }

    public String getNome() {
        return nome;
    }

    public String getAutor() {
        return autor;
    }

    public Boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        this.emprestimos.add(emprestimo);
    }

    public void removerEmprestimo(Emprestimo emprestimo) {
        this.emprestimos.remove(emprestimo);
    }

    public List<Emprestimo> getEmprestimos() {
        return this.emprestimos;
    }


    @Override
    public String toString() {
        return "Livro{" +
                "nome='" + nome + '\'' +
                ", autor='" + autor + '\'' +
                ", disponível=" + disponivel +
                '}';
    }
}
