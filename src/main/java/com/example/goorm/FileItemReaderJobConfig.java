package com.example.goorm;

import com.example.goorm.oreum.CsvReader;
import com.example.goorm.oreum.CsvWriter;
import com.example.goorm.oreum.Oreum;
import com.example.goorm.oreum.repository.OreumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class FileItemReaderJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final OreumRepository oreumRepository;
    private final CsvReader csvReader;
    private final CsvWriter csvWriter;

    private static final int chunkSize = 366;

    private static int monthCheck = 1;
    private static int dayCheck = 1;

    // csvFileItemReaderJob 라는 이름의 job 생성
    @Bean
    public Job csvFileItemReaderJob() {
        return jobBuilderFactory.get("csvFileItemReaderJob")
                .start(csvFileItemReaderStep())
                .build();
    }

    @Bean
    public ItemProcessor<String, Oreum> csvProcessor() {
        return oreumStr -> {
            String[] lineArr = oreumStr.split(",");
            String name = lineArr[0];
            String type = lineArr[1].split("/")[1];
            String pos = lineArr[2];
            System.out.println(name+" "+type+" "+pos);
            double x = Double.parseDouble(pos.split(" ")[0]);
            double y = Double.parseDouble(pos.split(" ")[1]);
            double z = Double.parseDouble(pos.split(" ")[2]);

            Oreum oreum = Oreum.builder()
                    .name(name)
                    .xPos(x)
                    .yPos(y)
                    .zPos(z)
                    .month(monthCheck)
                    .day(dayCheck)
                    .build();

            oreum.toTypeEnum(type);
            oreumRepository.save(oreum);
            if((monthCheck == 1 || monthCheck == 3 || monthCheck == 5 || monthCheck == 7
                    || monthCheck == 8 || monthCheck == 10 || monthCheck ==12) && dayCheck == 31) {
                monthCheck++;
                dayCheck = 0;
            }
            else if(monthCheck == 2 && dayCheck == 29){
                monthCheck++;
                dayCheck = 0;
            }
            else if((monthCheck == 4 || monthCheck == 6 || monthCheck ==9 || monthCheck == 11) && dayCheck == 30){
                monthCheck++;
                dayCheck = 0;
            }
            dayCheck++;
            return oreum;
        };
    }

    // csvFileItemReaderStep 이라는 이름의 Step 생성
    @Bean
    public Step csvFileItemReaderStep() {
        return stepBuilderFactory.get("csvFileItemReaderStep")
                .<String, Oreum>chunk(chunkSize)
                // Reader 에서 읽어올 타입 - String, Writer에 넘겨줄 타입이 Oreum
                .reader(csvReader.csvFileItemReader()) // 일단 csv에서 String 읽기
                .processor(csvProcessor()) // String을 가공
                .writer(csvWriter)
                .build();
    }
}
