package com.example.springbootdemo.mapper;

import com.example.springbootdemo.pojo.Title;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TitleMapper {

    @Select("SELECT * FROM title WHERE titleName = #{titleName}")
    Title findByName(String titleName);

    @Insert("INSERT INTO title (id, titleName, projectName, description, textData, imgPath, svgPath, voicePath) " +
            "VALUES (#{id}, #{titleName}, #{projectName}, #{description}, #{textData}, #{imgPath}, #{svgPath}, #{voicePath})")
    void addTitle(Title title);

    @Select("SELECT * FROM title")
    List<Title> getAllTitles();
    @Select("SELECT * FROM title WHERE id=#{id}")
    Title findById(Integer id);

    @Delete("DELETE FROM title WHERE id = #{id}")
    void deleteTitle(Integer id);
    @Update("UPDATE title SET titleName = #{titleName}, projectName = #{projectName}, " +
            "description = #{description}, textData = #{textData}, imgPath = #{imgPath}," +
            " svgPath = #{svgPath}, voicePath = #{voicePath} WHERE id = #{id}")
    void updateTitle(Title requestBody);
}