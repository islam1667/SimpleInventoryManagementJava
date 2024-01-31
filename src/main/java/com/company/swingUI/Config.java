package com.company.swingUI;

import com.company.swingUI.JPanels.AddJInternalFrame;
import com.company.swingUI.JPanels.BrowseJInternalFrame;
import com.company.swingUI.JPanels.SellJInternalFrame;
import javax.swing.JInternalFrame;

/**
 *
 * @author islam
 */
public class Config {
    public static JInternalFrame sellFrame = new SellJInternalFrame();
    
    public static JInternalFrame addFrame = new AddJInternalFrame();
    
    public static JInternalFrame browseFrame = new BrowseJInternalFrame();
}
