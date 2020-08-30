package com.yeyangshu.oa.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yeyangshu.oa.RespStat;
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
        //PageHelper.startPage(pageNum, pageSize);

        List<Account> accounts = accountMapper.selectByPermission();
        System.out.println(accounts.toString());

        AccountExample example = new AccountExample();
        List<Account> accountList = accountMapper.selectByExample(example);
        // 导航页码数固定位5页
        return new PageInfo<>(accountList, 5);
    }

    /**
     * 操作数据库进行删除操作
     * 注意事项：
     * 1、需要提醒用户是否真的删除
     * 2、使用删除标记，使数据不会永远删除（同理 update，只增加而不去直接修改表内容）
     * @param id
     * @return
     */
    public RespStat deleteById(int id) {
        // 删除后会返回行数
        int rows = accountMapper.deleteByPrimaryKey(id);
        System.out.println("delete by id, rows is ：" + rows);
        if (rows == 1) {
            return RespStat.build(200);
        } else {
            return RespStat.build(500, "删除错误");
        }
    }

    /**
     * 更新用户信息
     * @param account
     */
    public void update(Account account) {
        accountMapper.updateByPrimaryKeySelective(account);
    }
}
