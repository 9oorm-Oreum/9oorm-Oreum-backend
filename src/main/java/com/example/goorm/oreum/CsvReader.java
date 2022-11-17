//package com.example.goorm.oreum;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//
//@Configuration
//@RequiredArgsConstructor
//public class CsvReader {
//    @Bean
//    public FlatFileItemReader<String> csvFileItemReader() {
//        /* file read */
//        FlatFileItemReader<String> flatFileItemReader = new FlatFileItemReader<>();
//        flatFileItemReader.setResource(new ClassPathResource("C:\\Users\\Windows10\\Desktop\\오름.txt"));
//        flatFileItemReader.setEncoding("UTF-8");
//        return flatFileItemReader;
//    }
//}