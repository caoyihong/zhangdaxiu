package com.jingyuan.zhifeng.entity;

public class SysAdmin {
    private Integer id;

    private String name;

    private String decription;

    private String password;

    private String salt;

    public SysAdmin(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription == null ? null : decription.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }
    
	/**
	 * 获取新盐
	 * @return
	 */
	public String getCredentialsSalt()
	{
		return name + salt;
	}
}