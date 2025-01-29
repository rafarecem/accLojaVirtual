package com.acc.Vendedor.Vendedor.controller;


import com.acc.Vendedor.Vendedor.dto.VendedorDTO;
import com.acc.Vendedor.Vendedor.model.Vendedor;
import com.acc.Vendedor.Vendedor.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @PostMapping
    public ResponseEntity<VendedorDTO> createVendedor(@RequestBody VendedorDTO vendedorDTO) {
        VendedorDTO createdVendedor = vendedorService.saveVendedor(vendedorDTO);
        return new ResponseEntity<>(createdVendedor, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Vendedor> getAllVendedores() {
        return vendedorService.getAllVendedores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendedorDTO> getVendedorById(@PathVariable int id) {
        VendedorDTO vendedorDTO = vendedorService.getVendedorById(id);
        if (vendedorDTO != null) {
            return new ResponseEntity<>(vendedorDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendedor(@PathVariable int id) {
        vendedorService.deleteVendedor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

