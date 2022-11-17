package com.example.goorm.oreum.dto;

import com.example.goorm.oreum.domain.MyOreum;
import com.example.goorm.oreum.domain.Oreum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OreumResponse {
    private String nickname;
    private String name;
    private String type;
    private double xPos;
    private double yPos;
    private double zPos;
    private Long myOreumId;
    private int left;
    private int right;

    public static OreumResponse of(Oreum oreum, MyOreum myOreum, BirthdayRequest request){
        return OreumResponse.builder()
                .nickname(request.getNickname())
                .name(oreum.getName())
                .type(oreum.getOreumType().getTitle())
                .xPos(oreum.getXPos())
                .yPos(oreum.getYPos())
                .zPos(oreum.getZPos())
                .myOreumId(myOreum.getId())
                .left(oreum.getLeftPos())
                .right(oreum.getRightPos())
                .build();
    }

    public static OreumResponse ofOthers(Oreum oreum, MyOreum myOreum){
        return OreumResponse.builder()
                .nickname(myOreum.getNickname())
                .name(oreum.getName())
                .type(oreum.getOreumType().getTitle())
                .xPos(oreum.getXPos())
                .yPos(oreum.getYPos())
                .zPos(oreum.getZPos())
                .myOreumId(myOreum.getId())
                .left(oreum.getLeftPos())
                .right(oreum.getRightPos())
                .build();
    }
}