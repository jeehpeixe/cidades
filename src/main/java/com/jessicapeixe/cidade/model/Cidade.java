package com.jessicapeixe.cidade.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "cidades")
public class Cidade {

    @Id
    Long ibge_id;
    String uf;
    String name;
    Boolean capital;
    Double lon;
    Double lat;
    String no_accents;
    String alternative_names;
    String microregion;
    String mesoregion;
}