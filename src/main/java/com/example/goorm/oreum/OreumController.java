package com.example.goorm.oreum;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class OreumController {

    private final OreumService oreumService;

    @GetMapping("/oreums")
    public ResponseEntity<List<Oreum>> getData(){
        return ResponseEntity.ok().body(oreumService.getOreums());
    }

    @PostMapping("/oreums")
    public ResponseEntity<String> pushData(){
        oreumService.readCsv();
        return ResponseEntity.ok().body("완성");
    }

    // 생일에 따른 오름 정보 보여주기
    @GetMapping("/oreum")
    public ResponseEntity<Oreum> getOreum(@RequestBody BirtyDayRequest request){
        return ResponseEntity.ok().body(oreumService.getOreum(request));
    }
}
