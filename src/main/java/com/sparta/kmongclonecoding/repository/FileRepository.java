package com.sparta.kmongclonecoding.repository;

import com.sparta.kmongclonecoding.domain.File;
import com.sparta.kmongclonecoding.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File,Long> {
    List<File> findAllByProject(Project project);
}
