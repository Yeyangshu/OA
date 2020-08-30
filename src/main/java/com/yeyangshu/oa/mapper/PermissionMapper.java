package com.yeyangshu.oa.mapper;

import com.yeyangshu.oa.entity.Account;
import com.yeyangshu.oa.entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PermissionMapper继承基类
 */
@Repository
public interface PermissionMapper extends MyBatisBaseDao<Permission, Integer, PermissionExample> {
}