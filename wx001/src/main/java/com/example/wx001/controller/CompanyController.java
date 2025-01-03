package com.example.wx001.controller;

import com.example.wx001.domain.Account;
import com.example.wx001.domain.Company;
import com.example.wx001.domain.R;
import com.example.wx001.service.CompanyService;
import com.example.wx001.util.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@CrossOrigin
@Api(tags = "客户公司模块")
public class CompanyController {

    @Resource
    private CompanyService companyService;

    @GetMapping("/company/queryCompanys")
    @ApiOperation("查询所有")
    public R queryCompanys(){

        List<Company>  companys = companyService.queryCompanys();
        return new R(200,"操作成功",companys);
    }

    @PostMapping("/company/saveCompany")
    @ApiOperation("添加商家")
    public R saveCompany(@RequestBody Company company) throws BusinessException {
        companyService.saveCompany(company);
        return new R(1000, "添加成功", company);
    }

    @DeleteMapping("/company/deleteCompany/{id}")
    @ApiOperation("删除")
    public R deleteCompany(@PathVariable Integer id) {

        //调用服务层根据id完成删除
        companyService.deleteCompany(id);
        return new R(1000, "删除成功", id);
    }

    @GetMapping("/company/queryById/{id}")
    @ApiOperation("信息查询")
    public R queryById(@PathVariable Integer id) {

        Company company = companyService.queryById(id);
        return new R(1000, "查询成功", company);
    }

    @PutMapping("/company/updateCompany")
    @ApiOperation("信息修改")
    public R updateCompany(@RequestBody Company company) throws BusinessException {
        companyService.updateCompany(company);
        return new R(1000, "修改成功", company);
    }
}
