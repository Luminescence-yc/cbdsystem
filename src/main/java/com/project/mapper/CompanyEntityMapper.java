package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CompanyBean;
import com.project.entity.CompanyEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 企业用户Mapper
 */
public interface CompanyEntityMapper extends BaseMapper<CompanyEntity> {
    @Select("select * from t_company where username=#{username}")
    CompanyEntity findCompanyByUsername(@Param("username") String username);

    @Select("select id from t_company where userId=#{userId}")
    int findByUserId(@Param("userId") int userId);

    @Select("select * from t_company where id=#{id}")
    @Results({
            @Result(property = "userEntity", column = "userId",
                    one = @One(select = "com.project.mapper.UserEntityMapper.selectByUserId"))//查询用户方法
    })
    CompanyEntity findCompanyById(@Param("id") int id);

    @Insert("insert into t_company  values (null,#{userEntity.id},#{userEntity.username},#{companyName},#{floor},#{contactPerson},#{tel});")
    int addCompany(CompanyEntity companyEntity);

    @Update(
            {"<script>update t_company " +
                    "<set>" +
                    "<if test='username!=null'>username=#{username},</if>" +
                    "<if test='contactPerson!=null'>contactPerson=#{contactPerson},</if>" +
                    "<if test='tel!=null'>tel=#{tel}</if>" +
                    "</set>" +
                    " where id=#{id}" +
                    "</script>"})
    int updateCompany(CompanyEntity companyEntity);

    @Delete("delete from t_company where id=#{id}")
    int deleteCompany(@Param("id") int id);

    @Select({"<script>SELECT * FROM t_company AS c JOIN t_user AS u  ON c.userId=u.id" +
            "<where>" +
            "<if test='condition.companyName!=null'>" +
            "c.companyName like concat('%',#{condition.companyName},'%') </if>" +
            "<if test='condition.floor!=null'>" +
            " and c.floor like concat('%',#{condition.floor},'%') </if>" +
            "<if test='condition.contactPerson!=null'>" +
            " and c.contactPerson like concat('%',#{condition.contactPerson},'%') </if>" +
            "<if test='condition.tel!=null'>" +
            "and c.tel like concat('%',#{condition.tel},'%') </if>" +
            "<if test='condition.username!=null'>" +
            " and u.username like  concat('%',#{condition.username},'%') </if>" +
            "</where></script>"})
    @Results({
            @Result(property = "userEntity", column = "userId",
                    one = @One(select = "com.project.mapper.UserEntityMapper.selectByUserId"))//查询用户方法
    })
    List<CompanyEntity> findCompanyByCondition(Page<CompanyEntity> page, @Param("condition") Map<String, String> condition);
}