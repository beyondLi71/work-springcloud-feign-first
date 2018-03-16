package com.beyondli.service;

import com.beyondli.feign.FeignTwo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by beyondLi
 * Date 2018/3/16
 * Desc .
 */
@Service
public class FeignOneServiceImpl implements FeignOneService {
    @Resource
    FeignTwo feignTwo;
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
}
