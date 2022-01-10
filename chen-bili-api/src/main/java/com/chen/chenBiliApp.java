package com.chen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author ChenYi
 * @corporation HongYang_software
 * @create 2022-01-10
 */
@SpringBootApplication
public class chenBiliApp {

    public static void main(String[] args) {

        ApplicationContext app = SpringApplication.run(chenBiliApp.class,args);

    }
}
