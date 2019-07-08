package com.project.util;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseData {
    private int code;

    private String message="ok";

    private Map<String,Object> data=new HashMap<>();

    public ResponseData() {

    }

    public ResponseData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseData ok(){
        return new ResponseData(200,"ok");
    }

    public static ResponseData notFound(){
        return new ResponseData(404,"not found");
    }

    public static ResponseData unauthorized(){
        return new ResponseData(401,"unauthorized");
    }
}
