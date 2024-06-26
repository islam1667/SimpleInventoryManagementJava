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


public class QtyCellEditor extends DefaultCellEditor {

    private final EventCellInputChange event;
    private final JSpinner input;

    private JTable table;
    private int row;
    private Product item;
    private boolean isImport;

    public QtyCellEditor(EventCellInputChange event, boolean isImport) {
        super(new JCheckBox());
        this.event = event;
        this.isImport = isImport;
        input = new JSpinner();
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) input.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) editor.getTextField().getFormatter();
        formatter.setCommitsOnValidEdit(true);

        input.addChangeListener((ChangeEvent e) -> {
            inputChange();
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        super.getTableCellEditorComponent(table, value, isSelected, row, column);
        this.table = table;
        this.row = row;
        this.item = (Product) table.getValueAt(row, 0);
        if (this.item == null) {
            input.setEnabled(false);
        } else {
//            double qty = Double.parseDouble(value.toString());
//            input.setValue(qty);
            if (item.getQuantity() <= 0 && !isImport) {
                input.setValue(0.0);
                input.setEnabled(false);
                return input;
            }
            
            if(isImport){
                input.setModel(new SpinnerNumberModel((double)table.getValueAt(row, column), 0.0, Integer.MAX_VALUE, 1.0));
            }else{
//                System.out.println(row + " " + "");
                input.setModel(new SpinnerNumberModel((double)table.getValueAt(row, column), 0.0, item.getQuantity(), 1.0));
            }
            
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
        System.out.println("qty val:" + input.getValue());
        return input.getValue();
    }

    private void inputChange() {
        double qty = Double.parseDouble(input.getValue().toString());
        table.setValueAt((double)item.getSellPrice() * qty * ((100.0 - (double)table.getValueAt(row, Config.DISCOUNT_COL))/100) , row, Config.TOTAL_COL);
        table.setValueAt(input.getValue(), row, Config.DISCOUNT_COL);
        event.inputChanged(null, -1);
    }

}
