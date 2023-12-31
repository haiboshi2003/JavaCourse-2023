package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.mapper.TitleMapper;
import com.example.springbootdemo.pojo.Title;
import com.example.springbootdemo.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleServiceImpl implements TitleService {
    @Autowired
    private TitleMapper titleMapper;
    @Override
    public Title findByName(String titleName){
        Title title = titleMapper.findByName(titleName);
        return title;

    }

    @Override
    public Title findById(Integer id) {
        Title title = titleMapper.findById(id);
        return title;
    }


    @Override
    public  void addTitle(Title title){
        titleMapper.addTitle(title);
    }

    @Override
    public List<Title> getAllTitles() {
        return titleMapper.getAllTitles();

    }

    @Override
    public void deleteTitle(Integer id) {
        titleMapper.deleteTitle(id);
    }

    @Override
    public void updateTitle(Title requestBody) {
        titleMapper.updateTitle(requestBody);
    }
}
