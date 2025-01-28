package com.acc.Estoque.Estoque.service;


import com.acc.Estoque.Estoque.dto.EstoqueDTO;
import com.acc.Estoque.Estoque.model.Estoque;
import com.acc.Estoque.Estoque.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository estoqueRepository;

    public EstoqueDTO criarEstoque(EstoqueDTO estoqueDTO) {
        Estoque estoque = new Estoque();
        estoque.setNomeProduto(estoqueDTO.getNomeProduto());
        estoque.setQuantidadeDisponivel(estoqueDTO.getQuantidadeDisponivel());
        estoque.setQuantidadeMinima(estoqueDTO.getQuantidadeMinima());
        estoque.setDataAtualizacao(LocalDateTime.now());
        Estoque estoqueSalvo = estoqueRepository.save(estoque);
        return toDTO(estoqueSalvo);
    }

    public EstoqueDTO atualizarEstoque(EstoqueDTO estoqueDTO) {
        Optional<Estoque> optionalEstoque = estoqueRepository.findById(estoqueDTO.getProdutoId());
        Estoque estoque;
        if (optionalEstoque.isPresent()) {
            estoque = optionalEstoque.get();
            estoque.setQuantidadeDisponivel(estoque.getQuantidadeDisponivel() + estoqueDTO.getQuantidadeDisponivel());
        } else {
            estoque = new Estoque();
            estoque.setNomeProduto(estoqueDTO.getNomeProduto());
            estoque.setQuantidadeDisponivel(estoqueDTO.getQuantidadeDisponivel());
            estoque.setQuantidadeMinima(estoqueDTO.getQuantidadeMinima() != null ? estoqueDTO.getQuantidadeMinima() : 10);
        }
        estoque.setDataAtualizacao(LocalDateTime.now());
        Estoque estoqueSalvo = estoqueRepository.save(estoque);

        return toDTO(estoqueSalvo);
    }

    private EstoqueDTO toDTO(Estoque estoque) {
        EstoqueDTO dto = new EstoqueDTO();
        dto.setNomeProduto(estoque.getNomeProduto());
        dto.setQuantidadeDisponivel(estoque.getQuantidadeDisponivel());
        dto.setQuantidadeMinima(estoque.getQuantidadeMinima());
        dto.setDataAtualizacao(estoque.getDataAtualizacao());
        return dto;
    }

    public EstoqueDTO buscarEstoque(Long id) {
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
        return toDTO(estoque);
    }

    public EstoqueDTO editarEstoque(Long id, EstoqueDTO estoqueDTO) {
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

        estoque.setNomeProduto(estoqueDTO.getNomeProduto());
        estoque.setQuantidadeDisponivel(estoqueDTO.getQuantidadeDisponivel());
        estoque.setQuantidadeMinima(estoqueDTO.getQuantidadeMinima());
        estoque.setDataAtualizacao(LocalDateTime.now());

        Estoque estoqueSalvo = estoqueRepository.save(estoque);
        return toDTO(estoqueSalvo);
    }

    public void deletarEstoque(Long id) {
        if (!estoqueRepository.existsById(id)) {
            throw new RuntimeException("Estoque não encontrado");
        }
        estoqueRepository.deleteById(id);
    }
}
