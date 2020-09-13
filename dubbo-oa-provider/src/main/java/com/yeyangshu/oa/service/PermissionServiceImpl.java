package com.yeyangshu.oa.service;

import com.github.pagehelper.PageInfo;
import com.yeyangshu.oa.entity.Permission;
import com.yeyangshu.oa.mapper.PermissionExample;
import com.yeyangshu.oa.mapper.PermissionMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 权限管理
 * @author yeyangshu
 * @version 1.0
 * @date 2020/8/30 11:14
 */
@Component
@Service(version = "1.0.0", timeout = 10000, interfaceClass = IPermissionService.class)
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    PermissionMapper permissionMapper;

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Permission> findByPage(int pageNum, int pageSize) {
        PermissionExample example = new PermissionExample();
        List<Permission> permissionList = permissionMapper.selectByExample(example);
        // 导航页码数固定位5页
        return new PageInfo<>(permissionList, 5);
    }

    /**
     * 通过主键 id 查找权限信息
     * @param id
     * @return
     */
    @Override
    public Permission findById(int id) {
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        return permission;
    }

    /**
     * 查找所有权限
     * @return
     */
    @Override
    public List<Permission> findAll() {
        PermissionExample permissionExample = new PermissionExample();
        return permissionMapper.selectByExample(permissionExample);
    }

    @Override
    public void update(Permission permission) {
        // 有值的进行更新
        permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Override
    public void add(Permission permission) {
        permissionMapper.insertSelective(permission);
    }
}
