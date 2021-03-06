package com.beyondli.service;

import com.beyondli.common.tools.exception.ExceptionManager;
import com.beyondli.dto.user.User;
import com.beyondli.feign.FeignTwo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;

/**
 * Created by beyondLi
 * Date 2018/3/16
 * Desc .
 */
@Service
public class FeignOneServiceImpl implements FeignOneService {
    @Resource
    FeignTwo feignTwo;
    @Resource
    ExceptionManager exceptionManager;
    /**
     * 调用two
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getTwo() {
        String info = feignTwo.hello();
        return info;
    }

    /**
     * 测试异常
     * @param state
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String testExcByState(Integer state) {
        if (Objects.equals(state, 0)) {
            throw exceptionManager.createByCode("TEST_0001");
        }
        return "success";
    }

    /**
     * 调用two测试异常
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void getTwoExc() {
        feignTwo.helloExc();
    }

    /**
     * 测试swagger
     * @param user
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testSwagger(User user) {
        System.out.println(user.toString());
    }
}
