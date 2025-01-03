package com.example.wx001.service.impl;

import com.example.wx001.domain.Company;
import com.example.wx001.mapper.CompanyMapper;
import com.example.wx001.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyMapper companyMapper;
    @Override
    public List<Company> queryCompanys() {
        return companyMapper.queryCompanys();
    }

    @Override
    public void saveCompany(Company company) {
        companyMapper.saveCompany(company);
    }

    @Override
    public void deleteCompany(Integer id) {
        companyMapper.deleteCompany(id);
    }

    @Override
    public Company queryById(Integer id) {
        return  companyMapper.queryById(id);
    }

    @Override
    public void updateCompany(Company company) {
        companyMapper.updateCompany(company);
    }
}
