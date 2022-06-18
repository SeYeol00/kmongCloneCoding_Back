package com.sparta.kmongclonecoding.repository;

import com.sparta.kmongclonecoding.domain.BigCategory;
import com.sparta.kmongclonecoding.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    Optional<Project> findById(Long id);

    List<Project> findAllByBigCategory(String bigCategory);

    List<Project> findAll();


}
