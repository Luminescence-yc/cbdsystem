package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CompanyContractBean;
import com.project.entity.CompanyContractEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Component
public interface CompanycontractEntityMapper extends BaseMapper<CompanyContractEntity> {
    @Insert("insert into t_companycontract values(null,#{companyEntity.id}," +
            "#{contractNum},#{startDate},#{endDate}," +
            "#{companyPrice},#{contractPicture},#{oldContractNum})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int addCompanycontract(CompanyContractEntity contractEntity);

    @Select("select * from t_companycontract where id=#{id};")
    @Results({@Result(column = "companyId", property = "companyEntity", one = @One(select = "com.project.mapper.CompanyEntityMapper.findCompanyById")),
    })
    public CompanyContractEntity selectByContractId(@Param("id") int id);

    /**
     * 用户合约展示及动态查询
     *
     * @param page      分页对象
     * @param condition 动态查询条件
     * @return 租户合约对象集合
     */
    @Select("<script> SELECT cc.id,cc.companyId,cc.contractNum,cc.startDate,cc.endDate,cc.companyPrice,cc.oldContractNum," +
            " c.id as c_id,c.userId,c.username,c.companyName,c.floor,c.contactPerson,c.tel " +
            " FROM t_companycontract as cc  left JOIN  t_company as c " +
            " on cc.companyId=c.id <where> " +
            "<if test='condition.companyName!=null'> and c.companyName like concat('%',#{condition.companyName},'%')</if>" +
            "<if test='condition.contractNum!=null'>and cc.contractNum like concat('%',#{condition.contractNum},'%')</if>" +
            "<if test='condition.companyPrice!=null'> and cc.companyPrice <![CDATA[ >= ]]>  #{condition.companyPrice}</if>" +
            "</where> </script>")
    @Results({
            @Result(property = "companyEntity.id", column = "c_id"),
            @Result(property = "companyEntity.userEntity.id", column = "userId"),
            @Result(property = "companyEntity.username", column = "username"),
            @Result(property = "companyEntity.companyName", column = "companyName"),
            @Result(property = "companyEntity.floor", column = "floor"),
            @Result(property = "companyEntity.contactPerson", column = "contactPerson"),
            @Result(property = "companyEntity.tel", column = "tel"),
    })
    public List<CompanyContractEntity> findByCondition(Page<CompanyContractEntity> page, @Param("condition") Map<String, String> condition);

    @Select("select * from t_companycontract  c join t_cbdParking b on c.id=b.companyContractId where startDate=#{startDate} and endDate=#{endDate}")
    public List<CompanyContractEntity> findByStartAndEndDate(Page<CompanyContractEntity> page,@Param("startDate") String startDate,@Param("endDate")String endDate);



    @Select({"<script>select c.companyId,c.contractNum,c.startDate,c.endDate," +
            "b.address,b.parkingNum " +
            "from t_companycontract c join t_cbdParking b on c.id=b.companyContractId " +
            "<where>" +
            "<if test='condition.startDate!=null'> and c.startDate &gt;= #{condition.startDate} </if>" +
            "<if test='condition.endDate!=null'> and c.endDate &lt;= #{condition.endDate} </if>" +
            " and c.companyId=#{condition.companyId}" +
            "</where></script>"})

    public List<CompanyContractBean>findByCompanyIdAndStartAndEndDate(@Param("page") Page<CompanyContractBean>page, @Param("condition") Map<String,String>condition);

}