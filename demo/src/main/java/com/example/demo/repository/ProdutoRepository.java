package com.example.demo.repository;

import com.example.demo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // JpaRepository já fornece os métodos CRUD básicos:
    // save(), findById(), findAll(), deleteById(), etc.
}