package com.company.swingUI;

import com.company.swingUI.JPanels.JPanelAdd;
import com.company.swingUI.JPanels.JPanelBrowse;
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
    
    public static final CardLayout layout = new CardLayout();
}
