package com.project.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.ResponseTimeBean;
import com.project.dao.IResponseTimeDao;
import com.project.entity.ResponseTimeEntity;
import com.project.mapper.ResponseTimeEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 罗亚辉
 * @ClassName:
 * @Description:
 * @date 2019年05月31日 9:09
 */
@Repository
public class ResponseTimeDaoImpl implements IResponseTimeDao {
    @Autowired
    ResponseTimeEntityMapper responseTimeEntityMapper;


    @Override
    public int addResponseTime(ResponseTimeEntity resp) {
        return responseTimeEntityMapper.insert(resp);
    }

    @Override
    public List<ResponseTimeBean> findAVGResponseTimeByNum(int num) {
        return responseTimeEntityMapper.findAVGResponseTimeByNum(num);
    }

    @Override
    public IPage<ResponseTimeEntity> findResponseTimeByFunctionName(String function, Page<ResponseTimeEntity> page) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("function",function);
        return responseTimeEntityMapper.selectPage(page,queryWrapper);
    }

}
