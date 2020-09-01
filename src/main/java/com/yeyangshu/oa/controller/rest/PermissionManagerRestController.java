package com.yeyangshu.oa.controller.rest;

import com.yeyangshu.oa.RespStat;
import com.yeyangshu.oa.entity.Permission;
import com.yeyangshu.oa.service.PermissionService;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 只和用户交换数据
 * restful风格的 controller
 * @author yeyangshu
 * @version 1.0
 * @date 2020/8/30 16:42
 */
@RestController
@RequestMapping("/api/v1/manager/permission")
public class PermissionManagerRestController {

    @Autowired
    PermissionService permissionService;

    /**
     * 添加权限
     * @param permission
     * @return
     */
    @RequestMapping("/add")
    public RespStat add(@RequestBody Permission permission) {
        System.out.println("permission：" + ToStringBuilder.reflectionToString(permission));
        permissionService.add(permission);
        return RespStat.build(200);
    }

    /**
     * 修改权限
     * @param permission
     * @return
     */
    @RequestMapping("/update")
    public RespStat update(@RequestBody Permission permission) {
        System.out.println("permission：" + ToStringBuilder.reflectionToString(permission));
        permissionService.update(permission);
        return RespStat.build(200);
    }
}