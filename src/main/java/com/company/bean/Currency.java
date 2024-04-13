/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.bean;

/**
 *
 * @author islam
 */
public enum Currency {
    AZN("AZN", 1),
    EUR("EUR", 2),
    USD("USD", 3),
    TRY("TRY", 4);
    
    Currency(String name, int intValue){
        this.name = name;
        this.intValue = intValue;
    }
    
    private final String name;
    private final int intValue;
    
    public int getIntValue(){
        return this.intValue;
    }
    
    public String getName(){
        return this.name;
    }
    
    public static Currency getCurrency(int intValue){
        for(Currency m : Currency.values()){
            if(m.getIntValue() == intValue){
                return m;
            }
        }
        return null;
    }
    
    public static int getIntValue(String name){
        for(Currency m : Currency.values()){
            if(m.getName() == name){
                return m.getIntValue();
            }
        }
        return 0;
    }

    @Override
    public String toString() {
       return this.name;
    }
}
