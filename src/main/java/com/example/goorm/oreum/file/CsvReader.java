package com.example.goorm.oreum.file;

import com.example.goorm.oreum.dto.CsvReaderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;

@Configuration
@RequiredArgsConstructor
public class CsvReader {
    @Bean
    public FlatFileItemReader csvFileItemReader() {
        // FlatFileItemReader - DB가 아닌 Resource에서 데이터를 읽어오도록 구현된 구현체
        return new FlatFileItemReaderBuilder<CsvReaderDto>()
                .name("CsvReader")
                .resource(new PathResource("C:\\Users\\Windows10\\Desktop\\오름.txt"))
                .delimited() // 한 라인에서 각각의 컬럼을 어떤 구분자로 구분할 건지
                .names("name", "type", "pos")
                .targetType(CsvReaderDto.class)
                .build();
    }
}