package com.example.goorm.oreum;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class OreumController {

    private final OreumService oreumService;
    private final OreumRepository oreumRepository;

    @GetMapping("/")
    public ResponseEntity<String> pushData(){
        oreumService.readCsv();
        return ResponseEntity.ok().body("완성");
    }

    @GetMapping("/oreum")
    public ResponseEntity<List<Oreum>> getData(){
        return ResponseEntity.ok().body(oreumService.getOreums());
    }
}
