package com.jessicapeixe.cidade.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.jessicapeixe.cidade.controller.CidadeRestController;
import com.jessicapeixe.cidade.model.Cidade;
import com.jessicapeixe.cidade.resource.CidadeResource;

public class CidadeResourceAssembler extends ResourceAssemblerSupport<Cidade, CidadeResource> {

    public CidadeResourceAssembler() {
        super(Cidade.class, CidadeResource.class);
    }   

    @Override
    protected CidadeResource instantiateResource(Cidade cidade) {
        return new CidadeResource(cidade);
    }

    @Override
    public CidadeResource toResource(Cidade cidade) {
        return new CidadeResource(cidade, linkTo(methodOn(CidadeRestController.class).get(cidade.getIbge_id())).withSelfRel());
    }
    
}