package com.kdn.apc.repository;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DataMapper {
    @Select("SELECT sName,iParent,iId FROM tb_map")
    @Results({
            @Result(column = "sName", property = "sName"),
            @Result(column = "iParent", property = "iParent"),
            @Result(column = "iId", property = "iId"),
    })
    List<Node> getAllNode();
    @Select("SELECT sDevId,iId FROM tb_dev")
    @Results({
            @Result(column = "sDevId", property = "sDevId"),
            @Result(column = "iId", property = "iId"),
    })
    List<Dev> gettAllDev();



}
