package com.jingyuan.zhifeng.core.utils;

import java.io.Serializable;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Objects;
import com.jingyuan.zhifeng.entity.ZFAgency;
import com.jingyuan.zhifeng.entity.ZFAgencyemployee;
import com.jingyuan.zhifeng.entity.ZFUser;
import com.jingyuan.zhifeng.service.ZFUserService;

public class UserRealm extends AuthorizingRealm{
	public Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ZFUserService userService;

//	权限认证时回调
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		if(!SecurityUtils.getSubject().isAuthenticated())
		{
			doClearCache(principals);
			SecurityUtils.getSubject().logout();
			return null;
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		ShiroUser user = (ShiroUser)principals.getPrimaryPrincipal();
		int userId = user.getId();
		try {
			info.addRoles(userService.findRoles(userId, user.getType()));
			info.addStringPermissions(userService.findPermissions(userId, user.getType()));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return info;
	}

//	身份认证时回调
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token)
			throws AuthenticationException {
		
//		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo("cloud","123",getName());
//		从shiro获取前台获取的用户名，查询数据库是否存在，之后返回具体校验信息
//		username可能是邮箱、手机号、用户名，需要检验
		String value = (String) token.getPrincipal();
		
//		value为用户名和type的连接体，例如：liutao,1
		String[] values = value.split(",");
		int type = Integer.valueOf(values[1]);
		ZFUser normalUser = null;
		ZFAgencyemployee agencye = null;
		ZFAgency agency = null;
		Object user = userService.isExistUser(values[0], null, type);
		if(user == null)
		{
			throw new UnknownAccountException("没找到用户名");
		}
		SimpleAuthenticationInfo authenticationInfo = null;
		if(type == 2)//中介机构用户
		{
			agencye = (ZFAgencyemployee)user;
			authenticationInfo = new SimpleAuthenticationInfo(
					new ShiroUser(agencye.getId(),agencye.getName(),agencye.getHead(),GlobalStatic.USER_AGENCYE,agencye.getPhone(),agencye.getEmail(),agencye.getStatus()),
					agencye.getPassword(),
					ByteSource.Util.bytes(agencye.getCredentialsSalt()),
					getName()
					);
		}else if (type == 3) {	//新增的中介机构登陆
			agency = (ZFAgency) user;
			authenticationInfo = new SimpleAuthenticationInfo(
					new ShiroUser(agency.getId(), agency.getName(), agency.getHead(), GlobalStatic.USER_AGENCY, agency.getPhone(), agency.getEmail(), agency.getStatus()),
					agency.getPassword(),
					ByteSource.Util.bytes(agency.getCredentialsSalt()),
					getName()
					);
		}else//1企业用户和0个人用户
		{
			normalUser = (ZFUser)user;
			authenticationInfo = new SimpleAuthenticationInfo(
					new ShiroUser(normalUser.getId(),normalUser.getName(),normalUser.getHead(),normalUser.getType(),normalUser.getPhone(),normalUser.getEmail(),normalUser.getStatus()),
					normalUser.getPassword(),
					ByteSource.Util.bytes(normalUser.getCredentialsSalt()),
					getName()
					);
		}
		
		return authenticationInfo;
	}

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable 
	{
		private static final long serialVersionUID = -1373760761780840081L;
//		登录用户的id
		public Integer id;
//		登录用户名字
		public String username;

		public String userImage;
		
		public int type;
		
		public String phone;
		
		public String email;
//		实名认证状态： 0等待认证，1认证成功，2冻结  是否使用待定
		public int status;
		
		public ShiroUser(Integer id, String username,String userImage,int type,String phone,String email,int status)
		{
			super();
			this.id = id;
			this.userImage = userImage;
			this.username = username;
			this.type = type;
			this.phone = phone;
			this.email = email;
			this.status = status;
		}
		public Integer getId()
		{
			return id;
		}
		public void setId(Integer id)
		{
			this.id = id;
		}
		public String getUsername()
		{
			return username;
		}
		public void setUsername(String username)
		{
			this.username = username;
		}
		public String getUserImage() {
			return userImage;
		}
		public void setUserImage(String userImage) {
			this.userImage = userImage;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return "ShiroUser [id=" + id + ", username=" + username
					+ ", userImage=" + userImage + ", type=" + type
					+ ", phone=" + phone + ", email=" + email + "]";
		}

		/**
		 * 重载hashCode,只计算loginName;
		 */
		@Override
		public int hashCode() {
			return Objects.hashCode(username);
		}

		/**
		 * 重载equals,只计算loginName;
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ShiroUser other = (ShiroUser) obj;
			if (username == null) {
				if (other.username != null) {
					return false;
				}
			} else if (!username.equals(other.username)) {
				return false;
			}
			return true;
		}
		
	}
	
	@Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
    
    
    

}
