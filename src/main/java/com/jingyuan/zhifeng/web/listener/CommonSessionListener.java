package com.jingyuan.zhifeng.web.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class CommonSessionListener implements SessionListener{
	
	@Override
	public void onStart(Session paramSession) {
		
		System.out.println("创建会话：" + paramSession.getId());
		
	}

	@Override
	public void onStop(Session paramSession) {
		System.out.println("会话停止：" + paramSession.getId());
	}

	@Override
	public void onExpiration(Session paramSession) {
		
		System.out.println("会话过期：" + paramSession.getId());
	}
	
}
