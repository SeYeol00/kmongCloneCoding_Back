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

import java.util.ArrayList;
import java.util.HashMap;
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

//    public List<ProjectListResponseDto> getProjectListPage() {
//        List<ProjectListResponseDto> projectListResponseDtos = new ArrayList<>();
//        List<Project> projects = projectRepository.findAll(Sort.by(Sort.Direction.DESC,"createdAt"));
//
//    }



    public void createProject(ProjectRequestDto projectRequestDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new IllegalArgumentException("등록되지 않은 사용자입니다.")
        );
        Project project= new Project(projectRequestDto,user);
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
