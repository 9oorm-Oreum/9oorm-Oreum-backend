package com.example.goorm.oreum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Oreum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private OreumType type;
    private double xPos;
    private double yPos;
    private double zPos;
    private int month;
    private int day;

    // 랜덤 색상값 추가해줘야 함
    public void toTypeEnum(String type) {
        this.type = Arrays.stream(OreumType.values())
                .filter(o1 -> o1.getTitle().equals(type))
                .findFirst()
                .get();
    }

}

