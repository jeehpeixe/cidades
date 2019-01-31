package com.jessicapeixe.cidade.repository;

import java.util.List;

import com.jessicapeixe.cidade.model.Cidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(exported = false)
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

    static final String estadoMenorQtd = "(SELECT COUNT(1) QTDE, UF FROM CIDADES GROUP BY UF ORDER BY QTDE ASC LIMIT 1)";
    static final String estadoMaiorQtd = "(SELECT COUNT(1) QTDE, UF FROM CIDADES GROUP BY UF ORDER BY QTDE DESC LIMIT 1)";

    static final String cidadeMaisDistante = ""+
    
        "(SELECT CAST(((40030 * ((180 / Pi()) * Acos(((Cos(((90 - B.LAT) * (Pi() / 180))) * Cos((90 - A.LAT) * (Pi() / 180))) + (Sin(((90 - B.LAT) * (Pi() / 180))) * Sin((90 - A.LAT) * (Pi() / 180)) * Cos(((B.LON - A.LON) * (Pi() / 180)))))))) /360) AS INTEGER) DISTANCIA,"+
        "       A.NAME ORIGEM,"+
        "       B.NAME DESTINO"+
        "  FROM CIDADES A"+
        "  JOIN CIDADES B "+
        "    ON 1 = 1"+
        " ORDER BY DISTANCIA DESC"+
        " LIMIT 1)";

    List<Cidade> findByUfContaining(@Param("uf") String uf);

    List<Cidade> findByCapitalTrueOrderByName();

    @Query(value = "SELECT COUNT(1) AS QTDE, UF FROM CIDADES GROUP BY UF", nativeQuery = true)
    List<Object[]> getQuantidadePorEstado();

    @Query(value = estadoMenorQtd + " UNION ALL " + estadoMaiorQtd, nativeQuery = true)
    List<Object[]> getMaiorMenorEstado();

    @Query(value = cidadeMaisDistante, nativeQuery = true)
    List<Object[]> getCidadeMaisDistante();

    @Query(value = "SELECT * FROM CIDADES WHERE UPPER(:coluna) LIKE CONCAT('%',UPPER(:valor),'%')", nativeQuery = true)
    List<Cidade> findByColunaTexto(@Param("coluna") String coluna, @Param("valor") String valor);


}