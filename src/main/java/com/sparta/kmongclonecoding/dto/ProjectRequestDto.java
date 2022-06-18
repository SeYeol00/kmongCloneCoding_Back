package com.sparta.kmongclonecoding.dto;

import com.sparta.kmongclonecoding.domain.ProfileImages;
import com.sparta.kmongclonecoding.domain.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
public class ProjectRequestDto {

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
    private String budget;
    private String taxInvoice;
    private String volunteerValidDate;
    private String dueDateForApplication;
    private String workingPeriod;


}
