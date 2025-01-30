package com.acc.Vendedor.Vendedor.service;

import com.acc.Vendedor.Vendedor.dto.VendedorRequest;
import com.acc.Vendedor.Vendedor.dto.VendedorResponse;
import com.acc.Vendedor.Vendedor.exception.VendedorNaoEncontradoException;
import com.acc.Vendedor.Vendedor.model.Vendedor;
import com.acc.Vendedor.Vendedor.repository.VendedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class VendedorServiceTest {

    @Mock
    private VendedorRepository vendedorRepository;

    @InjectMocks
    private VendedorService vendedorService;

    private Vendedor vendedor;
    private VendedorRequest vendedorRequest;
    private VendedorResponse vendedorResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        vendedor = new Vendedor();
        vendedor.setId(1);
        vendedor.setVendedorNome("João");
        vendedor.setVendedorSetor("Vendas");
        vendedor.setSetorVendedor("A");

        vendedorRequest = new VendedorRequest();
        vendedorRequest.setVendedorNome("João");
        vendedorRequest.setVendedorSetor("Vendas");
        vendedorRequest.setSetorVendedor("A");

        vendedorResponse = new VendedorResponse();
        vendedorResponse.setIdVendedor(1);
        vendedorResponse.setVendedorNome("João");
        vendedorResponse.setVendedorSetor("Vendas");
        vendedorResponse.setSetorVendedor("A");
    }

    @Test
    void testCriarVendedor() {
        // Mockando a resposta do repositório
        Mockito.when(vendedorRepository.save(Mockito.any(Vendedor.class))).thenReturn(vendedor);

        VendedorResponse response = vendedorService.criarVendedor(vendedorRequest);

        assertNotNull(response);
        assertEquals("João", response.getVendedorNome());
        assertEquals("Vendas", response.getVendedorSetor());
    }

    @Test
    void testObterVendedorPorId() {
        Mockito.when(vendedorRepository.findById(1)).thenReturn(Optional.of(vendedor));

        VendedorResponse response = vendedorService.obterVendedorPorId(1);

        assertNotNull(response);
        assertEquals(1, response.getIdVendedor());
        assertEquals("João", response.getVendedorNome());
    }

    @Test
    void testObterVendedorPorId_VendedorNaoEncontrado() {
        Mockito.when(vendedorRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(VendedorNaoEncontradoException.class, () -> {
            vendedorService.obterVendedorPorId(1);
        });
    }

    @Test
    void testDeletarVendedor() {

        Mockito.when(vendedorRepository.existsById(1)).thenReturn(true);

        vendedorService.deletarVendedor(1);

        Mockito.verify(vendedorRepository, Mockito.times(1)).deleteById(1);
    }

    @Test
    void testDeletarVendedor_VendedorNaoEncontrado() {
        Mockito.when(vendedorRepository.existsById(1)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> {
            vendedorService.deletarVendedor(1);
        });
    }

    @Test
    void testAtualizarVendedor() {
        Mockito.when(vendedorRepository.findById(1)).thenReturn(Optional.of(vendedor));
        Mockito.when(vendedorRepository.save(Mockito.any(Vendedor.class))).thenReturn(vendedor);

        VendedorResponse response = vendedorService.atualizarVendedor(1, vendedorRequest);

        assertNotNull(response);
        assertEquals("João", response.getVendedorNome());
    }

    @Test
    void testAtualizarVendedor_VendedorNaoEncontrado() {
        Mockito.when(vendedorRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            vendedorService.atualizarVendedor(1, vendedorRequest);
        });
    }
}
