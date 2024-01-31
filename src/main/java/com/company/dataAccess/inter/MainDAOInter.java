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

            String createTableQuery = "CREATE TABLE IF NOT EXISTS `producttable`  ("
                    + "  `id` int NOT NULL AUTO_INCREMENT,"
                    + "  `productName` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,"
                    + "  `productDescription` text CHARACTER SET utf16 COLLATE utf16_general_ci NULL,"
                    + "  `productNumber` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,"
                    + "  `productAmount` int NOT NULL,"
                    + "  `productPrice` double NULL DEFAULT NULL,"
                    + "  PRIMARY KEY (`id`) USING BTREE"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf16;";
            s.execute(createTableQuery);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
