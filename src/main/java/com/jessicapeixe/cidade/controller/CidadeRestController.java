package com.jessicapeixe.cidade.controller;

import java.util.List;

import com.jessicapeixe.cidade.model.Cidade;
import com.jessicapeixe.cidade.repository.CidadeRepository;
import com.jessicapeixe.cidade.resource.CidadeResource;
import com.jessicapeixe.cidade.assembler.CidadeResourceAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cidades")
public class CidadeRestController {
    
    @Autowired
    CidadeRepository repository;

    CidadeResourceAssembler assembler = new CidadeResourceAssembler();
    
    @ApiOperation("Retorna todos as cidades")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<CidadeResource>> getAll() {
        return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
    }
    
    @ApiOperation("Obter os dados da cidade pelo ID do IBGE")
    @GetMapping("/{id}")
    public ResponseEntity<CidadeResource> get(@PathVariable Long id) {
        Cidade cidade = repository.findOne(id);

        if (cidade != null) {
            return new ResponseEntity<>(assembler.toResource(cidade), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation("Adiciona uma nova cidade")
    @PostMapping
    public ResponseEntity<CidadeResource> create(@RequestBody Cidade cidade) {
        cidade = repository.save(cidade);
        if (cidade != null) {
            return new ResponseEntity<>(assembler.toResource(cidade), HttpStatus.OK);
        } 
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ApiOperation("Exclui a cidade do IBGE informado")
    @DeleteMapping("/{id}")
    public ResponseEntity<CidadeResource> delete(@PathVariable Long id) {
        Cidade cidade = repository.findOne(id);
        if (cidade != null) {
            repository.delete(cidade);
            return new ResponseEntity<>(assembler.toResource(cidade), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ApiOperation("Retornar cidades que são capitais ordenadas por nome")
    @GetMapping("/capitais")
    public ResponseEntity<List<CidadeResource>> getAllCapitais() {
        return new ResponseEntity<>(assembler.toResources(repository.findByCapitalTrueOrderByName()), HttpStatus.OK);
    }

    @ApiOperation("Retornar o nome das cidades pelo estado informado")
    @GetMapping("/estado/{uf}")
    public ResponseEntity<List<CidadeResource>> getAllCidadesFromEstado(@PathVariable String uf) {
        return new ResponseEntity<>(assembler.toResources(repository.findByUfContaining(uf)), HttpStatus.OK);
    }

    @ApiOperation("Retornar a quantidade de registros total")
    @GetMapping("/quantidade/total")
    public Integer getQuantidadeTotal() {
        return repository.findAll().size();
    }

    //@ApiOperation("Retornar a quantidade de registros por estado")
    //@GetMapping("/quantidade/estado")
    //public List<Object[]> getQuantidadePorEstado() {
    //    return repository.getQuantidadePorEstado();
    //}
}