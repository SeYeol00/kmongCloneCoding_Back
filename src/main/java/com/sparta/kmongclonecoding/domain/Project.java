package com.sparta.kmongclonecoding.domain;

import com.sparta.kmongclonecoding.dto.ProjectRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project extends Timestamped{

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
    private Date volunteerValidDate;

    @Column(nullable = false)
    private Date dueDateForApplication;

    @Column(nullable = false)
    private int workingPeriod;




    public Project(ProjectRequestDto projectRequestDto, User user,Date volunteerValidDate,Date dueDateForApplication){
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
        this.workingPeriod=projectRequestDto.getWorkingPeriod();
        this.volunteerValidDate = volunteerValidDate;
        this.dueDateForApplication =dueDateForApplication;
    }
}

