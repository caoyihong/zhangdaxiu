package com.jingyuan.zhifeng.core.constant;

public enum UserType {

	STUDENT(0,"student"),TEACHER(1,"teacher"),SYSADMIN(2,"sysadmin");
	
	private final String name;
	
	private final int type;
	
	UserType(int type,String name)
	{
		this.type = type;
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getType()
	{
		return this.type;
	}
	
	public static String getUrl(int type)
	{
		String url = null;
		switch(type)
		{
		case 0: url = "/stu";break;
		case 1: url = "/teach";break;
		case 2: url = "/admin";break;
		default : url = "/";
		}
		return url;
	}
}
