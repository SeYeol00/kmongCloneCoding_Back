package com.sparta.kmongclonecoding.dto;

import com.sparta.kmongclonecoding.domain.File;
import com.sparta.kmongclonecoding.domain.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
@Setter
@Getter
public class UpdateProjectRequestDto {
    private Long project_Id;
    private String bigCategory;
    private String smallCategory;
    private String progressMethod;
    private String projectScope;
    private String title;
    private String currentStatus;
    private Map<String, Boolean> responseDtoMap;
    private String solutionInUse;
    private String reactable;
    private int budget;
    private Boolean taxInvoice;
    private String volunteerValidDate;
    private String dueDateForApplication;
    private int workingPeriod;
    private String description;

    /*private List<FileRequestDto> files;*/



    public UpdateProjectRequestDto(Project project, Map<String, Boolean> responseDtoMap,String valid, String due){
        this.project_Id = project.getId();
        this.bigCategory = project.getBigCategory();
        this.smallCategory = project.getSmallCategory();
        this.progressMethod = project.getProgressMethod();
        this.projectScope = project.getProjectScope();
        this.title = project.getTitle();
        this.currentStatus = project.getCurrentStatus();
        this.responseDtoMap = responseDtoMap;
        this.solutionInUse = project.getSolutionInUse();
        this.reactable = project.getReactable();
        this.budget = project.getBudget();
        this.taxInvoice = project.getTaxInvoice();
        this.volunteerValidDate = valid;
        this.dueDateForApplication = due;
        this.workingPeriod = project.getWorkingPeriod();
        this.description = project.getDescription();
    }
}
