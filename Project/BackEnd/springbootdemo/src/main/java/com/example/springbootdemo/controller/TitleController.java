package com.example.springbootdemo.controller;

import com.example.springbootdemo.pojo.Result;
import com.example.springbootdemo.pojo.Title;
import com.example.springbootdemo.service.TitleService;
import com.example.springbootdemo.utils.FileUtil;
import com.example.springbootdemo.utils.WordExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/title")
//@CrossOrigin(origins = "*")
public class TitleController {

    @Autowired
    public TitleService titleService;

    // 处理预检请求
    @RequestMapping(value = "/add", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/add", consumes = "multipart/form-data")
    public Result addTitle(@RequestParam(name = "imgFile", required = false) MultipartFile imgFile,
                           @RequestParam(name = "svgFile", required = false) MultipartFile svgFile,
                           @RequestParam(name = "voiceFile", required = false) MultipartFile voiceFile,
                           @ModelAttribute Title requestBody){
        if(requestBody.getTitleName().isEmpty())
            return Result.error("题目名称未填");
        else if (requestBody.getId() == null) {
            return Result.error("题目编号未填");
        }
        else if (requestBody.getProjectName().isEmpty()) {
            return Result.error("学科名称未填");
        } else {
            Title title = titleService.findById(requestBody.getId());
            if (title == null) {
                // 处理img文件上传逻辑
                if (imgFile != null && !imgFile.isEmpty()) {
                    try {
                        String imgFilePath = "/data/" + requestBody.getId().toString() + "/" + imgFile.getOriginalFilename();
                        FileUtil.saveFile(imgFile, requestBody.getId().toString());
                        requestBody.setImgPath(imgFilePath);

                    } catch (Exception e) {
                        e.printStackTrace();
                        return Result.error("img文件保存失败");
                    }
                } else {
                    System.out.println("imgFile is empty or not present");
                }

                // 处理svg文件上传逻辑
                if (svgFile != null && !svgFile.isEmpty()) {
                    try {
                        String svgFilePath = "/data/" + requestBody.getId().toString() + "/" + svgFile.getOriginalFilename();
                        FileUtil.saveFile(svgFile, requestBody.getId().toString());
                        requestBody.setSvgPath(svgFilePath);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return Result.error("svg文件保存失败");
                    }
                } else {
                    System.out.println("svgFile is empty or not present");
                }

                // 处理voice文件上传逻辑
                if (voiceFile != null && !voiceFile.isEmpty()) {
                    try {
                        String voiceFilePath = "/data/" + requestBody.getId().toString() + "/" + voiceFile.getOriginalFilename();
                        FileUtil.saveFile(voiceFile, requestBody.getId().toString());
                        requestBody.setVoicePath(voiceFilePath);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return Result.error("voice文件保存失败");
                    }
                } else {
                    System.out.println("voiceFile is empty or not present");
                }

                // 调用服务层方法保存 Title 对象
                titleService.addTitle(requestBody);

                return Result.success("题目已成功加入题库");
            } else {
                // 已经被加入
                return Result.error("题目已存在");
            }

        }


    }

    @PostMapping(value = "/addModel", consumes = "multipart/form-data")
    public Result addModel(@RequestParam(name = "docxFile", required = true) MultipartFile docxFile,
                           @RequestParam(name = "id", required = true) Integer id){
        if (id == null) {
            return Result.error("题目编号未填");
        }
        Title title = titleService.findById(id);
        if(title == null){
            if(docxFile != null && !docxFile.isEmpty()){
                try {
                    String docxFilePath = "/data/" + id.toString() + "/" + docxFile.getOriginalFilename();

                    Title newtitle = FileUtil.processDocxFile(docxFile, id);
                    titleService.addTitle(newtitle);
                    return Result.success("docx文件保存成功");

                } catch (Exception e) {
                    e.printStackTrace();
                    return Result.error("docx文件保存失败");
                }
            }
            else {
                return Result.error("docxFile is empty or not present");
            }
        }
        else {
            return Result.error("题目已存在");
        }


    }


    @GetMapping("/getAll")
    public Result getAllTitles(){
        return Result.success(titleService.getAllTitles());
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteTitle(@PathVariable Integer id) {
        if (id == null) {
            return Result.error("题目编号未提供");
        }

        Title title = titleService.findById(id);
        if (title == null) {
            return Result.error("题目不存在");
        }

        if((title.getImgPath() != null && !title.getImgPath().isEmpty()) || (title.getVoicePath() != null && !title.getVoicePath().isEmpty() )|| (title.getSvgPath() != null) && !title.getSvgPath().isEmpty()){
            // 获取文件路径
            String currentWorkingDirectory = System.getProperty("user.dir");

            // 构建目标文件路径
            Path directory = Paths.get(currentWorkingDirectory, "/src/main/resources/static/data/" + title.getId().toString());


            boolean fileDeleted = FileUtil.deleteFolder(new File(directory.toString()));

            if (!fileDeleted) {
                return Result.error("文件删除失败");
            }
        }



        titleService.deleteTitle(id);


        return Result.success("题目删除成功");
    }



    @GetMapping("/getById/{id}")
    public Result getTitleById(@PathVariable Integer id) {
        if (id == null) {
            return Result.error("题目编号未提供");
        }

        Title title = titleService.findById(id);
        if (title == null) {
            return Result.error("题目不存在");
        }


        return Result.success(title);
    }
    @PostMapping("/exportWord/{id}")
    public Result exportTitleToWord(@PathVariable Integer id) {
        Title title = titleService.findById(id);
        if (title == null) {
            return Result.error("题目不存在");
        }

        WordExporter.exportTitleToWord(title);

        return Result.success("导出成功");
    }
}
