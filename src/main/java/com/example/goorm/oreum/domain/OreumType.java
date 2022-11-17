package com.example.goorm.oreum.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OreumType implements EnumMapperType{
    HORSESHOE("말굽"),
    CONE("원추"),
    CIRCLE("원형"),
    COMPLEX("복합");

    @Getter
    private final String status;

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getTitle() {
        return status;
    }
}
