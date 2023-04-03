package service;

import model.Biblioteca;
import model.Livro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaServiceTeste {

    @Test
    public void deveVerificarLivrosDisponiveisCorretamente(){
        //Given
        BibliotecaService bibliotecaService = new BibliotecaService();
        Biblioteca biblioteca = new Biblioteca();
        Livro livro = new Livro("Livro","João");
        biblioteca.adicionarLivro(livro);
        List<Livro> livrosDisponiveis;
        //When
        livrosDisponiveis = bibliotecaService.verificarLivrosDisponiveis(biblioteca);
        //Then
        List<Livro> livros = new ArrayList<>();
        livros.add(livro);

        Assertions.assertEquals(livros,livrosDisponiveis);
    }
}
