package com.company.bean;

/**
 *
 * @author islam
 */
public enum MeasureType {
    ITEM(1, "Item"),
    LITER(2, "Liter"),
    PAIR(3, "Pair");
    
    private final int intValue;
    private final String name;
    
    MeasureType(int intValue, String name){
        this.intValue = intValue;    
        this.name = name;
    }
    
    public int getIntValue(){
        return this.intValue;
    }
    
    public static MeasureType getMeasure(int intValue){
        for(MeasureType m : MeasureType.values()){
            if(m.getIntValue() == intValue){
                return m;
            }
        }
        return null;
    }

    @Override
    public String toString() {
       return this.name;
    }
}
