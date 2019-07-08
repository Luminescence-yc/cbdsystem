package com.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.RentHistoryEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: RentHistoryEntityMapper
 * @Description: 出租、租赁交易历史记录 Mapper
 * @date 2019年06月01日 11:22
 */
public interface RentHistoryEntityMapper extends BaseMapper<RentHistoryEntity> {

    /**
 * 查询，个人用户所有 出租历史记录
 * @param personalId  当前登录用户ID
 * @param page  分页对象
 * @return 出租历史记录 对象集合
 * @throws Exception
 */
@Select("select * from t_rentHistory where rentPersonalId = #{rentPersonalId}")
@Results({
        @Result(property = "rentPersonal",column = "rentPersonalId",one=@One(select = "com.project.mapper.PersonalEntityMapper.getPersonalAndUserByAll")),
        @Result(property = "hirePersonal",column = "hirePersonalId",one=@One(select = "com.project.mapper.PersonalEntityMapper.getPersonalAndUserByAll"))
})
List<RentHistoryEntity> showRentOutHistoryByRentOutPersonalId(@Param("rentPersonalId") int personalId, Page<RentHistoryEntity> page);

    /**
     * 查询，个人用户所有 租赁历史记录
     * @param personalId  当前登录用户ID
     * @param page 分页对象
     * @return 出租历史记录 对象集合
     * @throws Exception
     */
    @Select("select * from t_rentHistory where hirePersonalId = #{hirePersonalId}")
    @Results({
            @Result(property = "rentPersonal",column = "rentPersonalId",one=@One(select = "com.project.mapper.PersonalEntityMapper.getPersonalAndUserByAll")),
            @Result(property = "hirePersonal",column = "hirePersonalId",one=@One(select = "com.project.mapper.PersonalEntityMapper.getPersonalAndUserByAll"))
    })
    List<RentHistoryEntity> showLeaseHistoryByLeasePersonalId(@Param("hirePersonalId") int personalId, Page<RentHistoryEntity> page);

    /**
     * 添加 个人出租历史记录
     * @param rentHistoryEntity 出租历史对象
     * @return 成功返回 1
     * @throws Exception
     */
    @Insert("insert into t_rentHistory values(null,#{rid},#{hid},#{entity.address},#{entity.areaNum},#{entity.parkingNum},#{entity.rentStartTime},#{entity.rentEndTime})")
    int addRentHistory(@Param("entity") RentHistoryEntity rentHistoryEntity,@Param("rid") int rentPersonalId,@Param("hid") int hirePersonalId);

    /**
     *  根据ID查询 出租历史记录
     * @param id 用户出租历史记录 ID
     * @return 出租对象
     */
    @Select("select * from t_rentHistory as rent left join t_personal as per on rent.rentPersonalId = per.id where rent.id=#{id}")
    @Results(value = {
            @Result(property = "rentPersonal",column = "rentPersonalId",one = @One(select = "com.project.mapper.PersonalEntityMapper.getPersonalAndUserByAll"))
    })
    RentHistoryEntity findByPersonalId(int id);

    /**
     *  根据ID查询 租赁历史记录
     * @param id 用户租赁历史记录 ID
     * @return 出租对象
     */
    @Select("select * from t_rentHistory as rent left join t_personal as per on rent.hirePersonalId = per.id where rent.id=#{id}")
    @Results(value = {
            @Result(property = "hirePersonal",column = "hirePersonalId",one = @One(select = "com.project.mapper.PersonalEntityMapper.getPersonalAndUserByAll"))
    })
    RentHistoryEntity findByHirePersonalId(int id);

}