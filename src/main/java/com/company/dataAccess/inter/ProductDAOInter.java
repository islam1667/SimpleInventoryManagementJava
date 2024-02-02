package com.company.dataAccess.inter;

import com.company.bean.Product;
import java.util.List;

/**
 *
 * @author islam
 */
public interface ProductDAOInter extends MainDAOInter{
    public int insertProduct(Product p);
    
    public int addProduct(int id, int amount);
    
    public int sellProduct(int id, int amount);
    
    public int updateProduct(int id, Product p);
    
    public Product getProduct(int databaseId);
    
    public List<Product> getAllProduct();
}
