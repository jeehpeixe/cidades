
package com.jessicapeixe.cidade.controller;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.jessicapeixe.cidade.model.Cidade;

import org.springframework.data.jpa.domain.Specification;

class CidadeSpecification implements Specification<Cidade> {

    private String randomColumnName;
    private String valueToSearchFor;

    public CidadeSpecification(String randomColumnName, String valueToSearchFor) {
        this.randomColumnName = randomColumnName;
        this.valueToSearchFor = valueToSearchFor;
    }

    @Override
    public Predicate toPredicate(Root<Cidade> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.and(builder.equal(root.<String>get(this.randomColumnName), this.valueToSearchFor));
    }
}