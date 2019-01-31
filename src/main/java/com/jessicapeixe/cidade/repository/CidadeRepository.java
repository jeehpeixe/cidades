package com.jessicapeixe.cidade.repository;

import java.util.List;

import com.jessicapeixe.cidade.model.Cidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    List<Cidade> findByUfContaining(@Param("uf") String uf);

    List<Cidade> findByCapitalTrueOrderByName();

    //@Query(value = "SELECT COUNT(1) AS QTDE, UF FROM CIDADES GROUP BY ESTADO", nativeQuery = true)
    //List<Object[]> getQuantidadePorEstado();


}