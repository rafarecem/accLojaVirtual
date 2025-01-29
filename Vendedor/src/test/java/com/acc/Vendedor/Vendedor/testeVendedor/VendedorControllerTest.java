package com.acc.Vendedor.Vendedor.controller;

import com.acc.Vendedor.Vendedor.dto.VendedorDTO;
import com.acc.Vendedor.Vendedor.model.Vendedor;
import com.acc.Vendedor.Vendedor.service.VendedorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VendedorController.class)
public class VendedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendedorService vendedorService;

    @Autowired
    private ObjectMapper objectMapper;



    @Test
    void testObjectMapper() throws Exception {
        VendedorDTO vendedorDTO = new VendedorDTO();
        vendedorDTO.setNomeVendedor("João");
        vendedorDTO.setSetorVendedor("Comercial");

        String json = objectMapper.writeValueAsString(vendedorDTO);
        System.out.println(json);

        assert json.contains("João");
        assert json.contains("Comercial");
    }



}
