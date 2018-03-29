package com.beyondli.service;

import com.beyondli.dto.user.User;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * Created by beyondLi
 * Date 2018/3/16
 * Desc .
 */
//校验注解
@Validated
public interface FeignOneService {

    /**
     * 调用two
     * @return
     */
    String getTwo();

    /**
     * 测试异常
     * @param state
     * @return
     */
    String testExcByState(Integer state);

    /**
     * 调用two测试异常
     */
    void getTwoExc();

    /**
     * 测试swagger
     * @param user
     */
    void testSwagger(@Valid User user);
}
