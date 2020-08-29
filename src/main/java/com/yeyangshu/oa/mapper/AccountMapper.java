package com.yeyangshu.oa.mapper;

import com.yeyangshu.oa.entity.Account;
import org.springframework.stereotype.Repository;

/**
 * AccountMapper继承MyBatisBaseDao
 */
@Repository
public interface AccountMapper extends MyBatisBaseDao<Account, Integer, AccountExample> {
}