/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import static sun.jvm.hotspot.HelloWorld.e;

/**
 *
 * @author tranh
 */
public class DBProvider {

    private static String DB_URL = "jdbc:mysql://localhost:3306/abc";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }

        return connection;
    }

}
