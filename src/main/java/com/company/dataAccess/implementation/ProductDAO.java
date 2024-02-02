package com.company.dataAccess.implementation;

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

    @Override
    public int insertProduct(Product p) {
        int affectedRows = 0;
        try (Connection c = getConnection();) {
            PreparedStatement ps = c.prepareStatement("INSERT INTO `producttable` "
                    + "(productName, productDescription, productNumber, productAmount, productPrice) "
                    + "VALUES (?,?,?,?,?)");
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getProductNumber());
            ps.setInt(4, p.getAmount());
            ps.setDouble(5, p.getPrice());
            affectedRows = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return affectedRows;
    }

    
    @Override
    public int sellProduct(int id, int amount) {
        int affectedRows = 0;
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("UPDATE `producttable` productAmount=productAmount-? WHERE id=?");
            ps.setInt(1, amount);
            ps.setInt(2, id);
            affectedRows = ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return affectedRows;
    }

    @Override
    public int updateProduct(int databaseId, Product p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Product getProduct(int id) {
        Product p = null;
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `producttable` WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                p = new Product(rs.getString("productName"),
                rs.getString("productDescription"),
                rs.getDouble("productPrice"),
                rs.getInt("id"),
                rs.getString("productNumber"),
                rs.getInt("productAmount"));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return p;
    }
    
    public Product ifExist(Product p) {
        Product pf = null;
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `producttable` WHERE"
                    + " productName=? AND"
                    + " productDescription=? AND"
                    + " productPrice=? AND"
                    + " productNumber=?");
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setDouble(3, p.getPrice());
            ps.setString(4, p.getProductNumber());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                pf = new Product(rs.getString("productName"),
                rs.getString("productDescription"),
                rs.getDouble("productPrice"),
                rs.getInt("id"),
                rs.getString("productNumber"),
                rs.getInt("productAmount"));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return pf;
    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `producttable`");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                products.add(new Product(rs.getString("productName"),
                rs.getString("productDescription"),
                rs.getDouble("productPrice"),
                rs.getInt("id"),
                rs.getString("productNumber"),
                rs.getInt("productAmount")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public int addProduct(int id, int amount) {
        int affectedRows = 0;
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("UPDATE `producttable` SET productAmount=productAmount+? WHERE id=?");
            ps.setInt(1, amount);
            ps.setInt(2, id);
            affectedRows = ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return affectedRows;
    }
    
    public List<Product> getSearch(String s){
        List<Product> products = new ArrayList<>();
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `producttable` WHERE"
                    + " productName LIKE ? OR"
                    + " productDescription LIKE ? OR"
                    + " CAST(ProductPrice AS CHAR) LIKE ? OR"
                    + " productNumber LIKE ? OR"
                    + " CAST(productAmount AS CHAR) LIKE ?");
            for(int i=1;i<6;i++) ps.setString(i, "%"+s+"%");
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                products.add(new Product(rs.getString("productName"),
                rs.getString("productDescription"),
                rs.getDouble("productPrice"),
                rs.getInt("id"),
                rs.getString("productNumber"),
                rs.getInt("productAmount")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return products;
    }

}
