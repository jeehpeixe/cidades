package com.jessicapeixe.cidade.resource;

import com.jessicapeixe.cidade.model.Cidade;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class CidadeResource extends Resource<Cidade> {
    
    public CidadeResource(Cidade content, Link... links) {
        super(content, links);
    }
    
}