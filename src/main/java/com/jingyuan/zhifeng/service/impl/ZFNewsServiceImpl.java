package com.jingyuan.zhifeng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingyuan.zhifeng.entity.ZFNews;
import com.jingyuan.zhifeng.repository.ZFNewsMapper;
import com.jingyuan.zhifeng.service.ZFNewsService;

@Service
public class ZFNewsServiceImpl implements ZFNewsService {

	@Autowired
	ZFNewsMapper newsMapper;
	
	@Override
	public ZFNews findById(int id) {
		return newsMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertNews(ZFNews news) {
		newsMapper.insertSelective(news);
		return news.getId();
	}

	@Override
	public void updateNews(ZFNews news) {
		newsMapper.updateByPrimaryKeySelective(news);
	}

	@Override
	public void deleteById(Integer id) {
		newsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<ZFNews> findAll() {
		return newsMapper.findAll();
	}

	@Override
	public List<ZFNews> findByCategory(String category) {
		return newsMapper.findByCategory(category);
	}

	@Override
	public List<ZFNews> searchByTitle(String title) {
		return newsMapper.searchByTitle(title);
	}

	@Override
	public List<ZFNews> searchNews(String keyword) {
		return newsMapper.searchNews(keyword);
	}

}
