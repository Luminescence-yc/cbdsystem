package com.project.bean;

import lombok.Data;

/**
 * @author 罗亚辉
 * @ClassName:日志请求参数对象
 * @Description:封装从前端获取的参数列表
 * @date 2019年05月30日 14:57
 */
@Data
public class LogRequestBean {
    /**
     * 当前页
     */
    private int currentPage=1;
    /**
     * 每页条数
     */
    private int pageSize=10;
    /**
     * 用户名
     */
    private String username;
    /**
     * ip地址
     */
    private String ip;
    /**
     * 日志类型
     */
    private String logType;
    /**
     * 日志属性
     */
    private String logBelong;
    /**
     * 起始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
}
