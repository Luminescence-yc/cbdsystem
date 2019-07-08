package com.project.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.MessageEntity;

import java.util.List;

/**
 * @author yangcheng
 * @ClassName:
 * @Description:
 * @date 2019年06月03日 10:10
 */
public interface IMessageDao {
    /**
     * 添加消息
     * @param messageEntity 消息实体类
     * @return 影响行数
     */
    public int addMessage(MessageEntity messageEntity) throws Exception;

    /**
     * 查询消息
     * @param id 用户id
     * @return 返回消息对象集合
     */
    public List<MessageEntity> findMssageByPersonalid(Page<MessageEntity> page,int id) throws Exception;
}
