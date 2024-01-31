package com.company.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author islam
 */
public class DAO {
    public final Connection getConnection() throws SQLException{
//        Class.forName("jdbc://localhost:3306/users");
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "12345";
        Connection c = DriverManager.getConnection(url, username, password);
        return c;
    }
}
