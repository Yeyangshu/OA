package com.yeyangshu.oa.service;

import com.yeyangshu.oa.entity.Account;
import com.yeyangshu.oa.mapper.AccountExample;
import com.yeyangshu.oa.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountMapper accountMapper;

    /**
     * 通过用户名和密码取出用户的信息
     * @param loginName
     * @param password
     * @return
     */
    public Account findByLoginNameAndPassword(String loginName, String password) {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria()
                .andLoginNameEqualTo(loginName)
                .andPasswordEqualTo(password);
        List<Account> accountListByLoginNameAndPsd = accountMapper.selectByExample(accountExample);;
        return accountListByLoginNameAndPsd.size() == 0 ? null : accountListByLoginNameAndPsd.get(0);
    }

    /**
     * 所有用户消息
     * @return
     */
    public List<Account> findAll() {
        AccountExample accountExample = new AccountExample();
        return accountMapper.selectByExample(accountExample);
    }
}
