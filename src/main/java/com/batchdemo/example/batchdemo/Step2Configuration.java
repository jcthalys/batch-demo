package com.batchdemo.example.batchdemo;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import javax.sql.DataSource;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * Created by Thalys Gomes on Mar, 2018
 */
@Configuration
public class Step2Configuration {

  @Bean
  ItemReader<Map<Integer, Integer>> jdbcReader(DataSource dataSource) {
    return new JdbcCursorItemReaderBuilder<Map<Integer, Integer>>()
        .dataSource(dataSource)
        .name("jdbc-reader")
        .sql("select COUNT(age) c, age a from PEOPLE group by age")
        .rowMapper((rs, rowNum) -> Collections.singletonMap(rs.getInt("c"), rs.getInt("a")))
        .saveState(true)
        .build();
  }

  @Bean
  ItemWriter<Map<Integer, Integer>> fileWriter(@Value("${output}") Resource resource) {
    return new FlatFileItemWriterBuilder<Map<Integer, Integer>>()
        .name("file-writer")
        .resource(resource)
        .lineAggregator(new DelimitedLineAggregator<Map<Integer, Integer>>() {
          {
            setDelimiter(",");
            setFieldExtractor(item -> {
              Entry<Integer, Integer> next = item.entrySet().iterator().next();
              return new Object[]{next.getKey(), next.getValue()};
            });
          }
        })
        .shouldDeleteIfExists(true)
        .saveState(true)
        .build();
  }
}
