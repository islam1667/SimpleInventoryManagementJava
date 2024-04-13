package com.company.swingUI.JPanels;

import com.company.bean.Product;
import com.company.dataAccess.implementation.ProductDAO;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FilterComboBox extends JComboBox {
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private JComboBox comboBox = this;

    public FilterComboBox() {
        super();
        this.setEditable(true);
        this.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        this.setMaximumRowCount(10);

        this.addFilterListener();

    }

    private void addFilterListener() {
        JTextField textField = (JTextField) comboBox.getEditor().getEditorComponent();
        
        textField.getDocument().addDocumentListener(new DocumentListener() {
            ScheduledFuture sf;
            private void action() {
                if(sf!=null) sf.cancel(true);
                
                sf = executor.schedule(new Runnable() {
                    @Override
                    public void run() {
                        String text = textField.getText();
                        comboBox.setModel(getDBFilteredModel(text));
                        try {
                            comboBox.setPopupVisible(true);
                        } catch (Exception e) {}
                    }
                }, 100, TimeUnit.MILLISECONDS);  
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                action();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                action();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    public DefaultComboBoxModel getDBFilteredModel(String enteredText) {
        ProductDAO dao = new ProductDAO();
        List<Product> products = dao.getSearch(enteredText);

        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (Product p : products) {
            model.addElement(p);
        }
        model.setSelectedItem(enteredText);
        return model;
    }
}
