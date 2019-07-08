package com.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.entity.PersonalEntity;
import org.apache.ibatis.annotations.*;


/**
 * @author 朱 骞
 * @version v1.0
 * @ClassName: PersonalEntityMapper
 * @Description: 个人用户Mapper
 * @date 2019年05月31日 9:25
 */
public interface PersonalEntityMapper extends BaseMapper<PersonalEntity> {
    /**
     * 动态修改个人用户信息
     * @param personalEntity 个人用户实体类
     * @return 修改的对象
     */
    @Update("<script> UPDATE t_personal " +
            "<set> " +
            "<if test='jobDescription!=null'>jobDescription=#{jobDescription},</if>" +
            "<if test='address!=null'>address=#{address},</if>" +
            "<if test='tel!=null'>tel=#{tel},</if>" +
            "<if test='email!=null'>email=#{email},</if>" +
            "</set> " +
            "WHERE id=#{id}" +
            "</script>")
    public int updatePersonalInfoById( PersonalEntity personalEntity);



    /**
     *投诉次数
     * 每投诉一次  数字加一
     * @param id 用户id
     * @return 0失败，1成功
     */
    @Update("UPDATE t_personal SET complainNum=complainNum+1 WHERE id=#{id}")
    public int updateComplainNumById(int id);
    /**
     * 查询个人用户信息和用户信息
     * @param id 用户id
     * @return 用户和更让人用户对象
     */
    @Select("SELECT * FROM t_personal AS p JOIN t_user AS u ON p.userId=u.id where p.id=#{id}")
    @Results({
            @Result(property = "userEntity", column = "userId",
                    one = @One(select = "com.project.mapper.UserEntityMapper.selectByUserId"))//查询用户方法
    })
    public PersonalEntity getPersonalAndUserByAll(@Param("id") int id);

    /**
     * 根据用户Id查找个人用户Id
     *
     * @param userId 用户Id
     * @return 个人用户Id
     */
    @Select("SELECT id FROM t_personal WHERE userId=#{userId}")
    public int findByUserId(@Param("userId") int userId);
}