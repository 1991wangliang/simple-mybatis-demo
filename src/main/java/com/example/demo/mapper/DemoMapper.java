package com.example.demo.mapper;

import com.codingapi.simplemybatis.mapper.SimpleMapper;
import com.codingapi.simplemybatis.mapper.TreeMapper;
import com.example.demo.entity.Demo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DemoMapper extends SimpleMapper<Demo>, TreeMapper<Demo,Long> {

    @Select("select name,super_id from t_demo where id=1 and 1=1")
    <T> List<T> find(Class<T> clazz);

}
