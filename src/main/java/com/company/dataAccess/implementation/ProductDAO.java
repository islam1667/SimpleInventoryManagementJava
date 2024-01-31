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
    public boolean insertProduct(Product p) {
        boolean successful = false;
        try (Connection c = getConnection();) {
            PreparedStatement ps = c.prepareStatement("INSERT INTO `producttable` "
                    + "(productName, productDescription, productNumber, productAmount, productPrice) "
                    + "VALUES (?,?,?,?,?)");
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getProductNumber());
            ps.setInt(4, p.getAmount());
            ps.setDouble(5, p.getPrice());
            successful = ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return successful;
    }

    
    @Override
    public boolean sellProduct(int id, int amount) {
        boolean successful = false;
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("UPDATE `producttable` productAmount=productAmount-? WHERE id=?");
            ps.setInt(1, amount);
            ps.setInt(2, id);
            successful = ps.execute();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return successful;
    }

    @Override
    public boolean updateProduct(int databaseId, Product p) {
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
    public boolean addProduct(int id, int amount) {
        boolean successful = false;
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("UPDATE `producttable` productAmount=productAmount+? WHERE id=?");
            ps.setInt(1, amount);
            ps.setInt(2, id);
            successful = ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return successful;
    }

}
