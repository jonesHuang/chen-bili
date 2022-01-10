package com.chen.bili.service;

import com.chen.bili.dao.DemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ChenYi
 * @corporation HongYang_software
 * @create 2022-01-10
 */
@Service
public class DemoService {

    @Autowired
    private DemoDao demoDao;

    public Long query(Long id){
        return demoDao.queryId(id);
    }
}
