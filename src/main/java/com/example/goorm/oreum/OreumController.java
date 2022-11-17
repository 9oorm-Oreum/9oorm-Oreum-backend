package com.example.goorm.oreum;

import com.example.goorm.oreum.dto.BirthDayRequest;
import com.example.goorm.oreum.dto.OreumResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class OreumController {

    private final OreumService oreumService;

    @PostMapping("/oreums")
    public ResponseEntity<String> pushData(){
        oreumService.readCsv();
        return ResponseEntity.ok().body("DB 푸시 완료");
    }

    // 생일에 따른 오름 정보 보여주기 (입력하고 디비에 저장하는 거까지)
    @PostMapping("/oreum")
    public ResponseEntity<OreumResponse> getOreum(@RequestBody BirthDayRequest request){
        return ResponseEntity.ok().body(oreumService.getOreum(request));
    }

    // 내가 받았던 오름 정보 보여주기 (/oreums/1) (카톡 공유했을 때 보여주는 api)
    @GetMapping("/oreums/{myOreumId}")
    public ResponseEntity<OreumResponse> findByName(@PathVariable("myOreumId") Long myOreumId) {
        return ResponseEntity.ok().body(oreumService.getMyOreum(myOreumId));
    }
}
