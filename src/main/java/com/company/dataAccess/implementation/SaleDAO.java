package com.company.dataAccess.implementation;

import com.company.bean.SaleRecord;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import com.company.dataAccess.inter.SaleDAOInter;

/**
 *
 * @author islam
 */
public class SaleDAO implements SaleDAOInter {

    public int sellProducts(List<SaleRecord> sales) {
        ProductDAO pdao = new ProductDAO();
        try (Connection c = getConnection();) {
            String query = "INSERT INTO `sales` (product_id, quantity, price) VALUES ";
            for (SaleRecord sr : sales) {
                if (pdao.sellProduct(sr.getProductId(), sr.getQuantity()) == 0) {
                    throw new Exception("AffR=0 error Nothing sold, passed selling it, productId=" + sr.getProductId());
                }
                query = query.concat("(" + sr.getProductId() + "," + sr.getQuantity() + "," + sr.getPrice() + "),");
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
}
