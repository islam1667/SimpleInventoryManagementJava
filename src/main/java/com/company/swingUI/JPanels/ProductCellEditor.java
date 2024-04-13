package com.company.swingUI.JPanels;

import com.company.bean.Product;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import com.company.swingUI.Config;

/**
 *
 * @author islam
 */
public class ProductCellEditor extends DefaultCellEditor {

    private FilterComboBox comboBox;
    private EventCellInputChange event;
    private boolean isImport;

    public ProductCellEditor(EventCellInputChange e, boolean isImport) {
        super(new FilterComboBox());
        comboBox = new FilterComboBox();
        this.isImport = isImport;
        event = e;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        super.getTableCellEditorComponent(table, value, isSelected, row, column);
//        System.out.println("getTableCellEditor called");

        this.comboBox = new FilterComboBox();
        this.comboBox.setSelectedItem(table.getModel().getValueAt(row, 0));

        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    fireEditingStopped();
                    for (int i = 0; i < table.getRowCount() - 1; i++) {
                        if (((Product) comboBox.getSelectedItem()).equals((Product) table.getValueAt(i, Config.DATA_COL))) {
                            System.out.println("return called");
                            return;
                        }
                    }
                    if (comboBox.getSelectedIndex()!= -1 && ((Product) comboBox.getSelectedItem()).getQuantity() <= 0 && !isImport) {
                        System.out.println("Quantity <= 0");
                        return ;
                    }
                    if (comboBox.getSelectedIndex() != -1) {
                        itemChanged(table, value, isSelected, row, column);
                    }
                }
            }
        });

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });

        return this.comboBox;
    }

    @Override
    public Object getCellEditorValue() {
        return comboBox.getSelectedItem();
    }

    private void itemChanged(JTable table, Object value, boolean isSelected, int row, int column) {
        Product item = (Product) this.comboBox.getSelectedItem();
//        System.out.println("ItemChanged called");
        var model =  table.getModel();
        
        model.setValueAt(item, row, Config.DATA_COL);
        model.setValueAt(row+1, row, Config.N_COL);
        model.setValueAt(item.getProductNumber(), row, Config.NUMBER_COL);
        model.setValueAt(item.getDescription(), row, Config.DESC_COL);
        model.setValueAt(1.0, row, Config.QUANTITY_COL);
        model.setValueAt(item.getMeasure(), row, Config.MEASURE_COL);
        model.setValueAt(item.getBuyPrice(), row, Config.BUYP_COL);
        model.setValueAt(item.getSellPrice(), row, Config.SELLP_COL);
        model.setValueAt(0.0, row, Config.DISCOUNT_COL);
        model.setValueAt("AZN", row, Config.CURRENCY_COL);
        model.setValueAt(item.getCompany(), row, Config.COMPANY_COL);
        
        // total price change inside input changed method
        event.inputChanged(item, row);
    }

}
