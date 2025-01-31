package com.acc.Vendedor.Vendedor.controller;

import com.acc.Vendedor.Vendedor.dto.VendedorRequest;
import com.acc.Vendedor.Vendedor.dto.VendedorResponse;
import com.acc.Vendedor.Vendedor.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendedorResponse criarVendedor(@RequestBody VendedorRequest vendedorRequest) {
        return vendedorService.criarVendedor(vendedorRequest);
    }

    @GetMapping("/{idVendedor}")
    public VendedorResponse obterVendedor(@PathVariable Integer idVendedor) {
        return vendedorService.obterVendedorPorId(idVendedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendedorResponse> atualizarVendedor(@PathVariable Integer id, @RequestBody VendedorRequest vendedorRequest) {
        VendedorResponse vendedorResponse = vendedorService.atualizarVendedor(id, vendedorRequest);
        return ResponseEntity.ok(vendedorResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVendedor(@PathVariable Integer id) {
        vendedorService.deletarVendedor(id);
        return ResponseEntity.noContent().build();
    }
}
