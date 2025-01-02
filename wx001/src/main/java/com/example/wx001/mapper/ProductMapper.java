package com.example.wx001.mapper;

import com.example.wx001.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {

    List<Product> queryProducts(Product product);

    void saveProduct(Product product);

    int checkPname(Product product);

    Product queryById(Integer id);

    void updateProduct(Product product);

    int checkPname2(Product product);

    void deleteProduct(Integer id);
}
