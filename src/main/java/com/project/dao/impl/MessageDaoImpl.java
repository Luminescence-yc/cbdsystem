package com.project.dao.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.annotation.ClassType;
import com.project.dao.IMessageDao;
import com.project.entity.MessageEntity;
import com.project.mapper.MessageEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yangcheng
 * @ClassName:
 * @Description:
 * @date 2019年06月03日 10:13
 */
@ClassType(describe = "消息")
@Component
public class MessageDaoImpl implements IMessageDao {
    @Autowired
    private MessageEntityMapper messageEntityMapper;
    @Override
    public int addMessage(MessageEntity messageEntity) throws Exception {
        return messageEntityMapper.addMessage(messageEntity);
    }

    @Override
    public List<MessageEntity> findMssageByPersonalid(Page<MessageEntity> page,int id) throws Exception {
        return messageEntityMapper.findMessageByPersonalid(page, id);
    }
}
