package com.yeyangshu.oa.mapper;

import com.yeyangshu.oa.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * RoleMapper继承基类
 */
@Repository
public interface RoleMapper extends MyBatisBaseDao<Role, Integer, RoleExample> {
}