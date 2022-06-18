package com.sparta.kmongclonecoding.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileRequestDto {

    private String fileUrl;

    private String fileName;
}
