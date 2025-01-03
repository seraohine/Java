package com.example.wx001.controller;

import com.example.wx001.domain.Account;
import com.example.wx001.domain.R;
import com.example.wx001.service.AccountService;
import com.example.wx001.util.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "账户服务接口")
@CrossOrigin //跨域 允许不同的服务区的请求
public class  AccountController {

    @Resource   //将容器中的AccountService子类对象注入进去，不需要自己new创建
    private AccountService accountService;

    @PostMapping("/account/login")
    @ApiOperation("用户登录")
    public R login(@RequestBody Account account) {

        Account acc = accountService.login(account);
        if(acc == null){
            return new R(500, "账号或者密码错误，登陆失败", null);
        }
        return new R(200, "登陆成功", acc);
    }

    @GetMapping("/account/queryAccounts")
    public R queryAccount() {
        //调用service层的方法返回List<Account>集合
        List<Account> accounts=accountService.queryAccounts();
        return new R(200, "查询成功", accounts);
    }

    @PostMapping("/account/saveAccount")
    @ApiOperation("添加用户")
    public R saveAccount(@RequestBody Account account) throws BusinessException {
        accountService.saveAccount(account);
        return new R(1000, "添加成功", account);
    }

    @DeleteMapping("/account/deleteAccounts/{accid}")
    public R deleteAccounts(@PathVariable Integer accid) {

        //调用服务层根据id完成删除
        accountService.deleteAccounts(accid);
        return new R(1000, "删除成功", accid);
    }

    @GetMapping("/account/queryById/{accid}")
    public R queryById(@PathVariable Integer accid) {

        Account account = accountService.qeryById(accid);
        return new R(1000, "查询成功", account);
    }

    @PutMapping("/account/abc")
    public R abc(@RequestBody Account account) throws BusinessException {
        accountService.abc(account);
        return new R(1000, "修改成功", account);
    }
    @PutMapping("/account/changeState/{accid}/{accstate}")
    public R changeState(@PathVariable Integer accid,@PathVariable String accstate){
        accstate = accstate.equals("1")?"0":"1";
        accountService.changeState(accid,accstate);
        return new R(1000, "禁用成功", null);
    }
}
