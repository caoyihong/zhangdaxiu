package com.jingyuan.zhifeng.web.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jingyuan.zhifeng.core.utils.UserRealm;
import com.jingyuan.zhifeng.core.utils.GlobalStatic;
import com.jingyuan.zhifeng.web.filter.CzFormAuthenticationFilter;

public class CzFormAuthenticationFilter extends FormAuthenticationFilter{

	private static Logger logger = LoggerFactory.getLogger(CzFormAuthenticationFilter.class);

//  饿封装验证码校验方法
    public void doCaptchaValidate(HttpServletRequest request)
    {

    }

    public boolean executeLogin(ServletRequest req, ServletResponse rep) throws Exception {

        HttpServletRequest request = (HttpServletRequest)req;
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(true);
        Object authc = session.getAttribute("passwordTryTimes");
        int retryCount = 1;
//      retry count + 1
//      判断session中是否有try次数，并加1
        if(authc == null) {
            session.setAttribute("passwordTryTimes",retryCount);
        }
        else {
            retryCount = (Integer)authc;
            session.setAttribute("passwordTryTimes",++retryCount);
        }

//      放入request中以便前端判断是否需要
        request.setAttribute("codeCount",retryCount);
        if(retryCount > 3) {
            //if retry count > 5 throw

            String code = (String) session.getAttribute(GlobalStatic.DEFAULT_CAPTCHA_PARAM);
            logger.info("session验证码 ：== {}",code);
//		    判断验证码是否存在，如果存在继续和表单提交的验证码进行比对
            if(code != null && !code.equals(""))
            {
//              验证码如果不一致就直接跳过校验
//              true的话就会跳过校验，意思是不会去login
                
                String reqCode = request.getParameter("code");
                
                if(StringUtils.isNotBlank(reqCode))
                {
                	reqCode = reqCode.toLowerCase();
                }
                logger.info("request验证码 ：== {}", reqCode);
                if(!code.equals(reqCode))
                {
                	req.setAttribute("msg", "验证码不对");
                    setFailureAttribute(request, new AuthenticationException("验证码不一致"));
                    
                    return true;
                }
            }
            else
            {
//            	由于code异常不存在session中所报的错
            	req.setAttribute("msg", "验证码不对");
            	return true;
            }
        }
        return super.executeLogin(req,rep);

    }


    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest req, ServletResponse response) throws Exception {

        logger.info("登录成功");
//      登录成功就去掉session里的try次数
        HttpServletRequest request = (HttpServletRequest)req;
        Session session = subject.getSession(false);
        session.removeAttribute("passwordTryTimes");

//      将用户id放入session，方便通用头使用
        UserRealm.ShiroUser currentUser = (UserRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        session.setAttribute("currentUser",currentUser);
        issueSuccessRedirect(request, response);
        return false;
    }
    
}
