package com.jingyuan.zhifeng.repository;

import java.util.List;

import com.jingyuan.zhifeng.entity.ZFAgencyemployee;

public interface ZFAgencyemployeeMapper {
	
	/**
	 * 根据id删除中介机构从业人员
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增从业人员(数据必须齐全)
     * @param record
     * @return
     */
    int insert(ZFAgencyemployee record);

    /**
     * 新增从业人员(数据可以不完整)
     * @param record
     * @return
     */
    int insertSelective(ZFAgencyemployee record);

    /**
     * 根据Id查询从业人员
     * @param id
     * @return
     */
    ZFAgencyemployee selectByPrimaryKey(Integer id);

    /**
     * 根据用户(通过id检索该用户)
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ZFAgencyemployee record);

    /**
     * 根据用户(通过id检索该用户)
     * @param record
     * @return
     */
    //int updateByPrimaryKey(ZFAgencyemployee record);
	
    /**
     * 根据手机号查询用户
     * @param phone
     * @return
     */
    ZFAgencyemployee selectByPhone(String phone);
	
    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    ZFAgencyemployee selectByUserName(String userName);

    /**
     * 根据email查询用户(未实现)
     * @param email
     * @return
     */
	//Object selectByEmail(String email);
	
	ZFAgencyemployee selectByUser(ZFAgencyemployee user);
	
	/**
	 * 根据weight排序查询对应的从业人员
	 * @param agencyId
	 * @param limit
	 * @return
	 */
	List<ZFAgencyemployee> findGoldEmployee(int agencyId,int limit);
	
	/**
	 * 根据中介机构的id查找对应的从业人员
	 * @param agencyId
	 * @return
	 */
	List<ZFAgencyemployee> findEmployeeByAgencyId(int agencyId);
	
	/**
	 * 根据userid查询中介结构从业人员
	 * @param id
	 * @return
	 */
	ZFAgencyemployee findEmployeeById(Integer id);
	
	List<ZFAgencyemployee> findOtherByUserId(Integer agencyId,Integer userId,Integer limit);
	
	/**
	 * 法务预约：查找符合条件的律师
	 * @param agencyId
	 * @param services
	 * @param agencye
	 * @param ageRank
	 * @return
	 */
	public List<ZFAgencyemployee> findAgencyeWithNeeds(int agencyId,String[] services,String[] agencye,Integer ageRank);
	
	public ZFAgencyemployee selectByUserId(Integer userId);
}