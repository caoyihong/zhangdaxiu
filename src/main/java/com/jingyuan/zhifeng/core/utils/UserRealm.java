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
import com.jingyuan.zhifeng.core.constant.UserType;
import com.jingyuan.zhifeng.entity.Student;
import com.jingyuan.zhifeng.entity.SysAdmin;
import com.jingyuan.zhifeng.entity.Teacher;
import com.jingyuan.zhifeng.service.UserService;

public class UserRealm extends AuthorizingRealm{
	public Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserService userService;

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
		/*try {
			info.addRoles(userService.findRoles(userId, user.getType()));
			info.addStringPermissions(userService.findPermissions(userId, user.getType()));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}*/
		return info;
	}

	/**
	 * 身份认证时回调
	 * 老师、学生、管理员登录统一调用该接口
	 * 通过type区分 0-学生，1-老师，2-管理员
	 */
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
		Student student = null;
		Teacher tach = null;
		SysAdmin admin = null;
		Object user = userService.findUserByNameAndType(values[0], type);
		if(user == null)
		{
			throw new UnknownAccountException("没找到用户名");
		}
		SimpleAuthenticationInfo authenticationInfo = null;
		if(type == UserType.STUDENT.getType())//中介机构用户
		{
			student = (Student)user;
			authenticationInfo = new SimpleAuthenticationInfo(
					new ShiroUser(student.getId(),student.getName(),UserType.STUDENT.getType()),
					student.getPassword(),
					ByteSource.Util.bytes(student.getCredentialsSalt()),
					getName()
					);
		}else if (type == UserType.TEACHER.getType()) {	//新增的中介机构登陆
			tach = (Teacher) user;
			authenticationInfo = new SimpleAuthenticationInfo(
					new ShiroUser(tach.getId(), tach.getName(),UserType.TEACHER.getType()),
					tach.getPassword(),
					ByteSource.Util.bytes(tach.getCredentialsSalt()),
					getName()
					);
		}else//1企业用户和0个人用户
		{
			admin = (SysAdmin)user;
			authenticationInfo = new SimpleAuthenticationInfo(
					new ShiroUser(admin.getId(),admin.getName(),UserType.SYSADMIN.getType()),
					admin.getPassword(),
					ByteSource.Util.bytes(admin.getCredentialsSalt()),
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
		public String name;
//		用户类型 0-学生，1-老师，2-管理员
		public int type;
		
		
		public ShiroUser(Integer id, String name,int type)
		{
			this.id = id;
			this.name = name;
			this.type = type;
		}
		public Integer getId()
		{
			return id;
		}
		public void setId(Integer id)
		{
			this.id = id;
		}
		public String getName()
		{
			return name;
		}
		public void setName(String name)
		{
			this.name = name;
		}
		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return "ShiroUser [id=" + id + ", name=" + name + ", type=" + type
					+ "]";
		}

		/**
		 * 重载hashCode,只计算loginName;
		 */
		@Override
		public int hashCode() {
			return Objects.hashCode(name);
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
			if ( name == null) {
				if (other.name != null) {
					return false;
				}
			} else if (!name.equals(other.name)) {
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
