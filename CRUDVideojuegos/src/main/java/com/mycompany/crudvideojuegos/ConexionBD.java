/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudvideojuegos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Daniel
 */

public class ConexionBD {
private static Connection conect = null;
    
    // Datos de conexión
    private static final String URL = "jdbc:mysql://localhost/videojuegos_db";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "1234";
    
    // Método para obtener la conexión
    public static Connection getConexion() {
        try {
            // Registrar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establecer la conexión
            conect = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
            
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver de MySQL");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos");
            e.printStackTrace();
        }
        
        return conect;
    }
    
    // Método para cerrar la conexión
    public static void cerrarConexion() {
        try {
            if (conect != null && !conect.isClosed()) {
                conect.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión");
            e.printStackTrace();
        }
    }
}
