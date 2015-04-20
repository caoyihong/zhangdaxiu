package com.jingyuan.zhifeng.entity;

public class Student {
    private Integer id;

    private String name;

    private Integer classid;

    private int sex;

    private Integer startyear;

    private String password;

    private String salt;

    private Class classBean;
    
    public Student(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(String name, String password, int classId,int sex,int startyear) {
		this.name = name;
		this.password = password;
		this.classid = classid;
		this.sex = sex;
		this.startyear = startyear;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Integer getStartyear() {
        return startyear;
    }

    public void setStartyear(Integer startyear) {
        this.startyear = startyear;
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

	/**
	 * @return the classBean
	 */
	public Class getClassBean() {
		return classBean;
	}

	/**
	 * @param classBean the classBean to set
	 */
	public void setClassBean(Class classBean) {
		this.classBean = classBean;
	}
	
	
}