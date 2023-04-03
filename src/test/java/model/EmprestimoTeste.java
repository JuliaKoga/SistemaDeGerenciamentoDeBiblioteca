package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EmprestimoTeste {

    @Test
    public void devolucaoDoLivroDeveSer30DiasAposSerEmprestado(){
        //Given
        Pessoa pessoa = new Pessoa("Jorge", LocalDate.of(2003,12,11),"jorge@hotmail.com","123");
        Livro livro = new Livro("Livro","Fulano");
        //When
        Emprestimo emprestimo = new Emprestimo(pessoa,livro);
        //Then
        Assertions.assertEquals(emprestimo.getDevolucao(),LocalDate.now().plusDays(30));
    }
}
