package com.uduran.apiserverlet.webapp10.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class ConexionBaseDatos {
    private static String url = "jdbc:sqlite://C:/Users/UlisesDurán/IdeaProjects/HelloWorld/webapp-10/webapp10/basedatos/carrocompra.db";

    private static Connection conn;

    public static Connection getInstance() throws SQLException {
        if(Objects.isNull(conn) || conn.isClosed()){
            try{
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                System.err.println("Error al cargar el driver JDBC de SQLite " + e.getMessage());
                e.printStackTrace();
            }

            conn = DriverManager.getConnection(url);
        }
        return conn;
    }

    public static void closeConnection() throws SQLException {
        if (!Objects.isNull(conn) || !conn.isClosed()) {
            conn.close();
        }
    }
}
