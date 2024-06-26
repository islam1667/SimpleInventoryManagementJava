package com.company.bean;

import com.company.inter.Record;
/**
 *
 * @author islam
 */
public class SaleRecord extends Record {
    private int saleId;
    private int productId;
    private double quantity;
    private double buyPrice;
    private double sellPrice;
    private double discount;
    private int currency;

    public SaleRecord(int productId, double quantity, double buyPrice, double sellPrice, double discount, int currency) {
        this.productId = productId;
        this.quantity = quantity;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.discount = discount;
        this.currency = currency;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }
  
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    @Override
    public Object[] toTableRow(int row){
        return new Object[]{
            this,
            row+1,
            this.quantity,
            this.buyPrice,
            this.sellPrice,
            this.discount,
            this.currency
        };
    }
    
    @Override
    public Object[] getColumnNames(){
        return new Object[]{
            "Data",
            "№",
            "Quantity",
            "BuyPrice",
            "SellPrice",
            "Discount",
            "Curenncy"
        };
    }
}
