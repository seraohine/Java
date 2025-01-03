package com.example.wx001.service;

import com.example.wx001.domain.Company;

import java.util.List;

public interface CompanyService {
    List<Company> queryCompanys();

    void saveCompany(Company company);

    void deleteCompany(Integer id);

    Company queryById(Integer id);

    void updateCompany(Company company);
}
