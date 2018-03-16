package com.beyondli.rest;

import com.beyondli.service.FeignOneService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by beyondLi
 * Date 2018/3/15
 * Desc .S
 */
@RestController
@RequestMapping(value = "/one")
public class FeignOneRest {
    @Resource
    FeignOneService feignOneService;
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

}
