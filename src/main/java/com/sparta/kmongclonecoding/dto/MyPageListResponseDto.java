package com.sparta.kmongclonecoding.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class MyPageListResponseDto {


    Long project_id;

    String title;

    int budget;

    String bigCategory;

    String smallCategory;

    String imageUrl;

    public MyPageListResponseDto(Long project_id, String title, int budget, String bigCategory, String smallCategory,  String imageUrl){
        this.project_id = project_id;
        this.title = title;
        this.budget = budget;
        this.bigCategory = bigCategory;
        this.smallCategory = smallCategory;
        this.imageUrl = imageUrl;
    }


}
