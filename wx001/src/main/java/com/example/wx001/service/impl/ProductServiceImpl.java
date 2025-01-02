package com.example.wx001.service.impl;

import com.example.wx001.domain.Product;
import com.example.wx001.mapper.ProductMapper;
import com.example.wx001.service.ProductService;
import com.example.wx001.util.BusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;


    @Override
    public List<Product> queryProducts(Product product) {
        return productMapper.queryProducts(product);
    }

    @Override
    public void saveProduct(Product product) throws BusinessException {
        int row =  productMapper.checkPname(product);
        if(row >0){
            throw new BusinessException("商品名重复");
        }
        productMapper.saveProduct(product);
    }

    @Override
    public Product querById(Integer id) {
        return productMapper.queryById(id);
    }

    @Override
    public void updateProduct(Product product) throws BusinessException {
        int row =  productMapper.checkPname2(product);
        if(row >0){
            throw new BusinessException("商品名重复");
        }
        productMapper.updateProduct(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productMapper.deleteProduct(id);
    }
}
