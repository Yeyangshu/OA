package com.yeyangshu.oa.mapper;

import com.yeyangshu.oa.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AccountMapper继承MyBatisBaseDao
 */
@Repository
public interface AccountMapper extends MyBatisBaseDao<Account, Integer, AccountExample> {
    public List<Account> selectByPermission();

    /**
     * 使用账号密码查找用户
     * @param loginName
     * @param password
     * @return
     */
    public Account findByLoginNameAndPassword(String loginName, String password);
}