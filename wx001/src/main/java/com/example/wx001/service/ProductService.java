package com.example.wx001.service;

import com.example.wx001.domain.Product;
import com.example.wx001.util.BusinessException;

import java.util.List;

public interface ProductService {

    List<Product> queryProducts(Product product);

    void saveProduct(Product product) throws BusinessException;

    Product querById(Integer id);

    void updateProduct(Product product) throws BusinessException;

    void deleteProduct(Integer id);
}
