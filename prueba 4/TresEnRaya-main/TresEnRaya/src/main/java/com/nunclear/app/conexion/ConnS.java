
package com.nunclear.app.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnS {
    private static volatile ConnS instance;
    private static volatile Connection connection;

    private ConnS() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnS.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to create ");
        }
        if (connection != null) {
            throw new RuntimeException("Use getConnection() method to create ");
        }
    }

    public static ConnS getInstance() {
        if (instance == null) {
            synchronized (ConnS.class) {
                if (instance == null) {
                    instance = new ConnS();
                    System.out.println("Se instanció ConnS");
                }
            }
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        if (connection == null) {
            try {
                String dbUrl = "jdbc:sqlite:data/Resultado.db?foreign_keys=on;";
                connection = DriverManager.getConnection(dbUrl);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
