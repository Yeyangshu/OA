package com.yeyangshu.oa.service;

import com.github.pagehelper.PageInfo;
import com.yeyangshu.oa.entity.Role;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/9/13 11:42
 */
public interface IRoleService {
    /**
     * 角色分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Role> findByPage(int pageNum, int pageSize);

    /**
     * 通过 id 查找角色
     * @param id
     * @return
     */
    Role findById(int id);

    /**
     * 添加权限
     * @param id
     * @param permissionIds
     */
    void addPermission(int id, int[] permissionIds);
}
