package com.example.goorm.oreum;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OreumService {
    private final OreumRepository oreumRepository;
    public List<Oreum> getOreums(){
        return oreumRepository.findAll();
    }

    public void readCsv(){
        File csv = new File("C:\\Users\\Windows10\\Desktop\\오름.txt");
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(csv));
            while((line = br.readLine()) != null){
                String[] lineArr = line.split(",");
                String name = lineArr[0];
                String type = lineArr[1].substring(1, lineArr[1].length()-1).split("/")[1];
                String pos = lineArr[2].substring(1, lineArr[2].length()-1);
                Double x = Double.parseDouble(pos.split(" ")[0]);
                Double y = Double.parseDouble(pos.split(" ")[1]);
                Double z = Double.parseDouble(pos.split(" ")[2]);
                System.out.println(name+" "+type+" "+x+" "+y+" "+z);
                Oreum oreum = Oreum.builder()
                        .name(name)
                        .type(type)
                        .xPos(x)
                        .yPos(y)
                        .zPos(z)
                        .build();
                oreumRepository.save(oreum);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
