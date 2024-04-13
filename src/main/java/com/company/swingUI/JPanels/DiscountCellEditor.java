package com.company.swingUI.JPanels;

import com.company.bean.Product;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.text.DefaultFormatter;
import com.company.swingUI.Config;

public class DiscountCellEditor extends DefaultCellEditor {

    private final EventCellInputChange event;
    private final JSpinner input;

    private JTable table;
    private int row;
    private Product item;

    public DiscountCellEditor(EventCellInputChange event) {
        super(new JCheckBox());
        this.event = event;

        input = new JSpinner();
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) input.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) editor.getTextField().getFormatter();
        formatter.setCommitsOnValidEdit(true);
        input.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 5.0));
        
        input.addChangeListener((ChangeEvent e) -> {
            inputChange();
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        super.getTableCellEditorComponent(table, value, isSelected, row, column);
        this.table = table;
        this.row = row;
        this.item = (Product) table.getValueAt(row, Config.DATA_COL);
        if(table.getValueAt(row, column) != null){
            input.setValue((double)table.getValueAt(row, column));
        }else{
            input.setValue(0.0);
        }
        if (this.item == null) {
            input.setEnabled(false);
        } else {
            input.setEnabled(false);
            enable();
        }
        return input;
    }

    private void enable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    input.setEnabled(true);
                } catch (Exception e) {
                }
            }
        }).start();
    }

    @Override
    public Object getCellEditorValue() {
        return input.getValue();
    }

    private void inputChange() {
        double qty = (double)table.getValueAt(row, Config.QUANTITY_COL);
        table.setValueAt(item.getSellPrice() * qty * ((100.0 - (double)input.getValue())/100) , row, Config.TOTAL_COL);
        table.setValueAt(input.getValue(), row, Config.DISCOUNT_COL);
        event.inputChanged(null, -1);
    }

}
