package com.nazoor.webservices.soapandrest.Soap.endpoint;


import com.nazoor.products.*;
import com.nazoor.webservices.soapandrest.Rest.model.Products;
import com.nazoor.webservices.soapandrest.Rest.service.ProductServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Endpoint
public class ProductEndPoint {
    private static final String NAMESPACE_URI = "http://nazoor.com/products";

    private ProductServiceImpl productService;

    @Autowired
    public ProductEndPoint(ProductServiceImpl productService){
        this.productService = productService;
    }


    /**
     * Add new Product Using SOAP
     * @param
     * @return
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
    @ResponsePayload
    public GetProductResponse getProducts(@RequestPayload GetProductRequest request) {
        GetProductResponse productResponse = new GetProductResponse();
        List<ShowProducts> listProducts = new ArrayList<>();
        List<Products> entityProducts = new ArrayList<>();
        productService.getAllProducts().forEach(e -> entityProducts.add(e));

        for (Products entity : entityProducts) {
            ShowProducts productSh = new ShowProducts();
            BeanUtils.copyProperties(entity, productSh);
            listProducts.add(productSh);
        }
        productResponse.getProducts().addAll(listProducts);
        return productResponse;
    }

    /**
     * Add new Product Using SOAP
     * @param
     * @return
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addProductRequest")
    @ResponsePayload
    public AddProductResponse addProducts(@RequestPayload AddProductRequest request) {
        AddProductResponse response = new AddProductResponse();
        ShowProducts newProduct = new ShowProducts();

        Products newMovieEntity = new Products(request.getProductName(), request.getProductType(),request.getProductQTY(),request.isProductAVL());
        Products savedMovieEntity = productService.addProducts(newMovieEntity);

        if (savedMovieEntity == null) {
            response.setStatus("Failed");
        } else {
            BeanUtils.copyProperties(savedMovieEntity, newProduct);
            response.setStatus("SUCCESS");
        }
        response.setProducts(newProduct);
        return response;
    }

    /**
     * Update Products Using SOAP
     * @param
     * @return
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateProductRequest")
    @ResponsePayload
    public UpdateProductResponse updateProduct(@RequestPayload UpdateProductRequest request){
        UpdateProductResponse response = new UpdateProductResponse();
        ShowProducts newProduct = new ShowProducts();
        Products updateProduct = productService.getProductById(request.getProductID());
        updateProduct.setProductName(request.getProductName());
        updateProduct.setProductType(request.getProductType());
        updateProduct.setProductQTY(request.getProductQTY());
        updateProduct.setProductAVL(request.isProductAVL());

        Products updatedProduct = productService.updateProduct(updateProduct,request.getProductID());
        if (updatedProduct == null) {
            response.setStatus("Failed");
        } else {
            BeanUtils.copyProperties(updatedProduct, newProduct);
            response.setStatus("SUCCESS");
        }
        response.setProducts(newProduct);
        return response;
    }

    /**
     * Delete Product Using SOAP
     * @param
     * @return
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteProductRequest")
    @ResponsePayload
    public DeleteProductResponse deleteProduct (@RequestPayload DeleteProductRequest request) {
        DeleteProductResponse response = new DeleteProductResponse();

        String delete = productService.deleteProduct(request.getProductID());
        response.setStatus(delete);
        return response;
    }
}
