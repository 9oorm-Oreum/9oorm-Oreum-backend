package com.example.goorm.oreum.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BirthDayRequest {
    private String nickname;
    private int month;
    private int day;
}