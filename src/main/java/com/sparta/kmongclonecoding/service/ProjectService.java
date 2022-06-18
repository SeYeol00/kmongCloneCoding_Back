package com.sparta.kmongclonecoding.service;

import com.sparta.kmongclonecoding.domain.Project;
import com.sparta.kmongclonecoding.domain.User;
import com.sparta.kmongclonecoding.dto.HomePageResponseDefaultDto;
import com.sparta.kmongclonecoding.dto.ProjectListResponseDto;
import com.sparta.kmongclonecoding.dto.ProjectRequestDto;
import com.sparta.kmongclonecoding.repository.ProjectRepository;
import com.sparta.kmongclonecoding.repository.UserRepository;
import com.sparta.kmongclonecoding.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;



@Service
@RequiredArgsConstructor
public class ProjectService {
    public static String[] imageList = new String[]{
            "https://user-images.githubusercontent.com/79959576/174434691-8fc1def9-9cfe-48bc-9530-ea01aad28bfa.jpg",
            "https://user-images.githubusercontent.com/79959576/174434696-33b43588-3889-441b-b840-e98cd215c29b.jpg",
            "https://user-images.githubusercontent.com/79959576/174434727-0a5417e9-09a1-4953-941e-e2a5f05aa9ec.jpg",
            "https://user-images.githubusercontent.com/79959576/174434729-b8190447-d6d0-430d-b66d-3f7561195e54.jpg",
            "https://user-images.githubusercontent.com/79959576/174434731-5bae8990-4e0b-484d-8ff7-b4193aac3e58.jpg"};

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;





    public List<HomePageResponseDefaultDto> getHomePage() {
        List<HomePageResponseDefaultDto> homePageResponseDefaultDtos = new ArrayList<>();
        List<Project> projects = projectRepository.findAllByBigCategory("IT.프로그래밍");
        int count=0;
        for (Project project : projects) {
            HomePageResponseDefaultDto homePageResponseDefaultDto = new HomePageResponseDefaultDto(project.getId(),project.getTitle(), project.getBudget(), project.getDescription(), project.getWorkingPeriod(), project.getImageUrl());
            homePageResponseDefaultDtos.add(homePageResponseDefaultDto);
            count+=1;
            if(count==4){
                break;
            }
        }
        return homePageResponseDefaultDtos;
    }

    public List<HomePageResponseDefaultDto> getHomePageByCategory(String category) {
        List<HomePageResponseDefaultDto> homePageResponseDefaultDtos = new ArrayList<>();
        List<Project> projects = projectRepository.findAllByBigCategory(category);
        int count=0;
        for (Project project : projects) {
            HomePageResponseDefaultDto homePageResponseDefaultDto = new HomePageResponseDefaultDto(project.getId(),project.getTitle(), project.getBudget(), project.getDescription(), project.getWorkingPeriod(), project.getImageUrl());
            homePageResponseDefaultDtos.add(homePageResponseDefaultDto);
            count+=1;
            if(count==4){
                break;
            }
        }
        return homePageResponseDefaultDtos;
    }

    public List<ProjectListResponseDto> getProjectListPage() throws ParseException {
        List<ProjectListResponseDto> projectListResponseDtos = new ArrayList<>();
        List<Project> projects = projectRepository.findAll(Sort.by(Sort.Direction.DESC,"createdAt"));
        for(Project project:projects){
            Calendar getToday = Calendar.getInstance();
            getToday.setTime(new Date()); //금일 날짜

            Calendar cmpDate = Calendar.getInstance();
            cmpDate.setTime(project.getVolunteerValidDate());

            long diffSec = (getToday.getTimeInMillis() - cmpDate.getTimeInMillis()) / 1000;
            long diffDays = diffSec / (24*60*60); //일자수 차이
            String diffDates = Long.toString(diffDays);

            ProjectListResponseDto projectListResponseDto = new ProjectListResponseDto(
                    project.getId(),
                    diffDates,
                    project.getTitle(),
                    project.getBudget(),
                    project.getBigCategory(),
                    project.getSmallCategory(),
                    project.getDescription(),
                    project.getWorkingPeriod(),
                    project.isTaxInvoice(),
                    project.getProgressMethod(),
                    project.getImageUrl());
            projectListResponseDtos.add(projectListResponseDto);
        }

        return projectListResponseDtos;
    }
//    public List<ProjectListResponseDto> getProjectListPage() {
//        List<ProjectListResponseDto> projectListResponseDtos = new ArrayList<>();
//        List<Project> projects = projectRepository.findAll(Sort.by(Sort.Direction.DESC,"createdAt"));
//
//    }

    public List<ProjectListResponseDto> getProjectListPageByBudget() {
        List<ProjectListResponseDto> projectListResponseDtos = new ArrayList<>();
        List<Project> projects = projectRepository.findAll(Sort.by(Sort.Direction.DESC,"budget"));
        for(Project project:projects){
            Calendar getToday = Calendar.getInstance();
            getToday.setTime(new Date()); //금일 날짜

            Calendar cmpDate = Calendar.getInstance();
            cmpDate.setTime(project.getVolunteerValidDate());

            long diffSec = (getToday.getTimeInMillis() - cmpDate.getTimeInMillis()) / 1000;
            long diffDays = diffSec / (24*60*60); //일자수 차이
            String diffDates = Long.toString(diffDays);

            ProjectListResponseDto projectListResponseDto = new ProjectListResponseDto(
                    project.getId(),
                    diffDates,
                    project.getTitle(),
                    project.getBudget(),
                    project.getBigCategory(),
                    project.getSmallCategory(),
                    project.getDescription(),
                    project.getWorkingPeriod(),
                    project.isTaxInvoice(),
                    project.getProgressMethod(),
                    project.getImageUrl());
            projectListResponseDtos.add(projectListResponseDto);
        }

        return projectListResponseDtos;
    }


