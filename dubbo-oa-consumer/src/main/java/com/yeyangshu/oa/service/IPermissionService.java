package com.yeyangshu.oa.service;

import com.github.pagehelper.PageInfo;
import com.yeyangshu.oa.entity.Permission;

import java.util.List;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/9/13 11:40
 */
public interface IPermissionService {
    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Permission> findByPage(int pageNum, int pageSize);

    /**
     * 通过权限 id 差抓权限
     * @param id
     * @return
     */
    Permission findById(int id);

    /**
     * 查找所有权限
     * @return
     */
    List<Permission> findAll();

    /**
     * 更新权限
     * @param permission
     */
    void update(Permission permission);

    /**
     * 添加权限
     * @param permission
     */
    void add(Permission permission);
}
