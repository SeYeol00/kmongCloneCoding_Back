package com.sparta.kmongclonecoding.service;

import com.sparta.kmongclonecoding.domain.Project;
import com.sparta.kmongclonecoding.domain.User;
import com.sparta.kmongclonecoding.dto.ProjectRequestDto;
import com.sparta.kmongclonecoding.repository.ProjectRepository;
import com.sparta.kmongclonecoding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;


    /*public List<HomePageResponseDefaultDto> getHomePage() {


        findAllByBigCategory();


    }*/


    public void createProject(ProjectRequestDto projectRequestDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new IllegalArgumentException("등록되지 않은 사용자입니다.")
        );
        Project project= new Project(projectRequestDto,user);
        projectRepository.save(project);
    }

    public void editProject(Long projectId, ProjectRequestDto projectRequestDto,Long userId) {
        Project project =projectRepository.findbyIdAndUserId(projectId,userId);

    }
}
