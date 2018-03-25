package com.beyondli.service;

/**
 * Created by beyondLi
 * Date 2018/3/16
 * Desc .
 */
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
}
