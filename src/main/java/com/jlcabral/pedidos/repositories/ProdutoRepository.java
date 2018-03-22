package com.jlcabral.pedidos.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jlcabral.pedidos.domain.Categoria;
import com.jlcabral.pedidos.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{


	/**
	 * Método criado a partir dos padrões de nome do spring data.
	 * 
	 * @param nome
	 * @param categorias
	 * @param pageRequest
	 * @return {@link Page}
	 */
	@Transactional(readOnly=true)
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
//	Exemplo de método acima informando a query.
//	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
//	Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);


}
