package com.sparta.kmongclonecoding.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectListResponseBudgetDto {


    Long project_id;

    String title;

    int budget;

    String discription;

    int workingPeriod;
}
