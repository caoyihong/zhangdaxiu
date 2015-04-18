package com.jingyuan.zhifeng.testDao;

import static junit.framework.Assert.assertTrue;
import junit.framework.Assert;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jingyuan.zhifeng.entity.SysAdmin;
import com.jingyuan.zhifeng.repository.SysAdminMapper;

public class TestAdminMapper {
	public static ClassPathXmlApplicationContext tx;
	public static SysAdminMapper adminMapper;
    
    @BeforeClass
    public static void setUp() throws Exception {
        String[] paths = new String[] { "classpath:applicationContext.xml" };
        tx = new ClassPathXmlApplicationContext(paths);
        adminMapper = tx.getBean(SysAdminMapper.class);
        
    }

    @AfterClass
    public static void tearDown() throws Exception {
        tx.close();
    }
    
    @Test
    public void shouldGetDatas()
    {
    	SysAdmin admin = new SysAdmin("admin", "123","dedede","aaaaaa");
    	adminMapper.insert(admin);
    	assertTrue(adminMapper.selectByAdmin(new SysAdmin("admin", "123")) != null);
    	adminMapper.deleteByPrimaryKey(admin.getId());
    }
}
