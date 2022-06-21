package com.sparta.kmongclonecoding.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileUrl;

    @Column(nullable = false)
    private String fileName;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "projectId")
    private Project project;

    public File(String fileUrl,String fileName,Project project){
    this.fileUrl = fileUrl;
    this.fileName = fileName;
    this.project = project;
    }


}
