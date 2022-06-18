package com.sparta.kmongclonecoding.model;

import com.sparta.kmongclonecoding.domain.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileUrl;

    @Column(nullable = false)
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;
}
