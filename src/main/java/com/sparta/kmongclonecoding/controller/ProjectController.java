package com.sparta.kmongclonecoding.controller;


import com.sparta.kmongclonecoding.dto.HomePageResponseDefaultDto;
import com.sparta.kmongclonecoding.dto.ProjectRequestDto;
import com.sparta.kmongclonecoding.security.UserDetailsImpl;
import com.sparta.kmongclonecoding.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;


    @GetMapping("/")
    public List<HomePageResponseDefaultDto> getHomePage(){
        return projectService.getHomePage();
    }

    @PostMapping("/projects/project")
    public void createProject(ProjectRequestDto projectRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        //일단 리턴값 void 로 설정
        projectService.createProject(projectRequestDto,userDetails.getUser().getId());
    }
}
