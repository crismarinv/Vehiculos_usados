package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/vehiculos_usados"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 

    public static Connection conectar() {
        Connection conexion = null;
        try {
            // Cargar el driver JDBC de MySQL
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            e.printStackTrace();
        }
        return conexion;
    }
}