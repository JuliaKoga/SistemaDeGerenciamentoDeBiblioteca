package service;

import model.Biblioteca;
import model.Emprestimo;
import model.Livro;
import model.Pessoa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoService {


    public void emprestarLivro(Livro livro, Pessoa pessoa, Biblioteca biblioteca) throws Exception{
        if (livro.isDisponivel()){
            Emprestimo emprestimo = new Emprestimo(pessoa,livro);

            livro.adicionarEmprestimo(emprestimo);
            pessoa.adicionarEmprestimo(emprestimo);
            biblioteca.adicionarEmprestimo(emprestimo);
            livro.setDisponivel(false);

            System.out.println("Livro "+ livro.getNome() +" emprestado para "+ pessoa.getNome()+" até "+emprestimo.getDevolucao());
        }else {
            throw new Exception("O livro "+ livro.getNome() +" não está disponível.");
        }
    }

    public Emprestimo buscarEmprestimo(Livro livro, Pessoa pessoa){
        for (Emprestimo emprestimo : livro.getEmprestimos()) {
            if (emprestimo.getPessoa().equals(pessoa)) {
                return emprestimo;
            }
        }
        return null;
    }

    public void devolverLivro(Livro livro, Pessoa pessoa,Biblioteca biblioteca) throws Exception{
        Emprestimo emprestimo = buscarEmprestimo(livro, pessoa);
        if (emprestimo != null){
            livro.removerEmprestimo(emprestimo);
            pessoa.removerEmprestimo(emprestimo);
            biblioteca.removerEmprestimo(emprestimo);
            livro.setDisponivel(true);

            System.out.println("Livro "+ livro.getNome()+" devolvido.");
        }else {
            throw new Exception("Emprestimo não encontrado.");
        }
    }

    public void renovarEmprestimo(Livro livro, Pessoa pessoa) throws Exception{
        Emprestimo emprestimo = buscarEmprestimo(livro, pessoa);
        if (emprestimo != null){
            emprestimo.setDevolucao(LocalDate.now().plusDays(30));

            System.out.println("Empréstimo do livro "+ livro.getNome()+" renovado, nova data de devolução: "+ emprestimo.getDevolucao());
        }else {
            throw new Exception("Emprestimo não encontrado.");
        }
    }

    public List<Emprestimo> verificarEmprestimosAtrasados(Biblioteca biblioteca){
        List<Emprestimo> emprestimosAtrasados = new ArrayList<>();
        for (Emprestimo emprestimo:biblioteca.getEmprestimos()){
            if (emprestimo.getDevolucao().isBefore(LocalDate.now())){
                emprestimosAtrasados.add(emprestimo);
            }
        } return emprestimosAtrasados;
    }
}
