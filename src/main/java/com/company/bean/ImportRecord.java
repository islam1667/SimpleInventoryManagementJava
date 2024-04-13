package com.company.bean;

import com.company.inter.Record;
/**
 *
 * @author islam
 */
public class ImportRecord extends Record {
    //recordId inside Record
    private int productId;
    private double quantity;
    private double buyPrice;
    private double sellPrice;
    private int currency;

    public ImportRecord(int productId, double quantity, double buyPrice, double sellPrice, int currency) {
        this.productId = productId;
        this.quantity = quantity;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.currency = currency;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
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

    public double getImportPrice() {
        return buyPrice;
    }

    public void setImportPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getBuyPrice() {
        return sellPrice;
    }

    public void setBuyPrice(double sellPrice) {
        this.sellPrice = sellPrice;
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
            "Curenncy"
        };
    }
}
