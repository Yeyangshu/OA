package com.yeyangshu.oa.controller;

import com.github.pagehelper.PageInfo;
import com.yeyangshu.oa.entity.Account;
import com.yeyangshu.oa.entity.Permission;
import com.yeyangshu.oa.entity.Role;
import com.yeyangshu.oa.service.AccountService;
import com.yeyangshu.oa.service.PermissionService;
import com.yeyangshu.oa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 系统设置模块
 * @author yeyangshu
 * @version 1.0
 * @date 2020/8/30 11:02
 */
@RequestMapping("/manager")
@Controller
public class ManagerController {

    @Autowired
    AccountService accountService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    RoleService roleService;

    /**
     * 账户管理
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping("/accountList")
    public String accountList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, Model model) {
        PageInfo<Account> page = accountService.findByPage(pageNum, pageSize);
        model.addAttribute("page", page);
        return "manager/accountList";
    }

    /**
     * 角色管理
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping("/roleList")
    public String roleList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, Model model) {
        PageInfo<Role> page = roleService.findByPage(pageNum, pageSize);
        model.addAttribute("page", page);
        return "manager/roleList";
    }

    /**
     * 角色添加/修改权限
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/rolePermission/{id}")
    public String rolePermission(@PathVariable int id, Model model) {
        System.out.println("role permission id is " + id);
        Role role = roleService.findById(id);
        List<Permission> permissionList = permissionService.findAll();
        model.addAttribute("role", role);
        model.addAttribute("permissionList", permissionList);
        return "manager/rolePermission";
    }

    /**
     * 权限管理
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping("/permissionList")
    public String permissionList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, Model model) {
        PageInfo<Permission> page = permissionService.findByPage(pageNum, pageSize);
        model.addAttribute("page", page);
        return "manager/permissionList";
    }

    @RequestMapping("/permissionModify")
    public String permissionModify(@RequestParam int id, Model model) {
        Permission permission = permissionService.findById(id);
        model.addAttribute("permission", permission);
        return "manager/permissionModify";
    }

    @RequestMapping("/permissionAdd")
    public String permissionAdd() {
        return "manager/permissionModify";
    }
}
