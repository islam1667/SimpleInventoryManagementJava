package com.company.swingUI;

import com.company.swingUI.JPanels.JPanelAdd;
import com.company.swingUI.JPanels.JPanelBrowse;
import com.company.swingUI.JPanels.JPanelHistory;
import com.company.swingUI.JPanels.JPanelImport;
import com.company.swingUI.JPanels.JPanelSell;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author islam
 */
public class Config {
    public static final JPanel sellFrame = new JPanelSell();
    
    public static final JPanel addFrame = new JPanelAdd();
    
    public static final JPanel browseFrame = new JPanelBrowse();
    
    public static final JPanel importFrame = new JPanelImport();
    
    public static final JPanel historyFrame = new JPanelHistory();
    
    public static final CardLayout layout = new CardLayout();
    
    //column indexes
    public static final int DATA_COL = 0;
    public static final int N_COL = 1;
    public static final int NAME_COL = 2;
    public static final int DESC_COL = 3;
    public static final int NUMBER_COL = 4;
    public static final int QUANTITY_COL = 5;
    public static final int MEASURE_COL = 6;
    public static final int BUYP_COL = 7;
    public static final int SELLP_COL = 8;
    public static final int DISCOUNT_COL = 9;
    public static final int CURRENCY_COL = 10;
    public static final int COMPANY_COL = 11;
    public static final int TOTAL_COL = 12;

//            
//            "Data", //0
//            "№", //1
//            "Name", //2
//            "Number", //3
//            "Description", //4
//            "Quantity", //5
//            "Measure", //6
//            "Buy Price", //7
//            "Sell Price", //8
//            "Discount", //9
//            "Currency", //10
//            "Company", //11
//            "Total Price Value" //12
}
