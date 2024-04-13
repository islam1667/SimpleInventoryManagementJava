package com.company.dataAccess.inter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author islam
 */
public interface MainDAOInter {

    default Connection getConnection() throws SQLException {
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//        }catch(ClassNotFoundException ex){}
        String url = "jdbc:mysql://localhost:3306/";
        String username = "root";
        String password = "12345";

        Connection c = DriverManager.getConnection(url, username, password);

        //initializing db if not exists
        initializeDatabase(c);

        return c;
    }

    default void initializeDatabase(Connection c) {
        try (Statement s = c.createStatement()) {
            String databaseName = "inventoryManagementDB";
            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + databaseName
                    + " CHARACTER SET utf16 COLLATE utf16_general_ci";

            s.executeUpdate(createDatabaseQuery);
            s.execute("USE " + databaseName);

            //Product table
            String createTableQuery = "CREATE TABLE IF NOT EXISTS `products` ("
                    + "  `product_id` int NOT NULL AUTO_INCREMENT,"
                    + "  `product_name` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,"
                    + "  `product_description` text CHARACTER SET utf16 COLLATE utf16_general_ci,"
                    + "  `product_number` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,"
                    + "  `product_quantity` int NOT NULL,"
                    + "  `product_buyprice` double DEFAULT NULL,"
                    + "  `product_sellprice` double DEFAULT NULL,"
                    + "  `product_measure` int DEFAULT NULL,"
                    + "  `product_company` text,"
                    + "  PRIMARY KEY (`product_id`) USING BTREE"
                    + ") ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf16;";
            s.execute(createTableQuery);

            //Sales history table
            String createSalesTableQuery = "CREATE TABLE IF NOT EXISTS `sales` ("
                    + "  `sale_id` int NOT NULL AUTO_INCREMENT,"
                    + "  `product_id` int NOT NULL,"
                    + "  `quantity` int NOT NULL,"
                    + "  `buyprice` double NOT NULL,"
                    + "  `sellprice` double NOT NULL,"
                    + "  `discount` double DEFAULT NULL,"
                    + "  `currency` int NOT NULL,"
                    + "  `record_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                    + "  PRIMARY KEY (`sale_id`) USING BTREE,"
                    + "  KEY `f1` (`product_id`),"
                    + "  CONSTRAINT `f1` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)"
                    + ") ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf16;";
            s.execute(createSalesTableQuery);

            String createImportsTableQuery = "CREATE TABLE IF NOT EXISTS `imports` ("
                    + "  `import_id` int NOT NULL AUTO_INCREMENT,"
                    + "  `product_id` int NOT NULL,"
                    + "  `quantity` int NOT NULL,"
                    + "  `buyprice` double NOT NULL,"
                    + "  `sellprice` double NOT NULL,"
                    + "  `record_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                    + "  PRIMARY KEY (`import_id`) USING BTREE,"
                    + "  KEY `f2` (`product_id`),"
                    + "  CONSTRAINT `f2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf16;";
            s.execute(createImportsTableQuery);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
