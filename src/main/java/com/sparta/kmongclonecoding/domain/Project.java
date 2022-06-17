package com.sparta.kmongclonecoding.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "bigCategoryId")
    private BigCategory bigCategory;

    @ManyToOne
    @JoinColumn(name = "smallCategoryId")
    private SmallCategory smallCategory;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "profileImagesId")
    private ProfileImages profileImages;

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
    private String budget;

    @Column(nullable = false)
    private String taxInvoice;

    @Column(nullable = false)
    private String volunteerValidDate;

    @Column(nullable = false)
    private String dueDateForApplication;

    @Column(nullable = false)
    private String workingPeriod;
}
