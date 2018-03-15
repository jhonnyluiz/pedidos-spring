package com.jlcabral.pedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jlcabral.pedidos.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
