package com.sparta.kmongclonecoding.repository;

import com.sparta.kmongclonecoding.domain.Project;
import com.sparta.kmongclonecoding.dto.ProjectListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    Optional<Project> findById(Long id);


    List<Project> findAllByBigCategory(String bigCategory);

    List<Project> findAll();

    Page<Project> findAll(Pageable pageable);

    Project findByIdAndUserId(Long projectId, Long userId);
    List<Project> findAllByUserId(Long userId);
}
