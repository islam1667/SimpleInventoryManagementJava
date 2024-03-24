package com.company.bean;

import com.company.inter.ProductInter;

/**
 *
 * @author islam
 */
public class Product implements ProductInter {
    private String name;
    private String description;
    private Double price;
    private Integer id;
    private String productNumber;
    private Integer quantity;

    public Product(String name, String description, Double price, Integer id, String productNumber, Integer quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.id = id;
        this.productNumber = productNumber;
        this.quantity = quantity;
    }
    
    
    
    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
    
    public void setPrice(Double price){
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public Integer getId() {
        return id;
    }

    public void setid(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productNumber;
    }

    public void setProductId(String productId) {
        this.productNumber = productId;
    }

    public Double getTotalPrice() {
        return (this.quantity * this.price);
    }

    @Override
    public String toString() {
        return this.getName();
//        return "Product{" + "name=" + name + ", description=" + description + ", price=" + price + ", productNumber=" + productNumber + '}';
    }
    
    public Object[] toTableRow(int rowNum) {
//        DecimalFormat df = new DecimalFormat("#,##0.##");
        return new Object[]{this, this.name , this.productNumber , this.description , this.quantity, this.price , this.getTotalPrice()};
    }
    
//    public Product rowDatatoObject(){
//        return 
//    }
    
}
