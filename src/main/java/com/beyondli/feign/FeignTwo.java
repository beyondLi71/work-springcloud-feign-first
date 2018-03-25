package com.beyondli.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by beyondLi
 * Date 2018/3/16
 * Desc .
 */
@FeignClient("feign-two")
public interface FeignTwo {
    @RequestMapping(value = "/two/hello", method = RequestMethod.GET)
    public String hello();

    /**
     * 调用two测试异常
     */
    @RequestMapping(value = "/two/hello/exc", method = RequestMethod.GET)
    void helloExc();
}
