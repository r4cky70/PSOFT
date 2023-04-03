package com.ufcg.psoft.mercadofacil.repository;

import com.ufcg.psoft.mercadofacil.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    T save(T produto);
    T find(ID id);
    List<T> findAll();
    T update(T produto);
    void delete(T produto);
    void deleteAll();
}

