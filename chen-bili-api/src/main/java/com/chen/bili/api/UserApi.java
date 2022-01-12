package com.chen.bili.api;

import com.chen.bili.api.support.UserSupport;
import com.chen.bili.domain.JsonResponse;
import com.chen.bili.domain.User;
import com.chen.bili.service.UserService;
import com.chen.bili.service.util.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ChenYi
 * @corporation HongYang_software
 * @create 2022-01-12
 */
@RestController
public class UserApi {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSupport userSupport;

    @GetMapping("users")
    public JsonResponse<User> getUserInfo(){
        Long userId = userSupport.getCurrentUserId();
        User user = userService.getUserInfo(userId);
        return new JsonResponse<>(user);
    }


    @GetMapping("/rsa-pks")
    public JsonResponse<String> getRsaPublicKey(){
        String pk=RSAUtil.getPublicKeyStr();
        return new JsonResponse<>(pk);
    }

    @PostMapping("/users")
    public JsonResponse<String> addUser(@RequestBody User user){
        //requestBody的作用是将前端的请求封装
        userService.addUser(user);

        return  JsonResponse.success();
    }

    @PostMapping("/users-tokens")
    public JsonResponse<String> login(@RequestBody User user) throws Exception{
        //requestBody的作用是将前端的请求封装
        String token = userService.login(user);

        return new JsonResponse<>(token);
    }

}
