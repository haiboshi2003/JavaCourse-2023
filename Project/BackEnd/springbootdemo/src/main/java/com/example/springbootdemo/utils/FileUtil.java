package com.example.springbootdemo.utils;

import com.example.springbootdemo.pojo.Title;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Objects;

public class FileUtil {
    public static void saveFile(MultipartFile file, String myPath) throws Exception {
        try {
            String currentWorkingDirectory = System.getProperty("user.dir");

            // 构建目标文件路径
            Path directory = Paths.get(currentWorkingDirectory, "/src/main/resources/static/data/" + myPath);
            if (Files.notExists(directory)) {
                Files.createDirectories(directory);
            }

            // 构建目标文件路径
            Path filePath = directory.resolve(Objects.requireNonNull(file.getOriginalFilename()));
            System.out.println("目录: " + filePath.toString());

            // 将文件保存到指定路径
            file.transferTo(filePath.toFile());
        } catch (Exception e) {
            throw new Exception("文件保存失败", e);
        }
    }

    public static Title processDocxFile(MultipartFile docxFile, Integer id) throws IOException {
        Title title = new Title();
        title.setId(id);

        try (InputStream inputStream = docxFile.getInputStream()) {
            XWPFDocument document = new XWPFDocument(inputStream);

            // 遍历文档中的段落
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                String text = paragraph.getText();

                // 根据文档内容的结构，逐段解析并赋值给 Title 对象的属性
                String trim = text.substring(text.indexOf(":") + 1);
               if (text.startsWith("[学科]:")) {
                    // 解析学科
                    title.setProjectName(trim);
                } else if (text.startsWith("[题目名称]:")) {
                    // 解析题目名称
                    title.setTitleName(trim);
                } else if (text.startsWith("[题目描述]:")) {
                    // 解析题目描述
                    title.setDescription(trim);
                } else if (text.startsWith("[题干]:")) {
                    // 解析题干
                    title.setTextData(trim);

                }

            }
        }

        return title;


    }
    public static boolean deleteFolder(File folder) {
        if (folder == null || !folder.exists()) {
            return false; // 文件夹不存在
        }

        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // 递归删除子文件夹
                    deleteFolder(file);
                } else {
                    // 删除文件
                    if (!file.delete()) {
                        return false; // 删除失败
                    }
                }
            }
        }

        // 删除空文件夹
        return folder.delete();
    }
}
