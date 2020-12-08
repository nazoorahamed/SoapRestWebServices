CREATE TABLE Products (
    productID int NOT NULL auto_increment,
    productName varchar(255),
    productType varchar(255),
    productQty int,
    prdAvailable boolean,
    primary key (productID)
);