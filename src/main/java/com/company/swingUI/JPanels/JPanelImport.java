package com.company.swingUI.JPanels;

import com.company.bean.ImportRecord;
import com.company.bean.Product;
import com.company.dataAccess.implementation.ImportDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.company.swingUI.Config;

/**
 *
 * @author islam
 */
public class JPanelImport extends javax.swing.JPanel {

    /**
     * Creates new form JPanelSell
     */
    public JPanelImport() {
        initComponents();
        configureTable();
    }

    private static final int TOTAL_COL = 11;
    private static final int QUANTITY_COL = 5;
    private static final int SELLP_COL = 8;
    private static final int BUYP_COL = 7;
    
    private void configureTable() {
        DefaultTableModel model = new DefaultTableModel(
                new String[]{
                    "Data", //0
                    "№", //1
                    "Name", //2
                    "Number", //3
                    "Description", //4
                    "Quantity", //5
                    "Measure", //6
                    "Buy Price", //7
                    "Sell Price", //8
                    // "Discount", //
                    "Currency", //9
                    "Company", //10
                    "Total Price Value" //11
                }, 1);

        jTableImport.setModel(model);
        jTableImport.setRowHeight(25);
        jTableImport.getColumnModel().getColumn(Config.DATA_COL).setMaxWidth(0);
        jTableImport.getColumnModel().getColumn(Config.N_COL).setMaxWidth(50);
        
        //filter combo box cell editor
        jTableImport.getColumnModel().getColumn(Config.NAME_COL).setCellEditor(new ProductCellEditor(new EventCellInputChange() {
            @Override
            public void inputChanged(Product item, int row) {
                jTableImport.getModel().setValueAt((item.getQuantity() == 0) ? 0.0 : item.getSellPrice(), row, TOTAL_COL);
                DefaultTableModel model = (DefaultTableModel) jTableImport.getModel();
                if (model.getValueAt(model.getRowCount() - 1, Config.DATA_COL) != null) {
                    model.addRow(new Object[model.getColumnCount()]);
                }
                sumAndShow();
                System.out.println("Input Change Fired");
            }
        }, true));

        //Quantity cell editor
        jTableImport.getColumnModel().getColumn(QUANTITY_COL).setCellEditor(new QtyCellEditor(new EventCellInputChange() {
            @Override
            public void inputChanged(Product p, int row) {
                sumAndShow();
            }
        }, true));
    }


    public double sumAndShow() {
        double sum = 0;
        for (int row = 0; row < jTableImport.getRowCount() - 1; row++) {
            sum += (double) jTableImport.getValueAt(row, BUYP_COL);
        }
        totalJLabel.setText("Total: " + String.format("%.2f", sum));
        return sum;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableImport = new javax.swing.JTable();
        totalJLabel = new javax.swing.JLabel();

        jButton1.setText("Process import");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTableImport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Data", "Part_Name", "Part_Number", "Part_Description", "Part_Quantity_Import", "Part_Price", "Total_Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableImport.setToolTipText("");
        jTableImport.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTableImport);
        if (jTableImport.getColumnModel().getColumnCount() > 0) {
            jTableImport.getColumnModel().getColumn(0).setResizable(false);
            jTableImport.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTableImport.getColumnModel().getColumn(1).setPreferredWidth(160);
            jTableImport.getColumnModel().getColumn(3).setPreferredWidth(0);
            jTableImport.getColumnModel().getColumn(5).setResizable(false);
            jTableImport.getColumnModel().getColumn(5).setPreferredWidth(0);
            jTableImport.getColumnModel().getColumn(6).setResizable(false);
            jTableImport.getColumnModel().getColumn(6).setPreferredWidth(0);
        }

        totalJLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        totalJLabel.setText("Import page");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totalJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(totalJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTableImport.clearSelection();
        DefaultTableModel model = (DefaultTableModel) jTableImport.getModel();
        List<ImportRecord> ir = this.modelToRecord(model);
        if (ir == null) {
            JOptionPane.showMessageDialog(this, "Quantity cant be 0.");
            return;
        } else if (ir.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nothing to sell.");
            return;
        }

        ImportDAO idao = new ImportDAO();
        int affectedRows = idao.importProducts(ir);
        if (affectedRows == 0) {
            System.out.println("Nothing inserted to db, something might gone wrong!");
        }
        model.setRowCount(0);
        model.setRowCount(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private List<ImportRecord> modelToRecord(DefaultTableModel model) {
        List<ImportRecord> ir = new ArrayList<>();
        for (int row = 0; row < model.getRowCount() - 1; row++) {
            if (((double) model.getValueAt(row, QUANTITY_COL)) <= 0) {
                System.out.println("There is 0");
                return null;
            }

            ir.add(new ImportRecord(((Product) model.getValueAt(row, Config.DATA_COL)).getId(),
                    (double) model.getValueAt(row, QUANTITY_COL),
                    (double) model.getValueAt(row, BUYP_COL),
                    (double) model.getValueAt(row, SELLP_COL),
                    1));
            //System.out.println(model.getDataVector().get(row));

        }
        return ir;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableImport;
    private javax.swing.JLabel totalJLabel;
    // End of variables declaration//GEN-END:variables
}
