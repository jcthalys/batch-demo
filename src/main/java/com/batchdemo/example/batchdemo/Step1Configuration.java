package com.batchdemo.example.batchdemo;

import javax.sql.DataSource;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * Created by Thalys Gomes on Mar, 2018
 */
@Configuration
public class Step1Configuration {

  @Bean
  FlatFileItemReader<Person> fileReader(@Value("${input}") Resource in) {
    return new FlatFileItemReaderBuilder<Person>()
        .name("file-reader")
        .resource(in)
        .targetType(Person.class)
        .delimited().delimiter(",").names(new String[]{"firstName", "age", "email"})
        .linesToSkip(1)
        .build();
  }

  @Bean
  JdbcBatchItemWriter<Person> jdbcWriter(DataSource ds) {
    return new JdbcBatchItemWriterBuilder<Person>()
        .dataSource(ds)
        .sql("insert into PEOPLE (FIRST_NAME, AGE, EMAIL) VALUES (:firstName, :age, :email)")
        .beanMapped()
        .build();
  }
}
