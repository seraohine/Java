package com.example.wx001.service.impl;

import com.example.wx001.domain.Account;
import com.example.wx001.mapper.AccountMapper;
import com.example.wx001.service.AccountService;
import com.example.wx001.util.BusinessException;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public Account login(Account account) {
        return accountMapper.login(account);
    }

    @Resource
    private AccountMapper accountMapper;
    @Override
    public List<Account> queryAccounts(){
        return  accountMapper.queryAccounts();
    }
    @Override
    public void saveAccount(Account account) throws BusinessException {
        int row = accountMapper.queryByAccname(account.getAccname());
        if(row>0){
            throw new BusinessException("账号重复");
        }
        accountMapper.saveAccount(account);
    }

    @Override
    public void deleteAccounts(Integer accid) {
        accountMapper.deleteAccounts(accid);
    }

    @Override
    public Account qeryById(Integer accid) {
        return accountMapper.queryById(accid);
    }

    @Override
    public void abc(Account account) throws BusinessException {
        int row = accountMapper.queryByAccname02(account);
        if(row>0){
            throw new BusinessException("账号重复");
        }
        accountMapper.abc(account);
    }

    @Override
    public void changeState(@Param("accid") Integer accid,@Param("accstate") String accstate) {
         accountMapper.changeState(accid,accstate);
    }
}
