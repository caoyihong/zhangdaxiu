package com.jingyuan.zhifeng.core.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import com.jingyuan.zhifeng.entity.ZFAgency;
import com.jingyuan.zhifeng.entity.ZFAgencyemployee;
import com.jingyuan.zhifeng.entity.ZFUser;
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
     * 个人或企业用户加密
     * @param AMUser
     */
    public void encryptPasswordZFUser(ZFUser AMUser) {

//    	设置一个自动生成的盐
        AMUser.setSalt(randomNumberGenerator.nextBytes().toHex());
//		根据明文密码、身份认证盐、加密算法、加密次数来算出加密的密码，与CredentialMatcher一致
        String newPassword = new SimpleHash(
                algorithmName,
                AMUser.getPassword(),
                ByteSource.Util.bytes(AMUser.getCredentialsSalt()),
                hashIterations).toHex();

        AMUser.setPassword(newPassword);
    }
    
    /**
     * 中介机构从业人员加密
     * @param AMUser
     */
    public void encryptPasswordZFA(ZFAgencyemployee AMUser) {

//    	设置一个自动生成的盐
        AMUser.setSalt(randomNumberGenerator.nextBytes().toHex());
//		根据明文密码、身份认证盐、加密算法、加密次数来算出加密的密码，与CredentialMatcher一致
        String newPassword = new SimpleHash(
                algorithmName,
                AMUser.getPassword(),
                ByteSource.Util.bytes(AMUser.getCredentialsSalt()),
                hashIterations).toHex();

        AMUser.setPassword(newPassword);
    }
    /**
     * 中介机构加密
     * @param AMUser
     */
    public void encryptPasswordZFAgency(ZFAgency agency) {
    	
//    	设置一个自动生成的盐
    	agency.setSalt(randomNumberGenerator.nextBytes().toHex());
//		根据明文密码、身份认证盐、加密算法、加密次数来算出加密的密码，与CredentialMatcher一致
    	String newPassword = new SimpleHash(
    			algorithmName,
    			agency.getPassword(),
    			ByteSource.Util.bytes(agency.getCredentialsSalt()),
    			hashIterations).toHex();
    	
    	agency.setPassword(newPassword);
    }
}
