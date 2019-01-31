package com.jessicapeixe.cidade;

import java.io.File;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.springframework.core.io.ClassPathResource;

class FileReader {
    public <T> List<T> GetContentFile(Class<T> type, String fileName) {
        
        Logger logger = LoggerFactory.getLogger(this.getClass());

        try {
            CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(',');
            CsvMapper mapper = new CsvMapper();
            File file = new ClassPathResource(fileName).getFile();

            MappingIterator<T> conteudo =  mapper.reader(type).with(schema).readValues(file);

            return conteudo.readAll();

        } 
        catch (Exception e) {
            return Collections.emptyList();
        }
    }
}   