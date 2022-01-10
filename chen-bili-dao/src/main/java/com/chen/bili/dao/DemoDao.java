package com.chen.bili.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author ChenYi
 * @corporation HongYang_software
 * @create 2022-01-10
 */
@Mapper
public interface DemoDao {

    public Long queryId(long id);
}
