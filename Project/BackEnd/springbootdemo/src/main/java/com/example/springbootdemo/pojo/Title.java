package com.example.springbootdemo.pojo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Title {
    private Integer id;
    private String titleName;
    private String projectName;
    private String description;

    private String textData;

    private MultipartFile imgFile;
    private MultipartFile svgFile;
    private MultipartFile voiceFile;

    private String imgPath;
    private String svgPath;
    private String voicePath;
}
