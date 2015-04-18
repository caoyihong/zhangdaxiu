package com.jingyuan.zhifeng.service;

import java.util.List;

import com.jingyuan.zhifeng.entity.ZFNews;

public interface ZFNewsService {

	public ZFNews findById(int id);
	
	public int insertNews(ZFNews news);
	
	public void updateNews(ZFNews news);
	
	public void deleteById(Integer id);
	
	public List<ZFNews> findAll();
	
	public List<ZFNews> findByCategory(String category);
	
	public List<ZFNews> searchByTitle(String title);
	
	public List<ZFNews> searchNews(String keyword);
}
