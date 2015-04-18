package com.jingyuan.zhifeng.repository;

import com.jingyuan.zhifeng.entity.ZFAddress;
import com.jingyuan.zhifeng.entity.ZFAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZFAddressMapper {
    int countByExample(ZFAddressExample example);

    int deleteByExample(ZFAddressExample example);

    int insert(ZFAddress record);

    int insertSelective(ZFAddress record);

    List<ZFAddress> selectByExample(ZFAddressExample example);

    int updateByExampleSelective(@Param("record") ZFAddress record, @Param("example") ZFAddressExample example);

    int updateByExample(@Param("record") ZFAddress record, @Param("example") ZFAddressExample example);
    
    int updateByPrimaryKeySelective(ZFAddress record);
    
    ZFAddress findAddressById(Integer addressId);
}