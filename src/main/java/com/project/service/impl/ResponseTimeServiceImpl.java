package com.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.ResponseTimeBean;
import com.project.dao.IResponseTimeDao;
import com.project.entity.ResponseTimeEntity;
import com.project.service.IResponseTimeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 罗亚辉
 * @ClassName:
 * @Description:
 * @date 2019年05月31日 9:07
 */
@Service
public class ResponseTimeServiceImpl implements IResponseTimeService {
    @Autowired
    IResponseTimeDao responseTimeDao;


    @Override
    public int addResponseTime(ResponseTimeEntity responseTimeEntity) {
        return responseTimeDao.addResponseTime(responseTimeEntity);
    }

    @Override
    public List<ResponseTimeBean> findAVGResponseTimeByNum(int num) {
        return responseTimeDao.findAVGResponseTimeByNum(num);
    }

    @Override
    public IPage<ResponseTimeBean> findResponseTimeByFunctionName(String functionName, Page<ResponseTimeEntity> page) {
        IPage<ResponseTimeEntity> page1=responseTimeDao.findResponseTimeByFunctionName(functionName, page);
        IPage<ResponseTimeBean> page2=new Page<>();
        BeanUtils.copyProperties(page1,page2);
        return page2;
    }
}
