package com.uduran.apiserverlet.webapp10.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class ConexionBaseDatos {
    private static String url = "jdbc:mysql://localhost:3306/java_curso?serverTimezone=Europe/Madrid";
    private static String username = "root";
    private static String password = "gilro";
    private static Connection conn;

    public static Connection getInstance() throws SQLException {
        if(Objects.isNull(conn)){
            conn = DriverManager.getConnection(url, username, password);
        }
        return conn;
    }
}
