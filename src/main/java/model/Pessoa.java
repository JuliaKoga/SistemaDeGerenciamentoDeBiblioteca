package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pessoa {
    private String nome;
    private LocalDate dataDeNascimento;
    private String email;
    private String telefone;
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public Pessoa(String nome, LocalDate dataDeNascimento, String email, String telefone) {
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.email = email;
        this.telefone = telefone;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", dataDeNascimento=" + dataDeNascimento +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
