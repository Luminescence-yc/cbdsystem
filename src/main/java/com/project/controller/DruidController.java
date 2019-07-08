package com.project.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class DruidController {
    public String git(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat();
        System.out.println(simpleDateFormat.format(new Date()));
        System.out.println("我是eclipes");
        return null;
    }
}
