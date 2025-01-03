package com.example.wx001.mapper;

import com.example.wx001.domain.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyMapper {

    List<Company> queryCompanys();

    void saveCompany(Company company);

    void deleteCompany(Integer id);

    Company queryById(Integer id);

    void updateCompany(Company company);
}
