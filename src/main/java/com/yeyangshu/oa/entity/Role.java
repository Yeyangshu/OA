package com.yeyangshu.oa.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 角色表
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/8/30 12:52
 */
public class Role implements Serializable {
    /** 序列化 */
    private static final long serialVersionUID = 1L;

    /** 角色 id */
    private Integer id;

    /** 角色名称 */
    private String name;

    /** 角色权限列表 */
    private List<Permission> permissionList;

    /** setter and getter */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Role other = (Role) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", permissionList=" + permissionList +
                '}';
    }
}