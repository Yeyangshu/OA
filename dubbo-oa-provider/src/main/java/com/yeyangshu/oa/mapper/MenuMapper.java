package com.yeyangshu.oa.mapper;

import com.yeyangshu.oa.entity.Menu;
import org.springframework.stereotype.Repository;

/**
 * MenuMapper继承基类
 */
@Repository
public interface MenuMapper extends MyBatisBaseDao<Menu, Integer, MenuExample> {
}