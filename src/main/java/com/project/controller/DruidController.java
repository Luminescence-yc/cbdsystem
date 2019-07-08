package com.project.controller;

import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class DruidController {
    public String git(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat();
        System.out.println(simpleDateFormat.format(new Date()));
        System.out.println("你好啊");
        return null;
    }
}
