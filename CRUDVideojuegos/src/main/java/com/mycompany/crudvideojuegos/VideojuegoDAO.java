/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudvideojuegos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Daniel
 */
public class VideojuegoDAO {
private Connection conexion;
    
    // Constructor
    public VideojuegoDAO() {
        this.conexion = ConexionBD.getConexion();
    }
    
    // CREATE - Insertar un nuevo videojuego
    public boolean insertarVideojuego(Videojuego videojuego) {
        String sql = "INSERT INTO videojuegos (titulo, genero, plataforma, desarrollador, " +
                    "año_lanzamiento, precio, clasificacion, descripcion) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, videojuego.getTitulo());
            pst.setString(2, videojuego.getGenero());
            pst.setString(3, videojuego.getPlataforma());
            pst.setString(4, videojuego.getDesarrollador());
            pst.setInt(5, videojuego.getAnioLanzamiento());
            pst.setDouble(6, videojuego.getPrecio());
            pst.setString(7, videojuego.getClasificacion());
            pst.setString(8, videojuego.getDescripcion());
            
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al insertar videojuego: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // READ - Obtener todos los videojuegos
    public List<Videojuego> obtenerTodosLosVideojuegos() {
        List<Videojuego> lista = new ArrayList<>();
        String sql = "SELECT * FROM videojuegos ORDER BY id";
        
        try (Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Videojuego v = new Videojuego();
                v.setId(rs.getInt("id"));
                v.setTitulo(rs.getString("titulo"));
                v.setGenero(rs.getString("genero"));
                v.setPlataforma(rs.getString("plataforma"));
                v.setDesarrollador(rs.getString("desarrollador"));
                v.setAnioLanzamiento(rs.getInt("año_lanzamiento"));
                v.setPrecio(rs.getDouble("precio"));
                v.setClasificacion(rs.getString("clasificacion"));
                v.setDescripcion(rs.getString("descripcion"));
                
                lista.add(v);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener videojuegos: " + e.getMessage());
            e.printStackTrace();
        }
        
        return lista;
    }
    
    // READ - Obtener un videojuego por ID
    public Videojuego obtenerVideojuegoPorId(int id) {
        String sql = "SELECT * FROM videojuegos WHERE id = ?";
        Videojuego videojuego = null;
        
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                videojuego = new Videojuego();
                videojuego.setId(rs.getInt("id"));
                videojuego.setTitulo(rs.getString("titulo"));
                videojuego.setGenero(rs.getString("genero"));
                videojuego.setPlataforma(rs.getString("plataforma"));
                videojuego.setDesarrollador(rs.getString("desarrollador"));
                videojuego.setAnioLanzamiento(rs.getInt("año_lanzamiento"));
                videojuego.setPrecio(rs.getDouble("precio"));
                videojuego.setClasificacion(rs.getString("clasificacion"));
                videojuego.setDescripcion(rs.getString("descripcion"));
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error al obtener videojuego por ID: " + e.getMessage());
            e.printStackTrace();
        }
        
        return videojuego;
    }
    
    // UPDATE - Actualizar un videojuego
    public boolean actualizarVideojuego(Videojuego videojuego) {
        String sql = "UPDATE videojuegos SET titulo=?, genero=?, plataforma=?, " +
                    "desarrollador=?, año_lanzamiento=?, precio=?, clasificacion=?, " +
                    "descripcion=? WHERE id=?";
        
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, videojuego.getTitulo());
            pst.setString(2, videojuego.getGenero());
            pst.setString(3, videojuego.getPlataforma());
            pst.setString(4, videojuego.getDesarrollador());
            pst.setInt(5, videojuego.getAnioLanzamiento());
            pst.setDouble(6, videojuego.getPrecio());
            pst.setString(7, videojuego.getClasificacion());
            pst.setString(8, videojuego.getDescripcion());
            pst.setInt(9, videojuego.getId());
            
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar videojuego: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // DELETE - Eliminar un videojuego
    public boolean eliminarVideojuego(int id) {
        String sql = "DELETE FROM videojuegos WHERE id = ?";
        
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, id);
            
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar videojuego: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // Método para buscar videojuegos por título
    public List<Videojuego> buscarPorTitulo(String titulo) {
        List<Videojuego> lista = new ArrayList<>();
        String sql = "SELECT * FROM videojuegos WHERE titulo LIKE ? ORDER BY id";
        
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, "%" + titulo + "%");
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                Videojuego v = new Videojuego();
                v.setId(rs.getInt("id"));
                v.setTitulo(rs.getString("titulo"));
                v.setGenero(rs.getString("genero"));
                v.setPlataforma(rs.getString("plataforma"));
                v.setDesarrollador(rs.getString("desarrollador"));
                v.setAnioLanzamiento(rs.getInt("año_lanzamiento"));
                v.setPrecio(rs.getDouble("precio"));
                v.setClasificacion(rs.getString("clasificacion"));
                v.setDescripcion(rs.getString("descripcion"));
                
                lista.add(v);
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("Error al buscar videojuegos: " + e.getMessage());
            e.printStackTrace();
        }
        
        return lista;
    }
}
