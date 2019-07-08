package com.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.bean.LogRequestBean;
import com.project.entity.LogEntity;
import com.project.mapper.LogEntityMapper;
import com.project.service.ILogService;
import org.springframework.stereotype.Service;

/**
 * @author 罗亚辉
 * @date 2019/5/30 9:58
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogEntityMapper, LogEntity> implements ILogService {
    @Override
    public IPage<LogEntity> findLogByPageAndCondition(LogRequestBean condition) {
        QueryWrapper<LogEntity> queryWrapper=new QueryWrapper<>();
        if (condition.getLogType()!=null)
            queryWrapper.eq("logType",condition.getLogType());
        if (condition.getLogBelong()!=null)
            queryWrapper.eq("logBelong",condition.getLogBelong());
        if (condition.getIp()!=null)
            queryWrapper.eq("ip",condition.getIp());
        if (condition.getUsername()!=null)
            queryWrapper.like("username",condition.getUsername());
        if (condition.getStartTime()!=null)
            queryWrapper.gt("operationTime",condition.getStartTime());
        if (condition.getEndTime()!=null)
            queryWrapper.lt("operationTime",condition.getEndTime());
        Page<LogEntity> page=new Page<>(condition.getCurrentPage(),condition.getPageSize());
        return this.baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public int addLog(LogEntity log) {
        return this.baseMapper.insert(log);
    }
}
