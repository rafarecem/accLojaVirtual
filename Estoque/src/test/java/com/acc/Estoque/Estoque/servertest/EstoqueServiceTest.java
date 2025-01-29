package com.acc.Estoque.Estoque.servertest;

import com.acc.Estoque.Estoque.dto.EstoqueDTO;
import com.acc.Estoque.Estoque.model.Estoque;
import com.acc.Estoque.Estoque.repository.EstoqueRepository;
import com.acc.Estoque.Estoque.service.EstoqueService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EstoqueServiceTest {
    /*
    @InjectMocks
    private EstoqueService estoqueService;

    @Mock
    private EstoqueRepository estoqueRepository;

    @Test
    void criarEstoque_DeveSalvarEretornarEstoqueDTO() {
        // Arrange
        EstoqueDTO estoqueDTO = new EstoqueDTO();
        estoqueDTO.setNomeProduto("Produto Teste");
        estoqueDTO.setQuantidadeDisponivel(50);
        estoqueDTO.setQuantidadeMinima(10);

        Estoque estoque = new Estoque();
        estoque.setNomeProduto("Produto Teste");
        estoque.setQuantidadeDisponivel(50);
        estoque.setQuantidadeMinima(10);

        Estoque estoqueSalvo = new Estoque();
        estoqueSalvo.setId(1L);
        estoqueSalvo.setNomeProduto("Produto Teste");
        estoqueSalvo.setQuantidadeDisponivel(50);
        estoqueSalvo.setQuantidadeMinima(10);
        estoqueSalvo.setDataAtualizacao(LocalDateTime.now());

        Mockito.when(estoqueRepository.save(Mockito.any(Estoque.class))).thenReturn(estoqueSalvo);

        // Act
        EstoqueDTO resultado = estoqueService.criarEstoque(estoqueDTO);

        // Assert
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("Produto Teste", resultado.getNomeProduto());
        Assertions.assertEquals(50, resultado.getQuantidadeDisponivel());
        Assertions.assertEquals(10, resultado.getQuantidadeMinima());
        Mockito.verify(estoqueRepository, Mockito.times(1)).save(Mockito.any(Estoque.class));
    }

    @Test
    void atualizarEstoque_DeveAtualizarEretornarEstoqueDTO() {
        // Arrange
        EstoqueDTO estoqueDTO = new EstoqueDTO();
        estoqueDTO.setProdutoId(1L);
        estoqueDTO.setQuantidadeDisponivel(20);

        Estoque estoqueExistente = new Estoque();
        estoqueExistente.setId(1L);
        estoqueExistente.setNomeProduto("Produto Existente");
        estoqueExistente.setQuantidadeDisponivel(30);
        estoqueExistente.setQuantidadeMinima(10);

        Estoque estoqueAtualizado = new Estoque();
        estoqueAtualizado.setId(1L);
        estoqueAtualizado.setNomeProduto("Produto Existente");
        estoqueAtualizado.setQuantidadeDisponivel(50); // Atualização
        estoqueAtualizado.setQuantidadeMinima(10);
        estoqueAtualizado.setDataAtualizacao(LocalDateTime.now());

        Mockito.when(estoqueRepository.findById(1L)).thenReturn(Optional.of(estoqueExistente));
        Mockito.when(estoqueRepository.save(Mockito.any(Estoque.class))).thenReturn(estoqueAtualizado);

        // Act
        EstoqueDTO resultado = estoqueService.atualizarEstoque(estoqueDTO);

        // Assert
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(50, resultado.getQuantidadeDisponivel());
        Mockito.verify(estoqueRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(estoqueRepository, Mockito.times(1)).save(Mockito.any(Estoque.class));
    }

    @Test
    void buscarEstoque_DeveRetornarEstoqueDTO_SeEncontrado() {
        // Arrange
        Estoque estoque = new Estoque();
        estoque.setId(1L);
        estoque.setNomeProduto("Produto Teste");
        estoque.setQuantidadeDisponivel(100);
        estoque.setQuantidadeMinima(20);

        Mockito.when(estoqueRepository.findById(1L)).thenReturn(Optional.of(estoque));

        // Act
        EstoqueDTO resultado = estoqueService.buscarEstoque(1L);

        // Assert
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("Produto Teste", resultado.getNomeProduto());
        Mockito.verify(estoqueRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void buscarEstoque_DeveLancarExcecao_SeNaoEncontrado() {
        // Arrange
        Mockito.when(estoqueRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            estoqueService.buscarEstoque(1L);
        });

        Assertions.assertEquals("Estoque não encontrado", exception.getMessage());
        Mockito.verify(estoqueRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void deletarEstoque_DeveDeletar_SeExistir() {
        // Arrange
        Mockito.when(estoqueRepository.existsById(1L)).thenReturn(true);

        // Act
        estoqueService.deletarEstoque(1L);

        // Assert
        Mockito.verify(estoqueRepository, Mockito.times(1)).existsById(1L);
        Mockito.verify(estoqueRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void deletarEstoque_DeveLancarExcecao_SeNaoExistir() {
        // Arrange
        Mockito.when(estoqueRepository.existsById(1L)).thenReturn(false);

        // Act & Assert
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            estoqueService.deletarEstoque(1L);
        });

        Assertions.assertEquals("Estoque não encontrado", exception.getMessage());
        Mockito.verify(estoqueRepository, Mockito.times(1)).existsById(1L);
    }
*/
}
