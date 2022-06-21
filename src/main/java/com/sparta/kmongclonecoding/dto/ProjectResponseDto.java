package com.sparta.kmongclonecoding.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class ProjectResponseDto {

    private Long project_Id;
    private String bigCategory;
    private String smallCategory;
    private String progressMethod;
    private String projectScope;
    private String title;
    private String currentStatus;
    private String requiredFunction;
    private String userRelatedFunction;
    private String commerceRelatedFunction;
    private String siteEnvironment;
    private String solutionInUse;
    private String reactable;
    private int budget;
    private Boolean taxInvoice;
    private Date volunteerValidDate;
    private Date dueDateForApplication;
    private int workingPeriod;
    private String description;
}
