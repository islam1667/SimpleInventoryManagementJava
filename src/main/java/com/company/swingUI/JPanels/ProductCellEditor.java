package com.company.swingUI.JPanels;

import com.company.bean.Product;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;

/**
 *
 * @author islam
 */
public class ProductCellEditor extends DefaultCellEditor {

    private FilterComboBox comboBox;
    private EventCellInputChange event;

    public ProductCellEditor(EventCellInputChange e) {
        super(new FilterComboBox());
        comboBox = new FilterComboBox();
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
        table.getModel().setValueAt(item, row, 0);
        table.getModel().setValueAt(item.getProductNumber(), row, 2);
        table.getModel().setValueAt(item.getDescription(), row, 3);
        table.getModel().setValueAt(1, row, 4);
        table.getModel().setValueAt(item.getPrice(), row, 5);
        table.getModel().setValueAt((item.getQuantity()==0) ? 0 : item.getPrice(), row, 6);
        
        event.inputChanged();
    }

}
