package com.project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.demo.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
