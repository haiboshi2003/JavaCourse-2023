package com.example.springbootdemo.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class test {
    public static void print(Integer id) throws IOException {
        String currentWorkingDirectory = System.getProperty("user.dir");

        // 构建目标文件路径
        Path directory = Paths.get(currentWorkingDirectory, "/src/main/resources/static/data/" + id);
        if (Files.notExists(directory)) {
            Files.createDirectories(directory);
        }
        // 构建目标文件路径
        Path filePath = directory.resolve(id.toString()+".docx");
        System.out.println("目录: " + filePath.toString());
    }
    public static void main(String[] args) throws IOException {

       print(2);


    }
}
