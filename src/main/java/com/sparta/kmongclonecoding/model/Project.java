package com.sparta.kmongclonecoding.model;

import com.sparta.kmongclonecoding.domain.ProfileImages;
import com.sparta.kmongclonecoding.domain.Timestamped;
import com.sparta.kmongclonecoding.domain.User;
import com.sparta.kmongclonecoding.dto.ProjectRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "profileImagesId")
    private ProfileImages profileImages;

    @Column(nullable = false)
    private String bigCategory;

    @Column(nullable = false)
    private String smallCategory;

    @Column(nullable = false)
    private String progressMethod;

    @Column(nullable = false)
    private String projectScope;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String currentStatus;

    @Column(nullable = false)
    private String requiredFunction;

    @Column(nullable = false)
    private String userRelatedFunction;

    @Column(nullable = false)
    private String commerceRelatedFunction;

    @Column(nullable = false)
    private String siteEnvironment;

    @Column(nullable = false)
    private String solutionInUse;

    @Column(nullable = false)
    private String reactable;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int budget;

    @Column(nullable = false)
    private boolean taxInvoice;

    @Column(nullable = false)
    private String volunteerValidDate;

    @Column(nullable = false)
    private String dueDateForApplication;

    @Column(nullable = false)
    private int workingPeriod;

    public Project(ProjectRequestDto projectRequestDto, User user){
        this.user=user;
        this.progressMethod=projectRequestDto.getProgressMethod();
        this.projectScope=projectRequestDto.getProjectScope();
        this.title=projectRequestDto.getTitle();
        this.currentStatus=projectRequestDto.getCurrentStatus();
        this.requiredFunction=projectRequestDto.getRequiredFunction();
        this.userRelatedFunction=projectRequestDto.getUserRelatedFunction();
        this.commerceRelatedFunction=projectRequestDto.getCommerceRelatedFunction();
        this.siteEnvironment=projectRequestDto.getSiteEnvironment();
        this.solutionInUse=projectRequestDto.getSolutionInUse();
        this.reactable=projectRequestDto.getReactable();
        this.budget=projectRequestDto.getBudget();
        this.taxInvoice=projectRequestDto.getTaxInvoice();
        this.volunteerValidDate=projectRequestDto.getVolunteerValidDate();
        this.dueDateForApplication=projectRequestDto.getDueDateForApplication();
        this.workingPeriod=projectRequestDto.getWorkingPeriod();
    }
}
