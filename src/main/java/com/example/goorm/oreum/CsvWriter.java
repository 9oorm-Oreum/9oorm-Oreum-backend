//package com.example.goorm.oreum;
//
//import com.example.goorm.oreum.repository.OreumRepository;
//import com.example.goorm.oreum.repository.TestRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@RequiredArgsConstructor
//public class CsvWriter implements ItemWriter<Test> {
////    private final OreumRepository oreumRepository;
//
//    private final TestRepository testRepository;
//
//    @Override
//    public void write(List<? extends Test> list) throws Exception {
//        testRepository.saveAll(new ArrayList<Test>(list));
//    }
//}