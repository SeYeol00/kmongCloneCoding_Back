package com.sparta.kmongclonecoding.service;

import com.sparta.kmongclonecoding.dto.HomePageResponseDefaultDto;
import com.sparta.kmongclonecoding.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;


    /*public List<HomePageResponseDefaultDto> getHomePage() {



        findAllByBigCategory();


    }*/
}
