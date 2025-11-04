/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudvideojuegos;
import java.sql.*;

/**
 *
 * @author Daniel
 */
public class UsuarioDAO {
 private Connection conexion;
    
    // Constructor
    public UsuarioDAO() {
        this.conexion = ConexionBD.getConexion();
    }
    
    // Método para validar login
    public Usuario validarUsuario(String nombreUsuario, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ?";
        Usuario usuario = null;
        
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, nombreUsuario);
            pst.setString(2, contrasena);
            
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setNombreCompleto(rs.getString("nombre_completo"));
                usuario.setRol(rs.getString("rol"));
                usuario.setFechaRegistro(rs.getTimestamp("fecha_registro"));
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error al validar usuario: " + e.getMessage());
            e.printStackTrace();
        }
        
        return usuario;
    }
    
    // Método para registrar un nuevo usuario
    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre_usuario, contrasena, nombre_completo, rol) " +
                    "VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, usuario.getNombreUsuario());
            pst.setString(2, usuario.getContrasena());
            pst.setString(3, usuario.getNombreCompleto());
            pst.setString(4, usuario.getRol());
            
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // Método para verificar si existe un usuario
    public boolean existeUsuario(String nombreUsuario) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE nombre_usuario = ?";
        
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, nombreUsuario);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error al verificar usuario: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
}