package service;

import model.Biblioteca;
import model.Emprestimo;
import model.Livro;
import model.Pessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmprestimoServiceTeste {
    EmprestimoService emprestimoService = new EmprestimoService();
    Pessoa pessoa;
    Pessoa pessoa1;
    Livro livro;
    Emprestimo emprestimo;
    Emprestimo emprestimo1;
    Biblioteca biblioteca = new Biblioteca();

    @BeforeEach
    public void setup(){
        pessoa = new Pessoa("Ana", LocalDate.of(2000, 4, 1),"ana123@hotmail.com","911111111");
        pessoa1 = new Pessoa("Lia", LocalDate.of(1998, 7, 23),"lia123@hotmail.com","911111111");
        livro = new Livro("Senhor dos Anéis","J. R. R. Tolkien");
        emprestimo = null;
        emprestimo1 = new Emprestimo(pessoa, livro);
    }

    @Test
    public void deveCriarEmprestimoCorretamente() throws Exception{
        //Given
        //When
        emprestimoService.emprestarLivro(livro,pessoa,biblioteca);
        emprestimo = emprestimoService.buscarEmprestimo(livro,pessoa);
        //Then
        assertEquals(emprestimo.getPessoa(),pessoa);
        assertEquals(emprestimo.getLivro(),livro);
        assertEquals(emprestimo.getDevolucao(),LocalDate.now().plusDays(30));
    }

    @Test
    public void deveCadastrarEmprestimoEmPessoaCorretamente() throws Exception{
        //Given
        //When
        emprestimoService.emprestarLivro(livro,pessoa,biblioteca);
        for (Emprestimo emprestimo1: pessoa.getEmprestimos()) {
            if (emprestimo1.getLivro().equals(livro)) {
                emprestimo = emprestimo1;
                break;
            }
        }
        //Then
        assertEquals(emprestimo.getPessoa(),pessoa);
        assertEquals(emprestimo.getLivro(),livro);
        assertEquals(emprestimo.getDevolucao(),LocalDate.now().plusDays(30));
    }

    @Test
    public void deveCadastrarEmprestimoEmLivroCorretamente() throws Exception{
        //Given
        //When
        emprestimoService.emprestarLivro(livro,pessoa,biblioteca);
        for (Emprestimo emprestimo1: livro.getEmprestimos()) {
            if (emprestimo1.getPessoa().equals(pessoa)) {
                emprestimo = emprestimo1;
                break;
            }
        }
        //Then
        assertEquals(emprestimo.getPessoa(),pessoa);
        assertEquals(emprestimo.getLivro(),livro);
        assertEquals(emprestimo.getDevolucao(),LocalDate.now().plusDays(30));
    }

    @Test
    public void deveCadastrarEmprestimoEmBibliotecaCorretamente() throws Exception{
        //Given
        //When
        emprestimoService.emprestarLivro(livro,pessoa,biblioteca);
        for (Emprestimo emprestimo1: biblioteca.getEmprestimos()) {
            if (emprestimo1.getLivro().equals(livro)) {
                emprestimo = emprestimo1;
                break;
            }
        }
        //Then
        assertEquals(emprestimo.getPessoa(),pessoa);
        assertEquals(emprestimo.getLivro(),livro);
        assertEquals(emprestimo.getDevolucao(),LocalDate.now().plusDays(30));
    }

    @Test
    public void deveLancarExcecaoSeLivroNaoEstiverDisponivel() throws Exception{
        //Given
        //When
        livro.setDisponivel(false);
        //Then
        Throwable throwable = Assertions.assertThrows(
                Exception.class, ()-> emprestimoService.emprestarLivro(livro,pessoa,biblioteca));
        assertEquals("O livro "+ livro.getNome() +" não está disponível.",throwable.getMessage());
    }

    @Test
    public void deveBuscarEmprestimoCorretamente()throws Exception{
        //Given
        livro.adicionarEmprestimo(new Emprestimo(pessoa, livro));

        //When
        Emprestimo emprestimo = emprestimoService.buscarEmprestimo(livro, pessoa);

        //Then
        assertEquals(emprestimo.getPessoa(),pessoa);
        assertEquals(emprestimo.getLivro(),livro);
        assertEquals(emprestimo.getDevolucao(),LocalDate.now().plusDays(30));
    }

    @Test
    public void deveBuscarEmprestimoInexistenteERetornarNulo()throws Exception{
        //Given
        livro.adicionarEmprestimo(new Emprestimo(pessoa,livro));
        //When
        emprestimo = emprestimoService.buscarEmprestimo(livro,pessoa1);
        //Then
        Assertions.assertNull(emprestimo);
    }

    @Test
    public void deveRemoverEmprestimoDeLivroCorretamenteAoDevolverLivro()throws Exception {
        //Given
        emprestimoService.emprestarLivro(livro,pessoa,biblioteca);
        //When
        emprestimoService.devolverLivro(livro,pessoa,biblioteca);
        //Then
        for (Emprestimo e : livro.getEmprestimos()) {
            if (e.equals(emprestimo1)) {
                emprestimo=e;
            }
        }
        Assertions.assertNull(emprestimo);
    }

    @Test
    public void deveRemoverEmprestimoDePessoaCorretamenteAoDevolverLivro()throws Exception {
        //Given
        emprestimoService.emprestarLivro(livro,pessoa,biblioteca);
        //When
        emprestimoService.devolverLivro(livro,pessoa,biblioteca);
        //Then
        for (Emprestimo e : pessoa.getEmprestimos()) {
            if (e.equals(emprestimo1)) {
                emprestimo=e;
            }
        }
        Assertions.assertNull(emprestimo);
    }

    @Test
    public void deveRemoverEmprestimoDeBibliotecaCorretamenteAoDevolverLivro()throws Exception {
        //Given
        emprestimoService.emprestarLivro(livro,pessoa,biblioteca);
        //When
        emprestimoService.devolverLivro(livro,pessoa,biblioteca);
        //Then
        for (Emprestimo e : biblioteca.getEmprestimos()) {
            if (e.equals(emprestimo1)) {
                emprestimo=e;
            }
        }
        Assertions.assertNull(emprestimo);
    }

    @Test
    public void deveDisponibilizarOLivroDepoisDeDevolvido()throws Exception{
        //Given
        emprestimoService.emprestarLivro(livro,pessoa,biblioteca);
        //When
        emprestimoService.devolverLivro(livro,pessoa,biblioteca);
        //Then
        Assertions.assertTrue(livro.isDisponivel());
    }

    @Test
    public void deveLancarExcecaoAoTentarDevolverLivroQueNaoFoiEmprestado()throws Exception{
        //Given
        //When
        //Then
        Throwable throwable = Assertions.assertThrows(
                Exception.class, ()-> emprestimoService.devolverLivro(livro,pessoa,biblioteca));
        assertEquals("Emprestimo não encontrado.",throwable.getMessage());
    }

    @Test
    public void deveRenovarEmprestimoCorretamente()throws Exception{
        //Given
        emprestimoService.emprestarLivro(livro,pessoa,biblioteca);
        emprestimo = emprestimoService.buscarEmprestimo(livro,pessoa);
        emprestimo.setDevolucao(LocalDate.of(2023, 4, 10));
        System.out.println(emprestimo.getDevolucao());
        //When
        emprestimoService.renovarEmprestimo(livro,pessoa);
        //Then
        assertEquals(emprestimo.getDevolucao(),LocalDate.now().plusDays(30));
    }

    @Test
    public void deveLancarExcecaoAoTentarRenovarEmprestimoInexistente()throws Exception{
        //Given
        //When
        //Then
        Throwable throwable = Assertions.assertThrows(
                Exception.class, ()-> emprestimoService.renovarEmprestimo(livro,pessoa));
        assertEquals("Emprestimo não encontrado.",throwable.getMessage());
    }

    @Test
    public void deveVerificarEmprestimosAtrasados()throws Exception{
        //Given
        List<Emprestimo> emprestimosAtrasados;
        emprestimoService.emprestarLivro(livro,pessoa,biblioteca);
        emprestimo = emprestimoService.buscarEmprestimo(livro,pessoa);
        emprestimo.setDevolucao(LocalDate.of(2023, 3, 10));
        //When
        emprestimosAtrasados = emprestimoService.verificarEmprestimosAtrasados(biblioteca);
        //Then
        List<Emprestimo> emprestimos = new ArrayList<>();
        emprestimos.add(emprestimo);

        Assertions.assertEquals(emprestimos,emprestimosAtrasados);
    }

}
