package com.company.dataAccess.implementation;

import com.company.bean.SaleRecord;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import com.company.dataAccess.inter.SaleDAOInter;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author islam
 */
public class SaleDAO implements SaleDAOInter {

    private List<SaleRecord> resultSetToList(ResultSet rs) {
        List<SaleRecord> sales = new ArrayList<>();
        try {
            while (rs.next()) {
                sales.add(new SaleRecord(
                        rs.getInt("product_id"),
                        rs.getDouble("quantity"),
                        rs.getDouble("buyprice"),
                        rs.getDouble("sellprice"),
                        rs.getDouble("Discount"),
                        rs.getInt("currency")
                ));
            }
            return sales;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int sellProducts(List<SaleRecord> sales) {
        ProductDAO pdao = new ProductDAO();
        try (Connection c = getConnection();) {
            String query = "INSERT INTO `sales` (product_id, quantity, buyprice, sellprice, discount, currency) VALUES ";
            for (SaleRecord sr : sales) {
                if (pdao.sellProduct(sr.getProductId(), sr.getQuantity()) == 0) {
                    throw new Exception("AffR=0 error Nothing sold, passed selling it, productId=" + sr.getProductId());
                }
                query = query.concat("(" + sr.getProductId() + "," + sr.getQuantity() + "," + sr.getBuyPrice() + "," + sr.getSellPrice() + "," + sr.getDiscount() + "," + sr.getCurrency() + "),");
            }
            query = query.substring(0, query.length() - 1).concat(";");
            System.out.println(query);
            return c.prepareStatement(query).executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int sellProduct(SaleRecord sr) {
        return sellProducts(Arrays.asList(sr));
    }

    public List<SaleRecord> getSales(Date d1, Date d2) {
        try (Connection c = getConnection();) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `sales` WHERE `record_date` BETWEEN ? AND ?");
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
