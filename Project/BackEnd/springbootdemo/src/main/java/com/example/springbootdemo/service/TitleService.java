package com.example.springbootdemo.service;


import com.example.springbootdemo.pojo.Title;

import java.util.List;

public interface TitleService {


    Title findByName(String titleName);
    Title findById(Integer id);

    void addTitle(Title title);

    List<Title> getAllTitles();

    void deleteTitle(Integer id);

    void updateTitle(Title requestBody);
}
