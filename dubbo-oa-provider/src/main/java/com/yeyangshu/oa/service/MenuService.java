package com.yeyangshu.oa.service;

import com.github.pagehelper.PageHelper;
import com.yeyangshu.oa.entity.Menu;
import com.yeyangshu.oa.mapper.MenuExample;
import com.yeyangshu.oa.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

	@Autowired
	MenuMapper menuMapper;
	
	
	public List<Menu> findAll() {
		
		
		MenuExample example = new MenuExample();
		
		
	//	example.createCriteria().andNameEqualTo("aaa");
		return menuMapper.selectByExample(example );
	}


	public void add() {

		Menu menu = new Menu();
		menu.setIndex("0");
		menu.setName("首页");
		menu.setRoles("all");
		
		menuMapper.insert(menu);
		
	}


	public Menu findById(Integer id) {
		MenuExample example = new MenuExample();
		
		example.createCriteria().andIdEqualTo(id);
	//	List<Menu> list = menuMapper.selectByExample(example );
		
		Menu menu = menuMapper.selectByPrimaryKey(id);
		return menu;
		
		
	//	return list.size() == 1?list.get(0) : null;
	}


	public List<Menu> findByPage(Integer pageNum, Integer pageSize) {

		PageHelper.startPage(pageNum, pageSize);
		MenuExample example = new MenuExample();
		// AOP 
		return menuMapper.selectByExample(example);
	}

}
