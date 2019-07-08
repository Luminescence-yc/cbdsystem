package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.CountEntity;
import com.project.entity.PersonalBillEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: PersonalBillEntityMapper
 * @Description: 个人账单 Mapper
 * @date 2019年06月01日 11:22
 */
public interface PersonalBillEntityMapper extends BaseMapper<PersonalBillEntity> {

    /**
     * 动态查询个人账单信息
     * @param condition 查询条件
     * @param page 分页对象
     * @return 个人账单对象集合
     * @throws Exception
     */
    @Select("<script> " +
            "select id as id," +
            "        dealDate as dealDate," +
            "        dealTime as dealTime," +
            "        expend as expend, " +
            "        income as income," +
            "        remark as remark from t_personalBill " +
            " <where>" +
            " personalId=#{condition.personalId}" +
            "<if test='condition.startTime !=null and condition.endTime == null'>" +
            " dealDate  &gt;= #{condition.startTime} " +
            "</if>" +
            "<if test='condition.startTime==null and condition.endTime != null'>" +
            " dealDate  &lt;= #{condition.endTime} " +
            "</if>" +
            "<if test='condition.startTime!=null and condition.endTime != null'>" +
            " dealDate &gt;= #{condition.startTime} and dealDate &lt;= #{condition.endTime} " +
            "</if>" +
            "</where></script>")
    List<PersonalBillEntity> findPersonalBillInfoByCondition(Page<PersonalBillEntity> page,@Param("condition") Map<String, String> condition) throws Exception;

    /**
     * 统计 个人交易总次数、交易总支出、交易总收入
     * @param personalId 用户ID
     * @return 统计对象
     * @throws Exception
     */
    @Select("select count(id) as dealNum,sum(expend) as expenditure ,sum(income) as generalIncome from t_personalBill where personalId = #{personalId} ")
    CountEntity countPersonal(int personalId)throws Exception;

    /**
     * 添加用户 交易记录
     * @param personalBillEntity 用户对象
     * @return 添加成功返回 1
     * @throws Exception
     */
    @Insert("insert into t_personalBill values(null,#{personalId},#{dealDate},#{dealTime},#{expend},#{income},#{remark})")
    int addUserBill(PersonalBillEntity personalBillEntity)throws Exception;
}