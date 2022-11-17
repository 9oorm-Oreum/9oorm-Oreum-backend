package com.example.goorm;

import com.example.goorm.oreum.file.CsvReader;
import com.example.goorm.oreum.file.CsvWriter;
import com.example.goorm.oreum.domain.Oreum;
import com.example.goorm.oreum.dto.CsvReaderDto;
import com.example.goorm.oreum.repository.OreumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @Bean
    public ItemProcessor<CsvReaderDto, Oreum> csvProcessor() {
        return csvReaderDto -> {
            String name = csvReaderDto.getName();
            if(!name.contains("오름"))
                name += "오름";
            String type = csvReaderDto.getType().split("/")[1];
            String pos = csvReaderDto.getPos();
            double x = Double.parseDouble(pos.split(" ")[0]);
            double y = Double.parseDouble(pos.split(" ")[1]);
            double z = Double.parseDouble(pos.split(" ")[2]);

            ArrayList<Integer> stickers = new ArrayList<Integer>(Arrays.asList(1,2,3,4));

            int left = (int)(Math.random()*5); // 0~4 (스티커 안붙이는 경우 고려)
            int right;
            if(left == 0) // 왼쪽이 빈 스티커인 경우, 무조건 오른쪽 스티커는 있도록
                right = (int)(Math.random()*4)+1;
            else { // 왼쪽이 빈 스티커가 아닌 경우, 오른쪽은 걔랑 다른 숫자
                stickers.remove((Integer) left);
                right = stickers.get((int)(Math.random()*3)); // 0~2 중에 하나
            }

            Oreum oreum = Oreum.builder()
                    .name(name)
                    .xPos(x)
                    .yPos(y)
                    .zPos(z)
                    .month(monthCheck)
                    .day(dayCheck)
                    .leftPos(left)
                    .rightPos(right)
                    .build();

            oreum.toTypeEnum(type);
            oreumRepository.save(oreum);

            if(((monthCheck<=7 && monthCheck%2==1) || (monthCheck>=8 && monthCheck%2==0))
                    && dayCheck == 31) {
                monthCheck++;
                dayCheck = 0;
            }
            else if(monthCheck == 2 && dayCheck == 29){
                monthCheck++;
                dayCheck = 0;
            }
            else if((monthCheck == 4 || monthCheck == 6 || monthCheck == 9 || monthCheck == 11)
                    && dayCheck == 30){
                monthCheck++;
                dayCheck = 0;
            }
            dayCheck++;
            return oreum;
        };
    }
}
