package com.example.springbootdemo.controller;

import com.example.springbootdemo.pojo.Result;
import com.example.springbootdemo.pojo.User;
import com.example.springbootdemo.service.UserService;
import com.example.springbootdemo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController

@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;
    @PostMapping("/register")

    public Result register(@RequestBody User requestBody){
        User u = userService.findById(requestBody.getId());
        if(u == null){
            //没有被占用
            //注册
            userService.register(requestBody.getUsername(), requestBody.getId(), requestBody.getPassword());
            return Result.success();
        }
        else{
            //已经注册
            return Result.error("已被注册");
        }


    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody User requestBody){
        //根据id查询用户
        User u = userService.findById(requestBody.getId());
        //判断用户是否存在
        if(u == null){
            return Result.error("用户不存在");
        }

        //判断密码是否正确
        if(requestBody.getPassword().equals(u.getPassword())){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getId());
            claims.put("name", u.getUsername());
            String token = JwtUtil.genToken(claims);

            System.out.print("token:" + token);

            return Result.success(token);
        }

        return Result.error("密码错误");
    }
}
