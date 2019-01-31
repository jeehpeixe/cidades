package com.jessicapeixe.cidade;

import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;

import com.jessicapeixe.cidade.model.Cidade;
import com.jessicapeixe.cidade.repository.CidadeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;

@SpringBootApplication
@Import(SpringDataRestConfiguration.class)
public class CidadeApplication {

    @Autowired
	CidadeRepository repositorio;
	
    @PostConstruct
    public void init() throws ParseException {
        int cont = 0;

        FileReader fileReader = new FileReader();

		List<Cidade> cidades = fileReader.GetContentFile(Cidade.class, "cidades.csv");

        for (Cidade cidade : cidades) {
          if (cont > 100) {
            break;
          }
          repositorio.save(cidade);
          cont++;
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(CidadeApplication.class, args);
	}
}