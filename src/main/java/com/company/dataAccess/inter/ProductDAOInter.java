package com.company.dataAccess.inter;

import com.company.bean.Product;
import java.util.List;

/**
 *
 * @author islam
 */
public interface ProductDAOInter extends MainDAOInter{
    public boolean insertProduct(Product p);
    
    public boolean addProduct(int id, int amount);
    
    public boolean sellProduct(int id, int amount);
    
    public boolean updateProduct(int id, Product p);
    
    public Product getProduct(int databaseId);
    
    public List<Product> getAllProduct();
}
