package com.example.demo.service;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository repository;
    
    // CREATE
    public Produto criar(Produto produto) {
        return repository.save(produto);
    }
    
    // READ ALL
    public List<Produto> listarTodos() {
        return repository.findAll();
    }
    
    // READ ONE
    public Optional<Produto> buscarPorId(Long id) {
        return repository.findById(id);
    }
    
    // UPDATE
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        return repository.findById(id)
            .map(produto -> {
                produto.setNome(produtoAtualizado.getNome());
                produto.setDescricao(produtoAtualizado.getDescricao());
                produto.setPreco(produtoAtualizado.getPreco());
                produto.setQuantidade(produtoAtualizado.getQuantidade());
                return repository.save(produto);
            })
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
    
    // DELETE
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}