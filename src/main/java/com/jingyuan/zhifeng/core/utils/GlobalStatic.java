package com.jingyuan.zhifeng.core.utils;
/**
 * 全局的静态变量,用于全局变量的存放
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2013-03-19 11:08:15
 * @see org.springrain.frame.util.GlobalStatic
 */
public class GlobalStatic {
	public static final String tempRootpath = System.getProperty("user.dir") + "/temp/";
	public static final int excelPageSize=1000;
	public static final  String suffix=".html";
	public static final String excelext=".xls";
	public static final String exportexcel="exportexcel";//是否是导出操作的key
	public static final String dataUpdate="更新";
	public static final String dataSave="保存";
	public static final String dataDelete="删除";
	public static final String cacheKey="springraincache";
	public static final String qxCacheKey="springrainqxcache";
	public static final String tableExt="_history_";
	public static final String frameTableAlias="frameTableAlias";
	public static final String pageurlName="pageurlName";
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String WARNING = "warning";
	public static final String FORMTOKEN = "formToken";
	public static final int RECOMMEND_SIZE = 4;
	public static final String RETURNDATAS = "returndatas";
	public static final int USER_PERSON = 0;
	public static final int USER_ENTERPRISE = 1;
	public static final int USER_AGENCYE = 2;
	public static final int USER_AGENCY = 3;
	public static final int ENABLED_TRUE = 1;
	public static final int ENABLED_FALSE = 0;
	//用户状态
	public static final int USER_NAUTHEN = 0;	//用户未认证
	public static final int USER_AUTHEN = 1;	//用户已认证
	public static final int USER_FROZEN = 2;	//用户冻结
	//订单状态
	public static final int ORDER_CREATE = 0;
	public static final int ORDER_ACCEPT = 1;
	public static final int ORDER_COMPLETE = 2;
	public static final int ORDER_ISSUE = 3;
	//信息是否已读
	public static final int Msg_NREAD = 0;
	public static final int Msg_READ = 1;
	
	/**
	 * JPQL-SQL
	 */
	public static final int TYPE_JPQL = 2;
	/**
	 * 原生SQL
	 */
	public static final int TYPE_NATIVE = 1;
	//上传文件的路径
	public static final String uploadpath = "C:/zhifeng/";
	public static final String SAVED_REQUEST_KEY = "shiroSavedRequest";

	//认证
	//public static final String reloginsession="shiro-reloginsession";
	//认证
	public static final String authenticationCacheName="shiro-authenticationCacheName";
	//授权
	public static final String authorizationCacheName="shiro-authorizationCacheName";
	//realm名称
	public static final String authorizingRealmName="shiroDbAuthorizingRealmName";
	
	/**
	 * 默认验证码参数名称
	 */
	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

	/**
	 * 登录次数超出allowLoginNum时，存储在session记录是否展示验证码的key默认名称
	 */
	public static final String DEFAULT_SHOW_CAPTCHA_KEY_ATTRIBUTE = "showCaptcha";

	/**
	 * 默认在session中存储的登录次数名称
	 */
	public static final String DEFAULT_LOGIN_NUM_KEY_ATTRIBUTE = "loginNum";
	  //允许登录次数，当登录次数大于该数值时，会在页面中显示验证码
	public static final Integer allowLoginNum=1;
	

}
