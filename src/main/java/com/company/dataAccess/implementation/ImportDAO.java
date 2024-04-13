package com.company.dataAccess.implementation;

import com.company.bean.ImportRecord;
import com.company.dataAccess.inter.ImportDAOInter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author islam
 */
public class ImportDAO implements ImportDAOInter {

    private List<ImportRecord> resultSetToList(ResultSet rs) {
        List<ImportRecord> sales = new ArrayList<>();
        try {
            while (rs.next()) {
                sales.add(new ImportRecord(
                        rs.getInt("product_id"),
                        rs.getDouble("quantity"),
                        rs.getDouble("buyprice"),
                        rs.getDouble("sellprice"),
                        rs.getInt("currency")
                ));
            }
            return sales;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int importProducts(List<ImportRecord> sales) {
        ProductDAO pdao = new ProductDAO();
        try (Connection c = getConnection();) {
            String query = "INSERT INTO `imports` (product_id, quantity, buyprice, sellprice, currency) VALUES ";
            for (ImportRecord ir : sales) {
                if (pdao.addProduct(ir.getProductId(), ir.getQuantity()) == 0) {
                    throw new Exception("AffR=0 error Nothing sold, passed selling it, productId=" + ir.getProductId());
                }
                query = query.concat("(" + ir.getProductId() + "," + ir.getQuantity() + "," + ir.getImportPrice() + "," + ir.getBuyPrice() + "," + ir.getCurrency() + "),");
            }
            query = query.substring(0, query.length() - 1).concat(";");
            System.out.println(query);
            return c.prepareStatement(query).executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int importProduct(ImportRecord ir) {
        return importProducts(Arrays.asList(ir));
    }
    
    public List<ImportRecord> getImports(Date d1, Date d2) {
        try (Connection c = getConnection();) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `imports` WHERE `record_date` BETWEEN ? AND ?");
            ps.setDate(1, d1);
            ps.setDate(2, d2);

            ResultSet rs = ps.executeQuery();
            return this.resultSetToList(rs);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
