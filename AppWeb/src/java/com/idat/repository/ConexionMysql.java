package com.idat.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMysql {

    public Connection conectar() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/prueba?useSSL= false"; // Replace with your database name
            String user = "root";
            String password = "123456";
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return con;
    }
}
