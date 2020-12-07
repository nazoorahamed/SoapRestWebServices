package com.nazoor.webservices.soapandrest.Rest.service;

import com.nazoor.webservices.soapandrest.Rest.model.Products;

import java.util.List;

public interface ProductService {

    Products addProducts(Products products);   // add a new Product

    Iterable<Products> getAllProducts(); //get all products

    Products updateProduct(Products products,int productId); //update a product

    String deleteProduct(int productId); // delete a product

    Products getProductById(int productId); // get product by Id


}
