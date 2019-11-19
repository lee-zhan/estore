package com.briup.apps.cms.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;

/**
 * @program: app01
 * @description: 统一异常处理类
 * @author: charles
 * @create: 2019-03-13 21:03
 **/

//拦截/通知器。将错误信息拦截后进行封装
/**
 * 如果controller发生异常的时候
 * 这里进行对异常的拦截
 * 将异常进行封装成自己定义的格式
 * 最后返回
 * */
@RestControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(value =  Exception.class) // 捕获 Controller 中抛出的指定类型的异常，也可以指定其他异常
    public <E> Message handler(Exception exception){
        exception.printStackTrace();
        //封装异常信息
        if(exception instanceof CustomerException) {
        	return MessageUtil.error(exception.getMessage());
        }
        return MessageUtil.error("后台接口异常");
    }
}
