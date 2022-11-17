//package com.example.goorm.oreum;
//
//import com.example.goorm.oreum.repository.OreumRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@RequiredArgsConstructor
//public class CsvWriter implements ItemWriter<Oreum> {
//
//    private final OreumRepository oreumRepository;
//
//    @Override
//    public void write(List<? extends Oreum> list) throws Exception {
//        oreumRepository.saveAll(new ArrayList<Oreum>(list));
//    }
//}