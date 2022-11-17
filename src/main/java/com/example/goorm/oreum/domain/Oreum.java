package com.example.goorm.oreum.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private OreumType oreumType;

    private double xPos;
    private double yPos;
    private double zPos;
    private int month;
    private int day;
    private int leftPos;
    private int rightPos;

    public void toTypeEnum(String type) {
        this.oreumType = Arrays.stream(OreumType.values())
                .filter(o1 -> o1.getTitle().equals(type))
                .findFirst()
                .get();
    }
}

