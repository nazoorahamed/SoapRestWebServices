package com.nazoor.webservices.soapandrest.Rest.service;

import com.nazoor.webservices.soapandrest.Rest.model.Products;
import com.nazoor.webservices.soapandrest.Rest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    /**
     * perform adding new product to the Database
     */
    @Override
    public Products addProducts(Products products) {
        return repository.save(products);
    }

    /**
     * perform returning all the products from the Database
     */
    @Override
    public Iterable<Products> getAllProducts() {
        return repository.findAll();
    }

    /**
     * perform updating a product to the Database
     */
    @Override
    public Products updateProduct(Products products, int productId) {
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

    /**
     * perform deleting a product from the Database
     */
    @Override
    public String deleteProduct(int productId) {
        repository.deleteById(productId);
        return "Deleted";
    }

    /**
     * perform finding a product from the Database
     */
    @Override
    public Products getProductById(int productId) {
        return repository.findById(productId).orElse(null);
    }
}
