package com.yeyangshu.oa.service;

import com.github.pagehelper.PageInfo;
import com.yeyangshu.oa.entity.Permission;
import com.yeyangshu.oa.entity.Role;
import com.yeyangshu.oa.mapper.PermissionExample;
import com.yeyangshu.oa.mapper.RoleExample;
import com.yeyangshu.oa.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色管理
 * @author yeyangshu
 * @version 1.0
 * @date 2020/8/30 11:15
 */
@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;

    public PageInfo<Role> findByPage(int pageNum, int pageSize) {
        RoleExample example = new RoleExample();
        List<Role> roleList = roleMapper.selectByExample(example);
        // 导航页码数固定位5页
        return new PageInfo<>(roleList, 5);
    }
}
