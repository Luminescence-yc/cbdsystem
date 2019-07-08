package com.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.LogRequestBean;
import com.project.entity.LogEntity;

import java.util.Map;

/**
 * 日志业务接口
 * @author lyh
 * @date 2019/5/30 11:43
 */
public interface ILogService {
    /**
     * 根据条件分页查询日志
     * @param logRequestBean 条件集合
     *                  logType:日志类型,
     *                  username:用户名,
     *                  ip:ip地址,
     *                  startTime:起始日期,
     *                  endTime:结束日期
     * @return 分页对象
     */
    IPage<LogEntity> findLogByPageAndCondition(LogRequestBean logRequestBean);

    /**
     * 添加日志
     * @param log 日志对象
     * @return 添加成功返回1，失败返回0
     *
     * dao层 aop
     */
    int addLog(LogEntity log);
}
