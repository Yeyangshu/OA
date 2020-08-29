package com.yeyangshu.oa.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
     * 所有用户信息
     * @return
     */
    public List<Account> findAll() {
        AccountExample accountExample = new AccountExample();
        return accountMapper.selectByExample(accountExample);
    }

    /**
     * 所有用户信息分页功能
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Account> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        AccountExample example = new AccountExample();
        List<Account> accountList = accountMapper.selectByExample(example);
        // 导航页码数固定位5页
        return new PageInfo<>(accountList, 5);
    }
}
