package com.chen.bili.api;

import com.chen.bili.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ChenYi
 * @corporation HongYang_software
 * @create 2022-01-10
 */
@RestController
public class DemoApi {
    @Autowired
    private DemoService demoService;

    @GetMapping("/query")
    public Long query(Long id){
        return demoService.query(id);
    }

}
