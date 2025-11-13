package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoService service;
    
    // CREATE - POST http://localhost:8080/api/produtos
    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
        Produto novoProduto = service.criar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }
    
    // READ ALL - GET http://localhost:8080/api/produtos
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        List<Produto> produtos = service.listarTodos();
        return ResponseEntity.ok(produtos);
    }
    
    // READ ONE - GET http://localhost:8080/api/produtos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    // UPDATE - PUT http://localhost:8080/api/produtos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, 
                                             @RequestBody Produto produto) {
        try {
            Produto produtoAtualizado = service.atualizar(id, produto);
            return ResponseEntity.ok(produtoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // DELETE - DELETE http://localhost:8080/api/produtos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}