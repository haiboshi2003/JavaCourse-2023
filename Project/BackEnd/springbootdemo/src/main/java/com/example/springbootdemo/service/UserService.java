package com.example.springbootdemo.service;

import com.example.springbootdemo.pojo.User;

public interface UserService {
    User findById(Integer id);

    void register(String username, Integer id, String password);
}
