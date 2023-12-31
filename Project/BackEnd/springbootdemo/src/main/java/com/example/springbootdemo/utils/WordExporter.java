package com.example.springbootdemo.utils;

import com.example.springbootdemo.pojo.Title;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.Document;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class WordExporter {
    //导出成docx文件
    public static void exportTitleToWord(Title title) {
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText("[题目编号]：" + title.getId());
            run.addBreak();

            run.setText("[课程名称]：" + title.getProjectName());
            run.addBreak();

            run.setText("[题目名称]：" + title.getTitleName());
            run.addBreak();
            if(title.getDescription() != null && !title.getDescription().isEmpty()){
                run.setText("[题目描述]：" + title.getDescription());
                run.addBreak();
            }


            if(title.getTextData() != null && !title.getTextData().isEmpty()){
                run.setText("[题干]：" + title.getTextData());
                run.addBreak();
            }






            String currentWorkingDirectory = System.getProperty("user.dir");

            // 构建目标文件路径
            Path directory = Paths.get(currentWorkingDirectory, "/src/main/resources/static/data/" + title.getId().toString());
            if (Files.notExists(directory)) {
                Files.createDirectories(directory);
            }
            // 构建目标文件路径
            Path filePath = directory.resolve(title.getId().toString()+".docx");
            System.out.println("目录: " + filePath.toString());

            // 保存图片到 Word 文档中
            if (title.getImgPath() != null && !title.getImgPath().isEmpty()) {
                String imgPath = "static" + title.getImgPath();
                run.setText("[图像展示]：");
                try {
                    InputStream inputStream = WordExporter.class.getClassLoader().getResourceAsStream(imgPath);
                    run.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_JPEG, "image.jpg", Units.toEMU(300), Units.toEMU(200));
                    run.addBreak();
                } catch (IOException e) {
                    e.printStackTrace();
                    // 处理异常
                }
            }

            // 保存 Word 文档
            try (FileOutputStream out = new FileOutputStream(new File(filePath.toUri()))) {
                document.write(out);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

}
