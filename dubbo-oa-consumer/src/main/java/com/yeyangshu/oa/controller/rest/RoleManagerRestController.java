package com.yeyangshu.oa.controller.rest;

import com.yeyangshu.oa.RespStat;
import com.yeyangshu.oa.service.IRoleService;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 只和用户交换角色数据
 * @author yeyangshu
 * @version 1.0
 * @date 2020/8/31 21:06
 */
@RestController
@RequestMapping("/api/v1/manager/role")
public class RoleManagerRestController {

    @Reference(version = "1.0.0")
    IRoleService roleService;

    /**
     * 添加权限
     * @param permissionIds
     * @return
     */
    @RequestMapping("/permission/add")
    public RespStat add(@RequestParam int[] permissionIds, @RequestParam int id) {
        System.out.println("permissionIds：" + ToStringBuilder.reflectionToString(permissionIds));
        roleService.addPermission(id, permissionIds);
        return RespStat.build(200);
    }
}
