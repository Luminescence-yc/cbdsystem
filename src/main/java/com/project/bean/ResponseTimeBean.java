package com.project.bean;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 罗亚辉
 * @ClassName:请求响应时间bean
 * @Description:封装前端需要的数据
 * @date 2019年05月30日 13:27
 */
@Data
@AllArgsConstructor
public class ResponseTimeBean {
    /**
     * 方法中文名
     */
    private String functionPackage;
    /**
     * 方法名
     */
    private String function;
    /**
     * 平均响应时间
     */
    private Integer responseTime;
}
