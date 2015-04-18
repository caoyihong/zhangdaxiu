package com.jingyuan.zhifeng.testService;

import static junit.framework.Assert.assertTrue;

import org.junit.Test;

import com.jingyuan.zhifeng.core.constant.UserType;
import com.jingyuan.zhifeng.core.utils.PasswordHelper;
import com.jingyuan.zhifeng.entity.SysAdmin;
import com.jingyuan.zhifeng.service.UserService;

public class TestUserService extends BaseTestService{

	private UserService userService;
	private PasswordHelper passwordHelper;
	@Override
	public void setService() {
		passwordHelper = tx.getBean(PasswordHelper.class);
		userService = tx.getBean(UserService.class);
	}

	@Test
	public void testIsExistUser()
	{
		SysAdmin admin = new SysAdmin("admin", "123","dedede","aaaaaa");
		userService.insertAdmin(admin);
		assertTrue(userService.isExistUser(admin.getName(), "123", UserType.SYSADMIN.getType()) != null);
//		暂时删不掉，需要调查下事务
		userService.deleteAdmin(admin.getId());
	}
	
}
