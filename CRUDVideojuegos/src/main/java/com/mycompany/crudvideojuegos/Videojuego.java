/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudvideojuegos;

/**
 *
 * @author Daniel
 */
public class Videojuego {
private int id;
    private String titulo;
    private String genero;
    private String plataforma;
    private String desarrollador;
    private int anioLanzamiento;
    private double precio;
    private String clasificacion;
    private String descripcion;
    
    // Constructor vacío
    public Videojuego() {
    }
    
    // Constructor con parámetros (sin ID para inserciones)
    public Videojuego(String titulo, String genero, String plataforma, 
                     String desarrollador, int anioLanzamiento, double precio, 
                     String clasificacion, String descripcion) {
        this.titulo = titulo;
        this.genero = genero;
        this.plataforma = plataforma;
        this.desarrollador = desarrollador;
        this.anioLanzamiento = anioLanzamiento;
        this.precio = precio;
        this.clasificacion = clasificacion;
        this.descripcion = descripcion;
    }
    
    // Constructor completo (con ID para actualizaciones)
    public Videojuego(int id, String titulo, String genero, String plataforma, 
                     String desarrollador, int anioLanzamiento, double precio, 
                     String clasificacion, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.plataforma = plataforma;
        this.desarrollador = desarrollador;
        this.anioLanzamiento = anioLanzamiento;
        this.precio = precio;
        this.clasificacion = clasificacion;
        this.descripcion = descripcion;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getGenero() {
        return genero;
    }
    
    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public String getPlataforma() {
        return plataforma;
    }
    
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }
    
    public String getDesarrollador() {
        return desarrollador;
    }
    
    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }
    
    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }
    
    public void setAnioLanzamiento(int anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public String getClasificacion() {
        return clasificacion;
    }
    
    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return "Videojuego{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", plataforma='" + plataforma + '\'' +
                ", desarrollador='" + desarrollador + '\'' +
                ", anioLanzamiento=" + anioLanzamiento +
                ", precio=" + precio +
                ", clasificacion='" + clasificacion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
