package com.project.dao.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.annotation.ClassType;
import com.project.dao.IRentMessageDao;
import com.project.entity.PersonalEntity;
import com.project.entity.RentMessageEntity;
import com.project.mapper.RentMessageEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:租车预定留言
 * @Description:租车预定留言持久层实现类
 * @date 2019年05月31日 17:50
 */
@ClassType(describe = "租车预定留言")
@Component
public class RentMessageDaoImpl implements IRentMessageDao {
    @Autowired
    private RentMessageEntityMapper rentMessageEntityMapper;

    @Override
    public int addRentMessage(RentMessageEntity rentMessageEntity) {
        return rentMessageEntityMapper.insert(rentMessageEntity);
    }

    @Override
    public int deleteRentMessageById(int id) {
        return rentMessageEntityMapper.deleteById(id);
    }

    @Override
    public List<RentMessageEntity> showRentMessageInfo(Page<RentMessageEntity> page, Map<String, String> map) {
        return rentMessageEntityMapper.selectRentMessageInfo(page, map);
    }

    @Override
    public int deleteRentMessageByRentId(int id) {
        return rentMessageEntityMapper.deleteRentMessageByRentId(id);
    }

    @Override
    public List<RentMessageEntity> getRentParkingByAll(int rentId, Page<RentMessageEntity> page, Map<String, String> condition) throws Exception {

        return rentMessageEntityMapper.getRentParkingByAll(rentId, page, condition);
    }
}
