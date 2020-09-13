package com.yeyangshu.oa.service;

import com.github.pagehelper.PageInfo;
import com.yeyangshu.oa.entity.Role;
import com.yeyangshu.oa.mapper.RoleExample;
import com.yeyangshu.oa.mapper.RoleMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色管理
 * @author yeyangshu
 * @version 1.0
 * @date 2020/8/30 11:15
 */
@Component
@Service(version = "1.0.0", timeout = 10000, interfaceClass = IRoleService.class)
public class RoleServiceImpl implements IRoleService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public PageInfo<Role> findByPage(int pageNum, int pageSize) {
        RoleExample example = new RoleExample();
        List<Role> roleList = roleMapper.selectByExample(example);
        // 导航页码数固定位5页
        return new PageInfo<>(roleList, 5);
    }

    /**
     * 查找角色信息
     * @param id
     * @return
     */
    @Override
    public Role findById(int id) {
        return roleMapper.findById(id);
    }

    /**
     * 批量为角色添加权限
     * @param id
     * @param permissionIds
     */
    @Override
    public void addPermission(int id, int[] permissionIds) {
        roleMapper.addPermission(id, permissionIds);
    }
}
