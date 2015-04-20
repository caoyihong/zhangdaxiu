package com.jingyuan.zhifeng;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.google.common.collect.ComparisonChain;
import com.jingyuan.zhifeng.core.constant.UserType;

public class TestUtils {

	@Test
	public void testUserType()
	{
//		assertEquals(UserType.getUrl(2), "/admin");
		System.out.println(UserType.getUrl(2));
	}
	
	@Test
	public void testComparisonChain()
	{
		System.out.println(ComparisonChain.start().compare(1, 1).compare(2, 1).compare("123", "123").result());
		System.out.println(ComparisonChain.start().compare(1, 1).compare(null, 2).result());
	}
	
	
}
