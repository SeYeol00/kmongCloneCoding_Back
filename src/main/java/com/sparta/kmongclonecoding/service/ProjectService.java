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

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;


    public List<HomePageResponseDefaultDto> getHomePage() {
        List<HomePageResponseDefaultDto> homePageResponseDefaultDtos = new ArrayList<>();
        List<Project> projects = projectRepository.findAllByBigCategory("IT.PROGRMMING");
        int count=0;
        for (Project project : projects) {
            HomePageResponseDefaultDto homePageResponseDefaultDto = new HomePageResponseDefaultDto(project.getId(),project.getTitle(), project.getBudget(), project.getDescription(), project.getWorkingPeriod());
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
            HomePageResponseDefaultDto homePageResponseDefaultDto = new HomePageResponseDefaultDto(project.getId(),project.getTitle(), project.getBudget(), project.getDescription(), project.getWorkingPeriod());
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
                    project.getProgressMethod());
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
                    project.getProgressMethod());
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
                    project.getProgressMethod());
            projectListResponseDtos.add(projectListResponseDto);
        }

        return projectListResponseDtos;



    }
}





    public void createProject(ProjectRequestDto projectRequestDto, Long userId) throws ParseException {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new IllegalArgumentException("등록되지 않은 사용자입니다.")
        );
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date volunteerValidDate = formatter.parse(projectRequestDto.getVolunteerValidDate());
        Date dueDateForApplication = formatter.parse(projectRequestDto.getDueDateForApplication());
        Project project= new Project(projectRequestDto,user,volunteerValidDate,dueDateForApplication);

        projectRepository.save(project);
    }

    public Map<String,Object> editProject(Long projectId, ProjectRequestDto projectRequestDto,Long userId) {
        Project project =projectRepository.findByIdAndUserId(projectId,userId);
        if(project == null){
            throw new NullPointerException("존재하지 않는 프로젝트입니다.");
        }
        project.update(projectRequestDto);

        Map<String,Object> responseDtoMap = new HashMap<String ,Object>();

        String []currentStatusArr=projectRequestDto.getCurrentStatus().split(",");
        String []requiredFunctionArr=projectRequestDto.getRequiredFunction().split(",");
        String []userRelatedFunctionArr=projectRequestDto.getUserRelatedFunction().split(",");
        String []commerceRelatedFunctionArr=projectRequestDto.getCommerceRelatedFunction().split(",");
        String []siteEnvironment=projectRequestDto.getSiteEnvironment().split(",");
        String []solutionInUseArr=projectRequestDto.getSolutionInUse().split(",");
        extracted(responseDtoMap, currentStatusArr);
        extracted(responseDtoMap,requiredFunctionArr);
        extracted(responseDtoMap,userRelatedFunctionArr);
        extracted(responseDtoMap,commerceRelatedFunctionArr);
        extracted(responseDtoMap,siteEnvironment);
        extracted(responseDtoMap,solutionInUseArr);

        return responseDtoMap;

        //"currentStatus":”[string] 프로젝트 준비상황”,
        //"requiredFunction"[string] 기본기능”):,
        //"userRequiredFunction"(”[string] 회원 관련 기능”):,
        //"commerceRequiredFunction"([string] 커머스 관련 기능”):,
        //"siteEnvironment"(”[string] 사이트 환경”):,
        //”solutionInUse”(String) 솔루션”: ,



    }

    private void extracted(Map<String, Object> responseDtoMap, String[] strArr) {
        for(int i = 0; i<= strArr.length; i++){
            responseDtoMap.put(strArr[i],true);
        }
    }

}
