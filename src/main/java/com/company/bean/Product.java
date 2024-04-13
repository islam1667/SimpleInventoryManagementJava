package com.company.bean;

import com.company.inter.ProductInter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author islam
 */
public class Product implements ProductInter {
    private int id;
    private String name;
    private String description;
    private double buyPrice;
    private double sellPrice;
    private String productNumber;
    private double quantity;
    private MeasureType measure;
    private String company;
    
    
    public Product(int id, String name, String description, double buyPrice, double sellPrice, String productNumber, double quantity, MeasureType measure, String company) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.productNumber = productNumber;
        this.quantity = quantity;
        this.measure = measure;
        this.company = company;
    }
    
    public Product(Product p){
        this.id = p.getId();
        this.name = p.getName();
        this.description = p.getDescription();
        this.buyPrice = p.getBuyPrice();
        this.sellPrice = p.getSellPrice();
        this.productNumber = p.productNumber;
        this.quantity = p.quantity;
        this.measure = p.measure;
        this.company = p.company;
    }
    

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public MeasureType getMeasure() {
        return measure;
    }

    public void setMeasure(MeasureType measure) {
        this.measure = measure;
    }
            
    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    @Override
    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellprice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setid(Integer id) {
        this.id = id;
    }

//    public String getProductId() {
//        return productNumber;
//    }
//
//    public void setProductId(String productId) {
//        this.productNumber = productId;
//    }

    public double getTotalPrice() {
        return (this.quantity * this.sellPrice);
    }

    @Override
    public String toString() {
        return this.getName();
//        return "Product{" + "name=" + name + ", description=" + description + ", price=" + price + ", productNumber=" + productNumber + '}';
    }

    
    public boolean equals(Product p) {
        return p.getId()==this.getId();
    }
    
    

    public Object[] toTableRow(int rowNum) {
//        DecimalFormat df = new DecimalFormat("#,##0.##");
        return new Object[]{
            this,
            rowNum+1,
            this.name,
            this.productNumber,
            this.description,
            this.quantity,
            this.measure,
            this.buyPrice,
            this.sellPrice,
            this.company,
            this.getTotalPrice()};
    }
    
    public static String[] getTableColumnNames(){
        return new String[]{
            "Data", //0
            "№",//1
            "Name", //2
            "Number", //3
            "Description", //4
            "Quantity", //5
            "Measure", //6
            "Buy Price", //7
            "Sell Price", //8
            "Company", //9
            "Total Price Value" //10
        };
    }
    
    
    
    public static void fillRow(int row, Product p, DefaultTableModel model){
        
    }
}
