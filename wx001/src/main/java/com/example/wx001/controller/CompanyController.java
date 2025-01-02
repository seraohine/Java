package com.example.wx001.controller;

import com.example.wx001.domain.Company;
import com.example.wx001.domain.R;
import com.example.wx001.service.CompanyService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@CrossOrigin
public class CompanyController {

    @Resource
    private CompanyService companyService;

    @GetMapping("/company/queryCompanys")
    public R queryCompanys(){

        List<Company>  companys = companyService.queryCompanys();
        return new R(200,"操作成功",companys);
    }
}
