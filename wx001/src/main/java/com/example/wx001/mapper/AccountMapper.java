package com.example.wx001.mapper;

import com.example.wx001.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMapper {

    Account login(Account account);

    List<Account> queryAccounts();

    void saveAccount(Account account);

    int queryByAccname(String accname);

    void deleteAccounts(Integer accid);

    Account queryById(Integer accid);

    void abc(Account account);

    int queryByAccname02(Account account);

    void changeState(Integer accid,String accstate);
}
