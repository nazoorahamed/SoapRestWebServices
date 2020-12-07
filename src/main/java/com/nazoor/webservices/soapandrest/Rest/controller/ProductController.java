package com.nazoor.webservices.soapandrest.Rest.controller;

import com.nazoor.webservices.soapandrest.Rest.model.Products;
import com.nazoor.webservices.soapandrest.Rest.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
     private ProductServiceImpl service;


    @PostMapping (path = "/addProduct")
    public Products addProducts(@RequestBody Products products){
        return service.addProducts(products);
    }

    @GetMapping (path = "/viewAll")
    public @ResponseBody Iterable<Products> getAllProducts(){
        return service.getAllProducts();
    }

    @PutMapping (path = "/products/{id}")
    Products updateProduct(@RequestBody Products products, @PathVariable int id){
        return service.updateProduct(products,id);
    }

    @DeleteMapping("/products/{id}")
    String deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }


}
