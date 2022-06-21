package com.sparta.kmongclonecoding.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectListResponseDto {


    Long project_id;

    String leftDaysForEnd;

    String title;

    int budget;

    String description;

    int workingPeriod;

    String bigCategory;

    String smallCategory;

    Boolean taxInvoice;

    String progressMethod;

    String imageUrl;

    public ProjectListResponseDto(Long project_id, String leftDaysForEnd, String title, int budget, String bigCategory, String smallCategory, String description, int workingPeriod, Boolean taxInvoice, String progressMethod,String imageUrl){
        this.project_id = project_id;
        this.leftDaysForEnd = leftDaysForEnd;
        this.title = title;
        this.budget = budget;
        this.bigCategory = bigCategory;
        this.smallCategory = smallCategory;
        this.description = description;
        this.workingPeriod = workingPeriod;
        this.taxInvoice = taxInvoice;
        this.progressMethod = progressMethod;
        this.imageUrl = imageUrl;
    }




}
