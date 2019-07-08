package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.entity.OnlineUserEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OnlineUserEntityMapper extends BaseMapper<OnlineUserEntity> {
    @Select("SELECT id,countDate,countTime,onlineUserNum FROM t_onlineuser AS o1 WHERE 1>(SELECT COUNT(*) FROM t_onlineuser WHERE countDate=o1.countDate AND onlineUserNum>o1.onlineUserNum) GROUP BY countDate")
    List<OnlineUserEntity> findAllDayMaxOnlineUser();

    }