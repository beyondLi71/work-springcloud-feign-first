package com.beyondli.rest;

import com.beyondli.common.utils.apiresult.AbstractApiResult;
import com.beyondli.dto.user.User;
import com.beyondli.service.FeignOneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * Created by beyondLi
 * Date 2018/3/15
 * Desc .S
 */
@RefreshScope
@RestController
@RequestMapping(value = "/one")
//swagger配置
@Api(value = "FeignOneRest")
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

    /**
     * 测试异常
     */
    @RequestMapping(value = "/test/exc/{state}", method = RequestMethod.GET)
    public AbstractApiResult testExcByState(@PathVariable Integer state) {
        String info = feignOneService.testExcByState(state);
        return AbstractApiResult.success(info);
    }

    /**
     * 调用two测试异常
     */
    @RequestMapping(value = "/get/two/exc", method = RequestMethod.GET)
    public void getTwoExc() {
        feignOneService.getTwoExc();
    }

    /**
     * 测试swagger
     */
    @RequestMapping(value = "/test/swagger", method = RequestMethod.POST)
    @ApiOperation(value = "测试", notes = "通过id获取用户")
    public void testSwagger(@RequestBody User user) {
        System.out.println(user);
    }
}