    public List<ProjectListResponseDto> getProjectListPageByBudgetByDate() {
        List<ProjectListResponseDto> projectListResponseDtos = new ArrayList<>();
        List<Project> projects = projectRepository.findAll(Sort.by(Sort.Direction.ASC,"volunteerValidDate"));
        for(Project project:projects){
            Calendar getToday = Calendar.getInstance();
            getToday.setTime(new Date()); //금일 날짜

            Calendar cmpDate = Calendar.getInstance();
            cmpDate.setTime(project.getVolunteerValidDate());

            long diffSec = (getToday.getTimeInMillis() - cmpDate.getTimeInMillis()) / 1000;
            long diffDays = diffSec / (24*60*60); //일자수 차이
            String diffDates = Long.toString(diffDays);

            ProjectListResponseDto projectListResponseDto = new ProjectListResponseDto(
                    project.getId(),
                    diffDates,
                    project.getTitle(),
                    project.getBudget(),
                    project.getBigCategory(),
                    project.getSmallCategory(),
                    project.getDescription(),
                    project.getWorkingPeriod(),
                    project.isTaxInvoice(),
                    project.getProgressMethod(),
                    project.getImageUrl());
            projectListResponseDtos.add(projectListResponseDto);
        }

        return projectListResponseDtos;



    }


    public void createProject(ProjectRequestDto projectRequestDto, Long userId) throws ParseException {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new IllegalArgumentException("등록되지 않은 사용자입니다.")
        );
        double randomValue = Math.random();
        int intValue = (int) (randomValue * 4);
        String imageUrl = imageList[intValue];

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date volunteerValidDate = formatter.parse(projectRequestDto.getVolunteerValidDate());
        Date dueDateForApplication = formatter.parse(projectRequestDto.getDueDateForApplication());
        Project project= new Project(projectRequestDto,user,volunteerValidDate,dueDateForApplication,imageUrl);

        projectRepository.save(project);
    }

    public Map<String,Boolean> editProject(Long projectId, ProjectRequestDto projectRequestDto,Long userId) throws ParseException {
        Project project =projectRepository.findByIdAndUserId(projectId,userId);
        if(project == null){
            throw new NullPointerException("존재하지 않는 프로젝트입니다.");
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date volunteerValidDate = formatter.parse(projectRequestDto.getVolunteerValidDate());
        Date dueDateForApplication = formatter.parse(projectRequestDto.getDueDateForApplication());

        project.update(projectRequestDto,volunteerValidDate,dueDateForApplication);

        Map<String, Boolean> responseDtoMap = new HashMap<String, Boolean>();

        if (projectRequestDto.getCurrentStatus().contains(",")) {
            String[] currentStatusArr = projectRequestDto.getCurrentStatus().split(",");
            extracted(responseDtoMap, currentStatusArr);
        } else {
            responseDtoMap.put(projectRequestDto.getCurrentStatus(), true);
        }
        if (projectRequestDto.getRequiredFunction().contains(",")) {
            String[] requiredFunctionArr = projectRequestDto.getRequiredFunction().split(",");
            extracted(responseDtoMap, requiredFunctionArr);
        } else {
            responseDtoMap.put(projectRequestDto.getRequiredFunction(), true);
        }

        if (projectRequestDto.getUserRelatedFunction().contains(",")) {
            String[] userRelatedFunctionArr = projectRequestDto.getUserRelatedFunction().split(",");
            extracted(responseDtoMap, userRelatedFunctionArr);
        } else {
            responseDtoMap.put(projectRequestDto.getUserRelatedFunction(), true);
        }
        if (projectRequestDto.getCommerceRelatedFunction().contains(",")) {
            String[] commerceRelatedFunctionArr = projectRequestDto.getCommerceRelatedFunction().split(",");
            extracted(responseDtoMap, commerceRelatedFunctionArr);
        } else {
            responseDtoMap.put(projectRequestDto.getCommerceRelatedFunction(), true);
        }
        if (projectRequestDto.getSiteEnvironment().contains(",")) {
            String[] siteEnvironment = projectRequestDto.getSiteEnvironment().split(",");
            extracted(responseDtoMap, siteEnvironment);
        } else {
            responseDtoMap.put(projectRequestDto.getSiteEnvironment(), true);
        }
        if (projectRequestDto.getSolutionInUse().contains(",")) {
            String[] solutionInUseArr = projectRequestDto.getSolutionInUse().split(",");
            extracted(responseDtoMap, solutionInUseArr);
        } else {
            responseDtoMap.put(projectRequestDto.getSolutionInUse(), true);
        }

        return responseDtoMap;
    }

    private void extracted(Map<String, Boolean> responseDtoMap, String[] strArr) {
        for (int i = 0; i <= strArr.length; i++) {
            responseDtoMap.put(strArr[i], true);
        }
    }


    public void deleteProject(Long projectId, Long userId) {
        Project project =projectRepository.findByIdAndUserId(projectId,userId);
        if(project == null ){
            throw new IllegalArgumentException("권한이 없습니다.");
        }
//        awsS3Service.deleteFile(fileName);

        projectRepository.deleteById(projectId);
    }
}