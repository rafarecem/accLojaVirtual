package com.acc.Vendedor.Vendedor.testeVendedor;

import com.acc.Vendedor.Vendedor.dto.VendedorDTO;
import com.acc.Vendedor.Vendedor.exception.InvalidVendedorException;
import com.acc.Vendedor.Vendedor.exception.VendedorNotFoundException;
import com.acc.Vendedor.Vendedor.model.Vendedor;
import com.acc.Vendedor.Vendedor.repository.VendedorRepository;
import com.acc.Vendedor.Vendedor.service.VendedorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VendedorServiceTest {

    @Mock
    private VendedorRepository vendedorRepository;

    @InjectMocks
    private VendedorService vendedorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveVendedor_Success() {
        VendedorDTO vendedorDTO = new VendedorDTO();
        vendedorDTO.setNomeVendedor("João");
        vendedorDTO.setSetorVendedor("Comercial");

        Vendedor vendedor = new Vendedor();
        vendedor.setNomeVendedor("João");
        vendedor.setSetorVendedor("Comercial");

        Vendedor savedVendedor = new Vendedor();
        savedVendedor.setIdVendedor(1);
        savedVendedor.setNomeVendedor("João");
        savedVendedor.setSetorVendedor("Comercial");

        when(vendedorRepository.save(any(Vendedor.class))).thenReturn(savedVendedor);

        VendedorDTO result = vendedorService.saveVendedor(vendedorDTO);

        assertNotNull(result);
        assertEquals(1, result.getIdVendedor());
        assertEquals("João", result.getNomeVendedor()); // Corrigido
        assertEquals("Comercial", result.getSetorVendedor());

        verify(vendedorRepository, times(1)).save(any(Vendedor.class));
    }

    @Test
    void testGetAllVendedores() {
        Vendedor vendedor1 = new Vendedor();
        vendedor1.setIdVendedor(1);
        vendedor1.setNomeVendedor("João");
        vendedor1.setSetorVendedor("Comercial");

        Vendedor vendedor2 = new Vendedor();
        vendedor2.setIdVendedor(2);
        vendedor2.setNomeVendedor("Maria");
        vendedor2.setSetorVendedor("Financeiro");

        when(vendedorRepository.findAll()).thenReturn(Arrays.asList(vendedor1, vendedor2));

        List<Vendedor> result = vendedorService.getAllVendedores();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(vendedorRepository, times(1)).findAll();
    }

    @Test
    void testGetVendedorById_Success() {
        Vendedor vendedor = new Vendedor();
        vendedor.setIdVendedor(1);
        vendedor.setNomeVendedor("Patricia");
        vendedor.setSetorVendedor("Comercial");

        when(vendedorRepository.findById(1)).thenReturn(Optional.of(vendedor));

        VendedorDTO result = vendedorService.getVendedorById(1);

        assertNotNull(result);
        assertEquals(1, result.getIdVendedor());
        assertEquals("Patricia", result.getNomeVendedor());
        assertEquals("Comercial", result.getSetorVendedor());

        verify(vendedorRepository, times(1)).findById(1);
    }

    @Test
    void testGetVendedorById_NotFound() {
        when(vendedorRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(VendedorNotFoundException.class, () -> vendedorService.getVendedorById(1));

        verify(vendedorRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteVendedor() {
        doNothing().when(vendedorRepository).deleteById(1);

        vendedorService.deleteVendedor(1);

        verify(vendedorRepository, times(1)).deleteById(1);
    }

}
