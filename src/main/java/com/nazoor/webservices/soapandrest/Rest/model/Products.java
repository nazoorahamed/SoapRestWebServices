package com.nazoor.webservices.soapandrest.Rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Products {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer productID;

    private String productName;

    private String productType;

    private Integer productQTY;

    private boolean productAVL;

    public Products() {
    }

    public Products(String productName, String productType, int productQTY, boolean productAVL) {
        this.productName = productName;
        this.productType = productType;
        this.productQTY = productQTY;
        this.productAVL = productAVL;
    }


    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getProductQTY() {
        return productQTY;
    }

    public void setProductQTY(Integer productQTY) {
        this.productQTY = productQTY;
    }

    public boolean getProductAVL() {
        return productAVL;
    }

    public void setProductAVL(boolean productAVL) {
        this.productAVL = productAVL;
    }
}
