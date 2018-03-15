package com.jlcabral.pedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jlcabral.pedidos.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
