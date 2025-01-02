package com.example.wx001.service;

import com.example.wx001.domain.Account;
import com.example.wx001.util.BusinessException;

import java.util.List;

public interface AccountService {
    Account login(Account account);

    List<Account> queryAccounts();

    void saveAccount(Account account) throws BusinessException;

    void deleteAccounts(Integer accid);

    Account qeryById(Integer accid);

    void abc(Account account) throws BusinessException;

    void changeState(Integer accid,String accstate);
}
