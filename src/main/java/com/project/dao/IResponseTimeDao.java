package com.project.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.ResponseTimeBean;
import com.project.entity.ResponseTimeEntity;

import java.util.List;

/**
 * @author 罗亚辉
 * @ClassName:
 * @Description:
 * @date 2019年05月31日 9:09
 */
public interface IResponseTimeDao {
    /**
     * 添加响应时间
     * @param responseTimeEntity 响应时间对象
     * @return 添加成功返回1，失败返回0
     */
    int addResponseTime(ResponseTimeEntity responseTimeEntity);

    /**
     * 查询所有方法的最近几次平均耗时
     * @param num 统计的次数（例如统计所有方法最近10次执行的平均耗时）
     * @return 方法平均耗时结果集
     */
    List<ResponseTimeBean> findAVGResponseTimeByNum(int num);

    /**
     * 根据方法名查询方法执行耗时
     * @param function 方法名
     * @param page 分页对象
     * @return 该方法的执行耗时分页对象
     */
    IPage<ResponseTimeEntity> findResponseTimeByFunctionName(String function, Page<ResponseTimeEntity> page);


}
