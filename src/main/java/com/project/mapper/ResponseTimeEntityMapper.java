package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.bean.ResponseTimeBean;
import com.project.entity.ResponseTimeEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ResponseTimeEntityMapper extends BaseMapper<ResponseTimeEntity> {
    /**
     * 查询所有方法的最近几次平均耗时
     * @param num 统计的次数（例如统计所有方法最近10次执行的平均耗时）
     *      解析SQL：
     *      #{num}>...
     *      目的是获取前几条数据，可以多次修改
     *      where FUNCTION=r.FUNCTION
     *      目的是确定分组字段
     *      id>r.id
     *      排序条件
     * @return 方法平均耗时结果集
     */
    @Select("SELECT functionPackage,FUNCTION,AVG(responseTime) FROM t_responsetime AS r WHERE #{num} >(SELECT COUNT(*) FROM t_responsetime WHERE FUNCTION=r.FUNCTION AND id>r.id) GROUP BY FUNCTION ")
    List<ResponseTimeBean> findAVGResponseTimeByNum(int num);
}