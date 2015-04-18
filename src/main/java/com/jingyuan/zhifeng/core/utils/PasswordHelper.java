package com.jingyuan.zhifeng.core.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import com.jingyuan.zhifeng.entity.Student;
import com.jingyuan.zhifeng.entity.SysAdmin;
import com.jingyuan.zhifeng.entity.Teacher;
@Component
public class PasswordHelper {

	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    /**
     * 学生用户加密
     * @param AMUser
     */
    public void encryptPasswordZFUser(Student stu) {

//    	设置一个自动生成的盐
    	stu.setSalt(randomNumberGenerator.nextBytes().toHex());
//		根据明文密码、身份认证盐、加密算法、加密次数来算出加密的密码，与CredentialMatcher一致
        String newPassword = new SimpleHash(
                algorithmName,
                stu.getPass(),
                ByteSource.Util.bytes(stu.getCredentialsSalt()),
                hashIterations).toHex();

        stu.setPass(newPassword);
    }
    
    /**
     * 老师加密
     * @param AMUser
     */
    public void encryptPasswordZFA(Teacher teach) {

//    	设置一个自动生成的盐
    	teach.setSalt(randomNumberGenerator.nextBytes().toHex());
//		根据明文密码、身份认证盐、加密算法、加密次数来算出加密的密码，与CredentialMatcher一致
        String newPassword = new SimpleHash(
                algorithmName,
                teach.getPassword(),
                ByteSource.Util.bytes(teach.getCredentialsSalt()),
                hashIterations).toHex();

        teach.setPassword(newPassword);
    }
    /**
     * 管理员加密
     * @param AMUser
     */
    public void encryptPasswordZFAgency(SysAdmin admin) {
    	
//    	设置一个自动生成的盐
    	admin.setSalt(randomNumberGenerator.nextBytes().toHex());
//		根据明文密码、身份认证盐、加密算法、加密次数来算出加密的密码，与CredentialMatcher一致
    	String newPassword = new SimpleHash(
    			algorithmName,
    			admin.getPassword(),
    			ByteSource.Util.bytes(admin.getCredentialsSalt()),
    			hashIterations).toHex();
    	
    	admin.setPassword(newPassword);
    }
}
