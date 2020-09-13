package com.yeyangshu.oa.service;

import com.github.pagehelper.PageInfo;
import com.yeyangshu.oa.RespStat;
import com.yeyangshu.oa.entity.Account;

import java.util.List;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/9/13 11:36
 */
public interface IAccountService {
    /**
     * 通过账户名和密码查找用户
     * @param loginName
     * @param password
     * @return
     */
    Account findByLoginNameAndPassword(String loginName, String password);

    /**
     * 所有用户信息
     * @return
     */
    List<Account> findAll();

    /**
     * 用户分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Account> findByPage(int pageNum, int pageSize);

    /**
     * 删除用户
     * @param id
     * @return
     */
    RespStat deleteById(int id);

    /**
     * 更新用户
     * @param account
     */
    void update(Account account);
}
