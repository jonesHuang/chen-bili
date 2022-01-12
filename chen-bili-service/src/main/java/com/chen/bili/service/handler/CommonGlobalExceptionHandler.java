package com.chen.bili.service.handler;

import com.chen.bili.domain.JsonResponse;
import com.chen.bili.domain.exception.ConditionException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ChenYi
 * @corporation HongYang_software
 * @create 2022-01-12
 */

@ControllerAdvice//对controller中被 @RequestMapping注解的方法加一些逻辑处理
@Order(Ordered.HIGHEST_PRECEDENCE)//优先级最高
public class CommonGlobalExceptionHandler {

    //用于标记异常处理类,value标记处理何种类型的异常
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResponse<String> commonExceptionHandler(HttpServletRequest req,Exception e){
        String errorMsg = e.getMessage();
        if (e instanceof ConditionException){
            String errorCode = ((ConditionException)e).getCode();
            return new JsonResponse<>(errorCode,errorMsg);
        }else {
            return new JsonResponse<>("500",errorMsg);
        }

    }

}
