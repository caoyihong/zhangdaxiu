package com.jingyuan.zhifeng.entity;

public class Teacher {
    private Integer id;

    private String name;

    private String course;

    private String password;

    private String salt;

    public Teacher(String name, String password) {
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course == null ? null : course.trim();
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