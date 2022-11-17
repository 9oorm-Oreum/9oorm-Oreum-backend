package com.example.goorm.oreum;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OreumService {
    private final OreumRepository oreumRepository;
    public List<Oreum> getOreums(){
        return oreumRepository.findAll();
    }

    public Oreum getOreum(BirtyDayRequest request){
        int month = request.getMonth();
        int day = request.getDay();
        return oreumRepository.findByBirthday(month, day);
    }

    public void readCsv(){
        File csv = new File("C:\\Users\\Windows10\\Desktop\\오름.txt");
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(csv));
            int monthCheck = 1;
            int dayCheck = 1;

            int cnt =0 ;
            while((line = br.readLine()) != null) {
                System.out.println(cnt++);
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
