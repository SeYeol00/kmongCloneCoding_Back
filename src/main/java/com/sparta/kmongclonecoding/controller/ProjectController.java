package com.sparta.kmongclonecoding.controller;


import com.sparta.kmongclonecoding.dto.HomePageResponseDefaultDto;
import com.sparta.kmongclonecoding.dto.MyPageListResponseDto;
import com.sparta.kmongclonecoding.dto.ProjectListResponseDto;
import com.sparta.kmongclonecoding.dto.ProjectRequestDto;
import com.sparta.kmongclonecoding.security.UserDetailsImpl;
import com.sparta.kmongclonecoding.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;


    @GetMapping("/")
    public List<HomePageResponseDefaultDto> getHomePage() {
        return projectService.getHomePage();
    }

    @PostMapping("/")
    public List<HomePageResponseDefaultDto> getHomePageByCategory(@RequestParam String Category) {
        return projectService.getHomePageByCategory(Category);
    }

    @GetMapping("/projects")
    public List<ProjectListResponseDto> getProjectListPage(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy
    ) {
        page = page - 1;
        return projectService.getProjectListPage(page, size, sortBy);

    }

    @GetMapping("/mypage/projects")
    public List<MyPageListResponseDto> getMyPageProject(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        return projectService.getMyPageProject(userDetails.getUser().getId());
    }
//    @GetMapping("/projects/budget")
//    public List<ProjectListResponseDto> getProjectListPageByBudget(){
//        return projectService.getProjectListPageByBudget();
//    }
//
//    @GetMapping("/projects/due")
//    public List<ProjectListResponseDto> getProjectListPageByDate(){
//        return projectService.getProjectListPageByDate();
//    }



    @PostMapping("/projects/project")
    public ResponseEntity<Void> createProject(@RequestBody ProjectRequestDto projectRequestDto,
//    public ResponseEntity<Void> createProject(@RequestPart(value = "projectDto") ProjectRequestDto projectRequestDto,
//                                              @RequestPart(value = "files") List<MultipartFile> files,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails) throws ParseException {
        //일단 리턴값 void 로 설정
//        projectService.createProject(projectRequestDto, userDetails.getUser().getId(), files);
        projectService.createProject(projectRequestDto, userDetails.getUser().getId());
        return ResponseEntity.ok().build();
    }


    @PutMapping("/projects/project/{projectId}")
    public Map<String, Boolean> editProject(@PathVariable Long projectId,
                                            @RequestBody ProjectRequestDto projectRequestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) throws ParseException {
        return projectService.editProject(projectId, projectRequestDto, userDetails.getUser().getId());

    }

    @DeleteMapping("/projects/project/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        projectService.deleteProject(projectId, userDetails.getUser().getId());
        return ResponseEntity.ok().build();
    }
}
