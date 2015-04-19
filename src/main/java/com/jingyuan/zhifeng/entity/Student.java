package com.jingyuan.zhifeng.entity;

public class Student {
    private Integer id;

    private String name;

    private Integer classid;

    private Byte sex;

    private Integer startyear;

    private String pass;

    private String salt;

    public Student(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}

	public Student() {
		// TODO Auto-generated constructor stub
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

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Integer getStartyear() {
        return startyear;
    }

    public void setStartyear(Integer startyear) {
        this.startyear = startyear;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass == null ? null : pass.trim();
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