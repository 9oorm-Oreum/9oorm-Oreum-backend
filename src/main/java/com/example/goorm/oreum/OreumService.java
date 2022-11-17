package com.example.goorm.oreum;

import com.example.goorm.oreum.domain.MyOreum;
import com.example.goorm.oreum.domain.Oreum;
import com.example.goorm.oreum.dto.BirthdayRequest;
import com.example.goorm.oreum.dto.OreumResponse;
import com.example.goorm.oreum.repository.MyOreumRepository;
import com.example.goorm.oreum.repository.OreumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class OreumService {
    private final OreumRepository oreumRepository;
    private final MyOreumRepository myOreumRepository;

    public OreumResponse getOreum(BirthdayRequest request){
        int month = request.getMonth();
        int day = request.getDay();
        Oreum oreum = oreumRepository.findByBirthday(month, day); // 저장된 오름을 찾아서
        log.info("Oreum id: "+oreum.getId());
        MyOreum myOreum = MyOreum.builder() // 나의 오름을 저장하고
                .nickname(request.getNickname())
                .oreumId(oreum.getId())
                .build();
        log.info("MyOreum id: "+myOreum.getId());
        myOreumRepository.save(myOreum);
        return OreumResponse.of(oreum, myOreum, request);
    }

    public OreumResponse getMyOreum(Long myOreumId){
        MyOreum myOreum = myOreumRepository.findById(myOreumId).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 오름 스티커 번호가 없습니다");
        });
        log.info("MyOreum id: "+myOreum.getId());
        Oreum oreumInfo = oreumRepository.findById(myOreum.getOreumId()).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 오름의 번호가 없습니다");
        });
        log.info("Oreum id: "+oreumInfo.getId());
        return OreumResponse.ofOthers(oreumInfo, myOreum);
    }
}
