/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudvideojuegos;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
/**
 *
 * @author Daniel
 */
public class VideojuegoControlador {
   private VideojuegoGUI vista;
    private VideojuegoDAO modelo;
    
    public VideojuegoControlador(VideojuegoGUI vista, VideojuegoDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        // Configurar los listeners
        this.vista.getBtnInsertar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarVideojuego();
            }
        });
        
        this.vista.getBtnActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarVideojuego();
            }
        });
        
        this.vista.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarVideojuego();
            }
        });
        
        this.vista.getBtnLimpiar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        
        this.vista.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarVideojuego();
            }
        });
        
        this.vista.getBtnMostrarTodos().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarTodosLosVideojuegos();
            }
        });
        
        // Listener para seleccionar fila de la tabla
        this.vista.getTablaVideojuegos().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    seleccionarVideojuego();
                }
            }
        });
        
        // Cargar datos iniciales
        cargarTodosLosVideojuegos();
    }
    
    // Método para insertar un nuevo videojuego
    private void insertarVideojuego() {
        try {
            // Validar campos obligatorios
            if (vista.getTxtTitulo().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(vista, 
                    "El título es obligatorio", 
                    "Error de validación", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Crear objeto Videojuego
            Videojuego videojuego = new Videojuego();
            videojuego.setTitulo(vista.getTxtTitulo().getText().trim());
            videojuego.setGenero(vista.getTxtGenero().getText().trim());
            videojuego.setPlataforma(vista.getTxtPlataforma().getText().trim());
            videojuego.setDesarrollador(vista.getTxtDesarrollador().getText().trim());
            
            // Validar año
            String anioStr = vista.getTxtAnio().getText().trim();
            if (!anioStr.isEmpty()) {
                videojuego.setAnioLanzamiento(Integer.parseInt(anioStr));
            }
            
            // Validar precio
            String precioStr = vista.getTxtPrecio().getText().trim();
            if (!precioStr.isEmpty()) {
                videojuego.setPrecio(Double.parseDouble(precioStr));
            }
            
            videojuego.setClasificacion(vista.getTxtClasificacion().getText().trim());
            videojuego.setDescripcion(vista.getTxtDescripcion().getText().trim());
            
            // Insertar en la base de datos
            if (modelo.insertarVideojuego(videojuego)) {
                JOptionPane.showMessageDialog(vista, 
                    "Videojuego insertado correctamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                cargarTodosLosVideojuegos();
            } else {
                JOptionPane.showMessageDialog(vista, 
                    "Error al insertar el videojuego", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, 
                "Error en el formato de número. Verifica el año y el precio.", 
                "Error de formato", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, 
                "Error inesperado: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    // Método para actualizar un videojuego
    private void actualizarVideojuego() {
        try {
            // Validar que se haya seleccionado un videojuego
            if (vista.getTxtId().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(vista, 
                    "Selecciona un videojuego de la tabla para actualizar", 
                    "Error", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Validar título
            if (vista.getTxtTitulo().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(vista, 
                    "El título es obligatorio", 
                    "Error de validación", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Crear objeto Videojuego
            Videojuego videojuego = new Videojuego();
            videojuego.setId(Integer.parseInt(vista.getTxtId().getText()));
            videojuego.setTitulo(vista.getTxtTitulo().getText().trim());
            videojuego.setGenero(vista.getTxtGenero().getText().trim());
            videojuego.setPlataforma(vista.getTxtPlataforma().getText().trim());
            videojuego.setDesarrollador(vista.getTxtDesarrollador().getText().trim());
            
            String anioStr = vista.getTxtAnio().getText().trim();
            if (!anioStr.isEmpty()) {
                videojuego.setAnioLanzamiento(Integer.parseInt(anioStr));
            }
            
            String precioStr = vista.getTxtPrecio().getText().trim();
            if (!precioStr.isEmpty()) {
                videojuego.setPrecio(Double.parseDouble(precioStr));
            }
            
            videojuego.setClasificacion(vista.getTxtClasificacion().getText().trim());
            videojuego.setDescripcion(vista.getTxtDescripcion().getText().trim());
            
            // Actualizar en la base de datos
            if (modelo.actualizarVideojuego(videojuego)) {
                JOptionPane.showMessageDialog(vista, 
                    "Videojuego actualizado correctamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                cargarTodosLosVideojuegos();
            } else {
                JOptionPane.showMessageDialog(vista, 
                    "Error al actualizar el videojuego", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, 
                "Error en el formato de número. Verifica el año y el precio.", 
                "Error de formato", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, 
                "Error inesperado: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    // Método para eliminar un videojuego
    private void eliminarVideojuego() {
        try {
            // Validar que se haya seleccionado un videojuego
            if (vista.getTxtId().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(vista, 
                    "Selecciona un videojuego de la tabla para eliminar", 
                    "Error", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int id = Integer.parseInt(vista.getTxtId().getText());
            String titulo = vista.getTxtTitulo().getText();
            
            // Confirmar eliminación
            int confirmacion = JOptionPane.showConfirmDialog(vista, 
                "¿Estás seguro de eliminar el videojuego '" + titulo + "'?", 
                "Confirmar eliminación", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                if (modelo.eliminarVideojuego(id)) {
                    JOptionPane.showMessageDialog(vista, 
                        "Videojuego eliminado correctamente", 
                        "Éxito", 
                        JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();
                    cargarTodosLosVideojuegos();
                } else {
                    JOptionPane.showMessageDialog(vista, 
                        "Error al eliminar el videojuego", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, 
                "Error al obtener el ID del videojuego", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, 
                "Error inesperado: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    // Método para cargar todos los videojuegos en la tabla
    private void cargarTodosLosVideojuegos() {
        // Limpiar tabla
        vista.getModeloTabla().setRowCount(0);
        
        // Obtener videojuegos
        List<Videojuego> lista = modelo.obtenerTodosLosVideojuegos();
        
        // Llenar tabla
        for (Videojuego v : lista) {
            Object[] fila = {
                v.getId(),
                v.getTitulo(),
                v.getGenero(),
                v.getPlataforma(),
                v.getDesarrollador(),
                v.getAnioLanzamiento(),
                String.format("$%.2f", v.getPrecio()),
                v.getClasificacion()
            };
            vista.getModeloTabla().addRow(fila);
        }
        
        // Limpiar campo de búsqueda
        vista.getTxtBuscar().setText("");
    }
    
    // Método para buscar videojuegos por título
    private void buscarVideojuego() {
        String textoBusqueda = vista.getTxtBuscar().getText().trim();
        
        if (textoBusqueda.isEmpty()) {
            JOptionPane.showMessageDialog(vista, 
                "Ingresa un título para buscar", 
                "Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Limpiar tabla
        vista.getModeloTabla().setRowCount(0);
        
        // Buscar videojuegos
        List<Videojuego> lista = modelo.buscarPorTitulo(textoBusqueda);
        
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(vista, 
                "No se encontraron videojuegos con ese título", 
                "Sin resultados", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Llenar tabla
            for (Videojuego v : lista) {
                Object[] fila = {
                    v.getId(),
                    v.getTitulo(),
                    v.getGenero(),
                    v.getPlataforma(),
                    v.getDesarrollador(),
                    v.getAnioLanzamiento(),
                    String.format("$%.2f", v.getPrecio()),
                    v.getClasificacion()
                };
                vista.getModeloTabla().addRow(fila);
            }
        }
    }
    
    // Método para seleccionar un videojuego de la tabla
    private void seleccionarVideojuego() {
        int filaSeleccionada = vista.getTablaVideojuegos().getSelectedRow();
        
        if (filaSeleccionada != -1) {
            int id = (int) vista.getModeloTabla().getValueAt(filaSeleccionada, 0);
            Videojuego videojuego = modelo.obtenerVideojuegoPorId(id);
            
            if (videojuego != null) {
                vista.getTxtId().setText(String.valueOf(videojuego.getId()));
                vista.getTxtTitulo().setText(videojuego.getTitulo());
                vista.getTxtGenero().setText(videojuego.getGenero());
                vista.getTxtPlataforma().setText(videojuego.getPlataforma());
                vista.getTxtDesarrollador().setText(videojuego.getDesarrollador());
                vista.getTxtAnio().setText(String.valueOf(videojuego.getAnioLanzamiento()));
                vista.getTxtPrecio().setText(String.valueOf(videojuego.getPrecio()));
                vista.getTxtClasificacion().setText(videojuego.getClasificacion());
                vista.getTxtDescripcion().setText(videojuego.getDescripcion());
            }
        }
    }
    
    // Método para limpiar todos los campos
    private void limpiarCampos() {
        vista.getTxtId().setText("");
        vista.getTxtTitulo().setText("");
        vista.getTxtGenero().setText("");
        vista.getTxtPlataforma().setText("");
        vista.getTxtDesarrollador().setText("");
        vista.getTxtAnio().setText("");
        vista.getTxtPrecio().setText("");
        vista.getTxtClasificacion().setText("");
        vista.getTxtDescripcion().setText("");
        vista.getTablaVideojuegos().clearSelection();
    }
    
    // Método para iniciar la vista
    public void iniciar() {
        vista.setVisible(true);
    }
}
