package com.company.dataAccess.implementation;

import com.company.bean.MeasureType;
import com.company.bean.Product;
import com.company.dataAccess.inter.ProductDAOInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author islam
 */
public class ProductDAO implements ProductDAOInter {

    private Product getProduct(ResultSet rs) {
        //Product(int id, String name, String description, double buyPrice, double sellPrice, String productNumber, double quantity, MeasureType measure, String company)
        try {
            Product p = new Product(rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getString("product_description"),
                    rs.getDouble("product_buyprice"),
                    rs.getDouble("product_sellprice"),
                    rs.getString("product_number"),
                    rs.getDouble("product_quantity"),
                    MeasureType.getMeasure(rs.getInt("product_measure")),
                    rs.getString("product_company"));
            return p;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insertProduct(Product p) {
        int affectedRows = 0;
        try (Connection c = getConnection();) {
            PreparedStatement ps = c.prepareStatement("INSERT INTO `products` "
                    + "(product_name, product_description, product_number, product_quantity, product_buyprice, product_sellprice, product_measure, product_company) "
                    + "VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getProductNumber());
            ps.setDouble(4, p.getQuantity());
            ps.setDouble(5, p.getBuyPrice());
            ps.setDouble(6, p.getSellPrice());
            ps.setInt(7, p.getMeasure().getIntValue());
            ps.setString(8, p.getCompany());
            affectedRows = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return affectedRows;
    }

    @Override
    public int addProduct(int id, double quantity) {
        int affectedRows = 0;
        try (Connection c = getConnection()) {
            PreparedStatement ps = c.prepareStatement("UPDATE `products` SET product_quantity=product_quantity+? WHERE product_id=?");
            ps.setDouble(1, quantity);
            ps.setInt(2, id);
            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    @Override
    public int sellProduct(int id, double quantity) {
        //Can be done with sql triggers
        return addProduct(id, -quantity);
    }

    @Override
    public int updateProduct(int Id, Product p) {
        int affectedRows = 0;
        try (Connection c = getConnection()) {
            PreparedStatement ps = c.prepareStatement("UPDATE `products` SET"
                    + " product_name = ?," //1
                    + " product_description = ?," //2
                    + " product_number = ?," //3
                    + " product_quantity = ?," //4
                    + " product_sellprice = ?," //5
                    + " product_buyprice = ?," //6
                    + " product_measure = ?," //7
                    + " product_company = ? WHERE product_id = ?"); //8 9
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getProductNumber());
            ps.setDouble(4, p.getQuantity());
            ps.setDouble(5, p.getSellPrice());
            ps.setDouble(6, p.getBuyPrice());
            ps.setInt(7, p.getMeasure().getIntValue());
            ps.setString(8, p.getCompany());
            ps.setInt(9, p.getId());
            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    @Override
    public Product getProduct(int id) {
        Product p = null;
        try (Connection c = getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `products` WHERE product_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = getProduct(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }

    public Product ifExist(Product p) {
        Product pf = null;
        try (Connection c = getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `products` WHERE"
                    + " product_name=? AND"
                    + " product_description=? AND"
                    + " product_buyprice=? AND"
                    + " product_sellprice=? AND"
                    + " product_number=? AND"
                    + " product_measure=? AND"
                    + " product_company=?");
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setDouble(3, p.getBuyPrice());
            ps.setDouble(4, p.getSellPrice());
            ps.setString(5, p.getProductNumber());
            ps.setInt(6, p.getMeasure().getIntValue());
            ps.setString(7, p.getCompany());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pf = getProduct(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pf;
    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();
        try (Connection c = getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `products`");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(getProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> getSearch(String s) {
        return getSearch(s, 1000);
    }

    public List<Product> getSearch(String s, int limit) {
        List<Product> products = new ArrayList<>();
        try (Connection c = getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `products` WHERE"
                    + " product_name LIKE ? OR"
                    + " product_description LIKE ? OR"
                    + " CAST(product_buyprice AS CHAR) LIKE ? OR"
                    + " CAST(product_sellprice AS CHAR) LIKE ? OR"
                    + " product_number LIKE ? OR"
                    + " CAST(product_quantity AS CHAR) LIKE ? OR"
                    + " product_company LIKE ?"
                    + " LIMIT ?");
            for (int i = 1; i < 8; i++) {
                ps.setString(i, "%" + s + "%");
            }

            ps.setInt(8, limit);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(getProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

}
