package com.jingyuan.zhifeng.repository;

import java.util.List;

import com.jingyuan.zhifeng.entity.ZFNews;

public interface ZFNewsMapper {
	
	int deleteByPrimaryKey(Integer id);

    int insert(ZFNews news);

    int insertSelective(ZFNews news);

    ZFNews selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZFNews news);

	/**
	 * 根据类目展示
	 * @param category
	 * @return
	 */
	List<ZFNews> findByCategory(String category);
	
	List<ZFNews> findAll();
	/**
	 * 搜索标题
	 * @param title
	 * @return
	 */
	List<ZFNews> searchByTitle(String title);
	/**
	 * 全文搜索(包括标题跟正文)
	 * @param keyword
	 * @return
	 */
	List<ZFNews> searchNews(String keyword);
}
