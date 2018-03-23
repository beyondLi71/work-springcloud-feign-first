package com.beyondli.rest;

import com.beyondli.service.FeignOneService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by beyondLi
 * Date 2018/3/15
 * Desc .S
 */
@RefreshScope
@RestController
@RequestMapping(value = "/one")
public class FeignOneRest {
    @Resource
    FeignOneService feignOneService;
    @Value("${title}")
    private String title;
    /**
     * 测试一号是否
     * @return
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "welcome to one";
    }

    /**
     * 调用two
     */
    @RequestMapping(value = "/get/two", method = RequestMethod.GET)
    public String getTwo() {
        String info = feignOneService.getTwo();
        return info;
    }

    /**
     *获取配置文件的信息
     */
    @RequestMapping(value = "/get/config", method = RequestMethod.GET)
    public String getConfig() {
        //return "ok";
        return title;
    }

}
