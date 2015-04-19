package com.jingyuan.zhifeng.testDao;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jingyuan.zhifeng.entity.Student;
import com.jingyuan.zhifeng.repository.StudentMapper;
import com.jingyuan.zhifeng.repository.SysAdminMapper;

public class TestStudentMapper {

	public static ClassPathXmlApplicationContext tx;
	public static StudentMapper studentMapper;
    
    @BeforeClass
    public static void setUp() throws Exception {
        String[] paths = new String[] { "classpath:applicationContext.xml" };
        tx = new ClassPathXmlApplicationContext(paths);
        studentMapper = tx.getBean(StudentMapper.class);
        
    }

    @AfterClass
    public static void tearDown() throws Exception {
        tx.close();
    }
    
	@Test
    public void shouldGetTwo()
    {
		System.out.println(studentMapper.selectStus("教育技术学院", "心理学", null));
    }
}
