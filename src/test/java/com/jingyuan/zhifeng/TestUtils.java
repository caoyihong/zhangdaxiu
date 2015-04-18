package com.jingyuan.zhifeng;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.jingyuan.zhifeng.core.constant.UserType;

public class TestUtils {

	@Test
	public void testUserType()
	{
//		assertEquals(UserType.getUrl(2), "/admin");
		System.out.println(UserType.getUrl(2));
	}
}
