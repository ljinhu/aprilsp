package com.jibolix.message.april.consumer.controller;

import com.jibolix.message.april.consumer.feign.ProFeignClient;
import com.jimbolix.april.common.exception.AprilApplicationException;
import com.jimbolix.april.common.exception.AprilError;
import com.jimbolix.april.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: ruihui.li
 * @Date: 2020/5/26 14:37
 * @Description: 
 */
@RestController
@RequestMapping("/index")
@Slf4j
public class IndexController {

    @Autowired
    private ProFeignClient proFeignClient;

    @GetMapping("/test")
    public Map test() throws Exception{
        log.info("@@@@@@@index接受到请求@@@@@@@@@");
        Map map = proFeignClient.test();
        return map;
    }

    @GetMapping("/ex")
    public R exTest()throws AprilApplicationException{
        log.info("@@@@测试统一异常处理@@@@");
        try{
            int i = Integer.valueOf("qw");
        }catch (Exception e){
            AprilError aprilErro = new AprilError(500,e.getLocalizedMessage());
            throw new AprilApplicationException(aprilErro);
        }
        return R.ok();
    }
}