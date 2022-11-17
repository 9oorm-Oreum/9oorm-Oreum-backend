package com.example.goorm.oreum;

import com.example.goorm.oreum.dto.BirthdayRequest;
import com.example.goorm.oreum.dto.OreumResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OreumController {
    private final OreumService oreumService;

    // 생일에 따른 오름 정보 보여주기 (입력하고 디비에 저장하는 거까지)
    @PostMapping("/oreum")
    public ResponseEntity<OreumResponse> getOreum(@RequestBody BirthdayRequest request){
        return ResponseEntity.ok().body(oreumService.getOreum(request));
    }

    // 내가 받았던 오름 정보 보여주기 (/oreums/1) (카톡 공유했을 때 보여주는 api)
    @GetMapping("/oreums/{myOreumId}")
    public ResponseEntity<OreumResponse> findByName(@PathVariable("myOreumId") Long myOreumId) {
        return ResponseEntity.ok().body(oreumService.getMyOreum(myOreumId));
    }
}
