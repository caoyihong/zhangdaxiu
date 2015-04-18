package com.jingyuan.zhifeng.core.aop;


import org.springframework.stereotype.Component;

/**
 * dao层以及服务层aop
 * @author cloud_000
 * @version Mar 11, 2015
 */
@Component("AllAop")
public class TestAop {

    public void beforeDao()
    {
        System.out.println("=========beforeDao=========");
    }

    public void afterDao()
    {
        System.out.println("=========afterDao==========");
    }

    public void afterService()
    {
        System.out.println("=========afterService==========");
    }

    public void beforeService()
    {
        System.out.println("=========beforeService==========");
    }
}
