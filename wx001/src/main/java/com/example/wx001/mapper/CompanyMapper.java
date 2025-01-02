package com.example.wx001.mapper;

import com.example.wx001.domain.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyMapper {

    List<Company> queryCompanys();
}
