package service;

import model.Biblioteca;
import model.Emprestimo;
import model.Livro;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaService {

    public List<Livro> verificarLivrosDisponiveis(Biblioteca biblioteca){
        List<Livro> livrosDisponiveis = new ArrayList<>();
        for (Livro livro:biblioteca.getLivros()){
            if (livro.isDisponivel()){
                livrosDisponiveis.add(livro);
            }
        } return livrosDisponiveis;
    }

}
