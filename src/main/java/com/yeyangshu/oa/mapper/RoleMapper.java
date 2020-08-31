package com.yeyangshu.oa.mapper;

import com.yeyangshu.oa.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * RoleMapper继承基类
 */
@Repository
public interface RoleMapper extends MyBatisBaseDao<Role, Integer, RoleExample> {

    /**
     * 角色添加权限
     * @param id
     * @param permissionIds
     */
    public void addPermission(int id, int[] permissionIds);

    /**
     * 通过 id 查找角色
     * @param id
     * @return
     */
    Role findById(int id);
}