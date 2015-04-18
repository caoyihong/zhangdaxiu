package com.jingyuan.zhifeng.core.utils;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;

public class MessageUtil {

//	线程安全
	private static Map<String, CodePoll> codes = new HashMap<String, CodePoll>();

//	随机生成6位数的验证码
	public static int code() {
		int code = RandomUtils.nextInt(0, 999999);
		if (code > 99999)
			return code;
		return code();
	}

//	发送 
	public static int send(String mobile, int times) {
		int code = code();
		String Contentsingle = "您正在使用知锋网手机校验, 请确定本人操作, 校验码:" + code
				+ ", 拒收消息回N【知锋网】";
		String username = "shuyy1";
		String password = "dh8rec";
		String SendTime = "";
		String AppendID = "";
		String DesMobilesingle[] = { mobile };
		GuoDu gd = new GuoDu();
		/* post方式发送单条消息 */
		String singleResponse = gd.postSendMsg(username, password,
				Contentsingle, DesMobilesingle, AppendID, SendTime);
//		System.out.println("返回码："+singleResponse);
		if (singleResponse.substring(0, 2).equals("03")) {
			return code;
		} else {
			times++;
			if (times > 3)
				return 0;
			return send(mobile, times);
		}
		// HttpPost post = new HttpPost("http://119.145.9.12/sendSMS.action");
		// List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		// nvps.add(new BasicNameValuePair("enterpriseID", "10400"));
		// nvps.add(new BasicNameValuePair("loginName", "admin"));
		// nvps.add(new BasicNameValuePair("password",
		// "5ed1d012f077c575df98713d4c6e4707"));
		// nvps.add(new BasicNameValuePair("content", content));
		// nvps.add(new BasicNameValuePair("mobiles", mobile));
		//
		// HttpEntity entity = new UrlEncodedFormEntity(nvps, Consts.UTF_8);
		// post.setEntity(entity);
		// try{
		// client.execute(post);
		// post.releaseConnection();
		// return code;
		// }catch(Exception e){
		// times ++;
		// if(times>3) return 0;
		// return send(mobile, times);
		// }
	}

	public static int send(String mobile) {
		return send(mobile, 0);
	}

	public static CodePoll getCode(String phone) {
		return codes.get(phone);
	}

	public static void removeCodePoll(String phone) {
		codes.remove(phone);
	}

	public static String checkSetCode(String phone) {
		CodePoll codePoll = getCode(phone);
		if (codePoll.times > 10) {
			return "验证码发送频率过大, 请过1~3小时再试!";
		}

		int code = send(phone);
		long outtime = System.currentTimeMillis() + 5 * 60 * 1000;
		CodePoll codePoll1 = new CodePoll(codePoll.getTimes() + 1, outtime,
				code);
		setCode(phone, codePoll1);
		return "验证码已发送, 请注意查收";
	}

	public static void setCode(String phone, CodePoll codePoll1) {
		synchronized (codes) {
			codes.put(phone, codePoll1);
		}
	}

	public static class CodePoll {
		private int times;
		private long outtime;
		private int code;

		public CodePoll(int times, long outtime, int code) {
			super();
			this.times = times;
			this.outtime = outtime;
			this.code = code;
		}

		public int getTimes() {
			return times;
		}

		public void setTimes(int times) {
			this.times = times;
		}

		public long getOuttime() {
			return outtime;
		}

		public void setOuttime(long outtime) {
			this.outtime = outtime;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}
	}
}
