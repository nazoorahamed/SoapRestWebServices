package com.nazoor.webservices.soapandrest.Rest.repository;

import com.nazoor.webservices.soapandrest.Rest.model.Products;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Products,Integer> {
}
