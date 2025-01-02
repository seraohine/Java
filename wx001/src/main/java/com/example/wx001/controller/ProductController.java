package com.example.wx001.controller;

import com.example.wx001.domain.Product;
import com.example.wx001.domain.R;
import com.example.wx001.service.ProductService;
import com.example.wx001.util.BusinessException;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController //将该类的对象自动创建并且注册到容器（Wx001Application）中统一管理
@CrossOrigin
public class ProductController {

    @Resource
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product/queryProducts")
    public R queryProducts(@RequestBody Product product){

        List<Product> products = productService.queryProducts(product);
        return new R(200,"查询成功",products);
    }
    @PostMapping("/product/saveProduct")
    public R saveProduct(@RequestBody Product product) throws BusinessException {
        productService.saveProduct(product);
        return  new R(200,"添加成功",null);
    }
    @DeleteMapping("/product/deleteProduct/{id}")
    public R deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        return new R(200,"删除成功",null);
    }
    @GetMapping("/product/queryById/{id}")
    public  R queryById(@PathVariable Integer id){
        Product product = productService.querById(id);
        return new R(200,"操作成功",product);
    }
    @PutMapping("/product/updateProduct")
    public  R updateProduct(@RequestBody Product product) throws BusinessException {

        productService.updateProduct(product);
        return new R(200,"修改成功",null);
    }
}
