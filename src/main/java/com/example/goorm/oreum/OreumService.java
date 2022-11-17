package com.example.goorm.oreum;

import com.example.goorm.oreum.dto.BirthDayRequest;
import com.example.goorm.oreum.dto.OreumResponse;
import com.example.goorm.oreum.repository.MyOreumRepository;
import com.example.goorm.oreum.repository.OreumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;

@Service
@RequiredArgsConstructor
public class OreumService {
    private final OreumRepository oreumRepository;
    private final MyOreumRepository myOreumRepository;

    public OreumResponse getOreum(BirthDayRequest request){
        int month = request.getMonth();
        int day = request.getDay();
        Oreum oreum = oreumRepository.findByBirthday(month, day); // 저장된 오름을 찾아서
        MyOreum myOreum = MyOreum.builder() // 나의 오름을 저장하고
                .nickname(request.getNickname())
                .oreumId(oreum.getId())
                .build();

        myOreumRepository.save(myOreum);
        return OreumResponse.of(oreum, myOreum, request);
    }

    public OreumResponse getMyOreum(Long myOreumId){
        MyOreum myOreum = myOreumRepository.findById(myOreumId).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 오름 스티커 번호가 없습니다");
        });
        Oreum oreumInfo = oreumRepository.findById(myOreum.getOreumId()).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 오름의 번호가 없습니다");
        });
        return OreumResponse.ofOthers(oreumInfo, myOreum);
    }

    public void readCsv(){
        File csv = new File("C:\\Users\\Windows10\\Desktop\\오름.txt");
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(csv));
            int monthCheck = 1;
            int dayCheck = 1;

            while((line = br.readLine()) != null) {
                String[] lineArr = line.split(",");
                String name = lineArr[0];
                String type = lineArr[1].split("/")[1];
                String pos = lineArr[2];
                Double x = Double.parseDouble(pos.split(" ")[0]);
                Double y = Double.parseDouble(pos.split(" ")[1]);
                Double z = Double.parseDouble(pos.split(" ")[2]);

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
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
