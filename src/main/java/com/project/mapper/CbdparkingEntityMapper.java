package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.CbdParkingEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName: CbdparkingEntityMapper
 * @Description: cbd车位mapper
 * @date 2019年05月30日 21:45
 */
public interface CbdparkingEntityMapper extends BaseMapper<CbdParkingEntity> {
    @Insert("<script>" +
            "insert into t_cbdparking values" +
            "<foreach collection='list' item='item' index='index' separator=','>" +
            "(null,null,#{cbdParking.externalcontractEntity.id},null,#{cbdParking.address}" +
            ",#{cbdParking.areaNum},#{item},#{cbdParking.finalDate},#{cbdParking.status})" +
            "</foreach>" +
            "</script>")
    int addCbdParking(@Param("cbdParking") CbdParkingEntity cbdParkingEntity, @Param("list") List<Integer> parkingNumList);

    @Select("<script>" +
            " select id,address,areaNum,parkingNum,finalDate,status from " +
            "t_cbdparking " +
            "<where>" +
            "<if test='condition.areaNum!=null'>and areaNum like concat('%',#{condition.areaNum},'%')</if> " +
            "<if test='condition.address!=null'>and address like concat('%',#{condition.address},'%')</if>" +
            "<if test='condition.status!=null'>and status = #{condition.status}</if> " +
            "<if test='condition.companyId!=null'>and companyId = #{condition.companyId}</if> " +
            "</where>" +
            "</script>")
    List<CbdParkingEntity> findCbdParkingByCondition(Page<CbdParkingEntity> page, @Param("condition") Map<String, String> condition);

    @Select("select count(*) from t_cbdparking where externalContractId=#{externalContractId} " +
            "and companyId is null and status=#{status} ")
    int findNoRentCbdParkingByExternalcontractId(@Param("externalContractId") int externalContractId, @Param("status") String status);

    @Select("select distinct(address) from t_cbdparking where status='未租赁';")
    List<String> findAllAddress();

    @Select("<script>" +
            "select distinct(areaNum) from t_cbdparking " +
            "<where>" +
            "status='未租赁'" +
            "<if test='address!=null and address.length!=0'>and address=#{address}</if> " +
            "</where>" +
            "</script>")
    List<String> findAreaByAddress(@Param("address") String address);

    @Select("<script>" +
            "select * from t_cbdparking" +
            "<where>" +
            "status='未租赁' " +
            "<if test='address!=null and address.length!=0'>and address=#{address}</if> " +
            "<if test='areaNum!=null and areaNum.length!=0'>and areaNum=#{areaNum}</if>" +
            "</where>" +
            "</script>")
    List<CbdParkingEntity> findCbdParkingByArea(Page<CbdParkingEntity> page, @Param("address") String address, @Param("areaNum") String area);

    @Update("<script>" +
            "update t_cbdparking set status=#{status},companyId=#{companyId}," +
            "companyContractId=#{companyContractId}  where id in" +
            "<foreach collection='list' item='idList' index='index' open='(' separator=',' close=')'>" +
            "#{idList}" +
            "</foreach>" +
            "</script>")
    int updateParking(@Param("list") List<Integer> list, @Param("companyId") int companyId, @Param("companyContractId") int companyContractId, @Param("status") String status);

    @Update("update t_cbdparking set status=#{status},companyId=null,companyContractId=null " +
            "where companyContractId=#{companyContractId}")
    int updateParkingNo(@Param("companyContractId") int companyContractId, @Param("status") String status);

    @Update("update t_cbdparking set finalDate=#{finalDate} " +
            "where companyContractId=#{companyContractId}")
    int updateParkingFinalDate(@Param("companyContractId") int companyContractId, @Param("finalDate") String finalDate);

    @Select("select id,address,areaNum,parkingNum,finalDate,status from t_cbdparking where address like concat('%',#{address},'%')")
    List<CbdParkingEntity> findCbdParkingByAddress(Page<CbdParkingEntity> page,@Param("address") String address);

}