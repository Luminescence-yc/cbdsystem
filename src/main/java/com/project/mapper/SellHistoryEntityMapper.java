package com.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.SellHistoryEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: SellHistoryEntityMapper
 * @Description: 出售、购买交易历史记录 Mapper
 * @date 2019年06月01日 11:22
 */
public interface SellHistoryEntityMapper extends BaseMapper<SellHistoryEntity> {

    /**
     * 根据当前用户ID  查询当前用户的所有出售历史交易记录
     * @param personalId 当前用户ID
     * @param page
     * @return 出售历史记录 对象集合
     * @throws Exception
     */
    @Select("select * from t_sellHistory where  sellPersonalId = #{PersonalId} or buyPersonalId = #{PersonalId}")
    @Results({
            @Result(property = "sellPersonal",column = "sellPersonalId",one = @One(select = "com.project.mapper.PersonalEntityMapper.getPersonalAndUserByAll")),
            @Result(property = "buyPersonal",column = "buyPersonalId",one = @One(select = "com.project.mapper.PersonalEntityMapper.getPersonalAndUserByAll"))
    })
    List<SellHistoryEntity> findSellHistoryByPersonalId(@Param("PersonalId") int personalId, Page<SellHistoryEntity> page) throws Exception;

    /**
     * 添加 个人出售历史记录
     * @param sellHistoryEntity 出售历史对象
     * @return 成功返回 1
     * @throws Exception
     */
    @Insert("insert into  t_sellHistory values(null,#{buyPersonal.id},#{sellPersonal.id},#{parkingMessage},#{externalPrice},#{sellDate})")
    int addSellHistory(SellHistoryEntity sellHistoryEntity)throws Exception;

    /**
     *  根据ID查询 购买历史记录
     * @param id 用户购买历史记录
     * @return 出售历史对象
     * @throws Exception
     */
    @Select("select * from t_sellHistory as sell left join t_personal as per on sell.sellPersonalId = per.id where sell.id=#{id} ")
    @Results(value = {
            @Result(property = "buyPersonal",column = "buyPersonalId",one = @One(select = "com.project.mapper.PersonalEntityMapper.getPersonalAndUserByAll")),
            @Result(property = "sellPersonal",column = "sellPersonalId",one = @One(select = "com.project.mapper.PersonalEntityMapper.getPersonalAndUserByAll")),
    })
    SellHistoryEntity findSellByPersonalId(int id)throws Exception;


}