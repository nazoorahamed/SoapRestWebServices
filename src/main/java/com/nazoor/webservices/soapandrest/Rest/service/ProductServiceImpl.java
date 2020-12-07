package com.nazoor.webservices.soapandrest.Rest.service;

import com.nazoor.webservices.soapandrest.Rest.model.Products;
import com.nazoor.webservices.soapandrest.Rest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Products addProducts(Products products) {
        return repository.save(products);
    }

    @Override
    public Iterable<Products> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Products updateProduct(Products products,int productId) {
        return repository.findById(productId)
                .map(products1 -> {
                    products1.setProductName(products.getProductName());
                    products1.setProductType(products.getProductType());
                    products1.setProductQTY(products.getProductQTY());
                    products1.setProductAVL(products.getProductAVL());
                    return repository.save(products1);
                })
                .orElseGet(() -> {
                    products.setProductID(productId);
                    return repository.save(products);
                });
    }

    @Override
    public String deleteProduct(int productId) {
        repository.deleteById(productId);
        return "Deleted";
    }

    @Override
    public Products getProductById(int productId) {
        return repository.findById(productId).orElse(null);
    }
}
