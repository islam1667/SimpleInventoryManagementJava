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

    private Product getProduct(ResultSet rs){
        try{
        Product p = new Product(rs.getString("product_name"),
                rs.getString("product_description"),
                rs.getDouble("product_price"),
                rs.getInt("product_id"),
                rs.getString("product_number"),
                rs.getInt("product_quantity"));
        return p;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public int insertProduct(Product p) {
        int affectedRows = 0;
        try (Connection c = getConnection();) {
            PreparedStatement ps = c.prepareStatement("INSERT INTO `products` "
                    + "(product_name, product_description, product_number, product_quantity, product_price) "
                    + "VALUES (?,?,?,?,?)");
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getProductNumber());
            ps.setInt(4, p.getQuantity());
            ps.setDouble(5, p.getPrice());
            affectedRows = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return affectedRows;
    }
    
    @Override
    public int addProduct(int id, int quantity) {
        int affectedRows = 0;
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("UPDATE `products` SET product_quantity=product_quantity+? WHERE product_id=?");
            ps.setInt(1, quantity);
            ps.setInt(2, id);
            affectedRows = ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return affectedRows;
    }
    
    @Override
    public int sellProduct(int id, int quantity) {
        //Can be done with sql triggers
        return addProduct(id, -quantity);
    }

    @Override
    public int updateProduct(int databaseId, Product p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Product getProduct(int id) {
        Product p = null;
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `products` WHERE product_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                p = getProduct(rs);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return p;
    }
    
    public Product ifExist(Product p) {
        Product pf = null;
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `products` WHERE"
                    + " product_name=? AND"
                    + " product_description=? AND"
                    + " product_price=? AND"
                    + " product_number=?");
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setDouble(3, p.getPrice());
            ps.setString(4, p.getProductNumber());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                pf = getProduct(rs);
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
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `products`");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                products.add(getProduct(rs));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return products;
    }
    
    public List<Product> getSearch(String s){
        return getSearch(s, 1000);
    }
    
    public List<Product> getSearch(String s, int limit){
        List<Product> products = new ArrayList<>();
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `products` WHERE"
                    + " product_name LIKE ? OR"
                    + " product_description LIKE ? OR"
                    + " CAST(Product_price AS CHAR) LIKE ? OR"
                    + " product_number LIKE ? OR"
                    + " CAST(product_quantity AS CHAR) LIKE ?"
                    + " LIMIT ?");
            for(int i=1;i<6;i++) ps.setString(i, "%"+s+"%");
            
            ps.setInt(6, limit);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                products.add(getProduct(rs));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return products;
    }

}
