package model;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        this.emprestimos.add(emprestimo);
    }

    public void removerEmprestimo(Emprestimo emprestimo) {
        this.emprestimos.remove(emprestimo);
    }

    public List<Emprestimo> getEmprestimos() {
        return this.emprestimos;
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }
    public List<Livro> getLivros() {
        return livros;
    }
}
