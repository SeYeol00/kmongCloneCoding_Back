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

    String discription;

    int workingPeriod;

    String bigCategory;

    String smallCategory;

    boolean taxInvoice;

    String progressMethod;

    public ProjectListResponseDto(Long project_id, String leftDaysForEnd, String title, int budget,String bigCategory,String smallCategory,String discription,int workingPeriod,boolean taxInvoice, String progressMethod){
        this.project_id = project_id;
        this.leftDaysForEnd = leftDaysForEnd;
        this.title = title;
        this.budget = budget;
        this.bigCategory = bigCategory;
        this.smallCategory = smallCategory;
        this.discription = discription;
        this.workingPeriod = workingPeriod;
        this.taxInvoice = taxInvoice;
        this.progressMethod = progressMethod;
    }




}
