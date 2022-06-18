package com.sparta.kmongclonecoding.controller;


import com.sparta.kmongclonecoding.dto.HomePageResponseDefaultDto;
import com.sparta.kmongclonecoding.dto.ProjectListResponseDto;
import com.sparta.kmongclonecoding.dto.ProjectRequestDto;
import com.sparta.kmongclonecoding.security.UserDetailsImpl;
import com.sparta.kmongclonecoding.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;


    @GetMapping("/")
    public List<HomePageResponseDefaultDto> getHomePage(){
        return projectService.getHomePage();
    }

    @GetMapping("/")
    public List<HomePageResponseDefaultDto> getHomePageByCategory(@RequestParam String Category){
        return projectService.getHomePageByCategory(Category);
    }

    @GetMapping("/projects")
    public List<ProjectListResponseDto> getProjectListPage(){
        return projectService.getProjectListPage();
    }




    @PostMapping("/projects/project")
    public void createProject(@RequestBody ProjectRequestDto projectRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        //일단 리턴값 void 로 설정
        projectService.createProject(projectRequestDto, userDetails.getUser().getId());
    }





    @PutMapping("/projects/project/{projectId}")
    public void editProject(@PathVariable Long projectId,
                            ProjectRequestDto projectRequestDto,
                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        projectService.editProject(projectId,projectRequestDto,userDetails.getUser().getId());

    }
}
