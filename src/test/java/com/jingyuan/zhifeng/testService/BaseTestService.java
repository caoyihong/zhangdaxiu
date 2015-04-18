package com.jingyuan.zhifeng.testService;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public abstract class BaseTestService {

	public static ClassPathXmlApplicationContext tx;
	public static SqlSessionFactory sqlSessionFactory;
    @BeforeClass
    public static void setUp() throws Exception {
        String[] paths = new String[] { "classpath:applicationContext.xml" };
        tx = new ClassPathXmlApplicationContext(paths);
        sqlSessionFactory = (SqlSessionFactory)tx.getBean("sqlSessionFactory");
        
    }

    @AfterClass
    public static void tearDown() throws Exception {
        tx.close();
    }
    
    @Before
    public abstract void setService();
}
