/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudvideojuegos;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
/**
 *
 * @author Daniel
 */
public class VideojuegoGUI extends JFrame {
  // Componentes de la interfaz
    private JTextField txtId, txtTitulo, txtGenero, txtPlataforma, txtDesarrollador;
    private JTextField txtAnio, txtPrecio, txtClasificacion, txtBuscar;
    private JTextArea txtDescripcion;
    private JTable tablaVideojuegos;
    private DefaultTableModel modeloTabla;
    private JButton btnInsertar, btnActualizar, btnEliminar, btnLimpiar, btnBuscar, btnMostrarTodos;
    
    public VideojuegoGUI() {
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        // Configuración de la ventana
        setTitle("CRUD Videojuegos - Gestión Completa");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior con título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(41, 128, 185));
        JLabel lblTituloPrincipal = new JLabel("SISTEMA DE GESTIÓN DE VIDEOJUEGOS");
        lblTituloPrincipal.setFont(new Font("Arial", Font.BOLD, 24));
        lblTituloPrincipal.setForeground(Color.WHITE);
        panelTitulo.add(lblTituloPrincipal);
        add(panelTitulo, BorderLayout.NORTH);
        
        // Panel izquierdo - Formulario
        JPanel panelFormulario = crearPanelFormulario();
        add(panelFormulario, BorderLayout.WEST);
        
        // Panel central - Tabla
        JPanel panelTabla = crearPanelTabla();
        add(panelTabla, BorderLayout.CENTER);
        
        // Panel inferior - Botones de acción
        JPanel panelBotones = crearPanelBotones();
        add(panelBotones, BorderLayout.SOUTH);
    }
    
    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(52, 152, 219), 2),
            "Datos del Videojuego",
            0,
            0,
            new Font("Arial", Font.BOLD, 14),
            new Color(52, 152, 219)
        ));
        panel.setPreferredSize(new Dimension(400, 600));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // ID
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        txtId = new JTextField(15);
        txtId.setEnabled(false);
        panel.add(txtId, gbc);
        
        // Título
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Título:"), gbc);
        gbc.gridx = 1;
        txtTitulo = new JTextField(15);
        panel.add(txtTitulo, gbc);
        
        // Género
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Género:"), gbc);
        gbc.gridx = 1;
        txtGenero = new JTextField(15);
        panel.add(txtGenero, gbc);
        
        // Plataforma
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Plataforma:"), gbc);
        gbc.gridx = 1;
        txtPlataforma = new JTextField(15);
        panel.add(txtPlataforma, gbc);
        
        // Desarrollador
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Desarrollador:"), gbc);
        gbc.gridx = 1;
        txtDesarrollador = new JTextField(15);
        panel.add(txtDesarrollador, gbc);
        
        // Año
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Año:"), gbc);
        gbc.gridx = 1;
        txtAnio = new JTextField(15);
        panel.add(txtAnio, gbc);
        
        // Precio
        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("Precio:"), gbc);
        gbc.gridx = 1;
        txtPrecio = new JTextField(15);
        panel.add(txtPrecio, gbc);
        
        // Clasificación
        gbc.gridx = 0; gbc.gridy = 7;
        panel.add(new JLabel("Clasificación:"), gbc);
        gbc.gridx = 1;
        txtClasificacion = new JTextField(15);
        panel.add(txtClasificacion, gbc);
        
        // Descripción
        gbc.gridx = 0; gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.NORTH;
        panel.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        txtDescripcion = new JTextArea(5, 15);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        panel.add(scrollDescripcion, gbc);
        
        return panel;
    }
    
    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(52, 152, 219), 2),
            "Lista de Videojuegos",
            0,
            0,
            new Font("Arial", Font.BOLD, 14),
            new Color(52, 152, 219)
        ));
        
        // Panel de búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.add(new JLabel("Buscar por título:"));
        txtBuscar = new JTextField(20);
        panelBusqueda.add(txtBuscar);
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(52, 152, 219));
        btnBuscar.setForeground(Color.WHITE);
        panelBusqueda.add(btnBuscar);
        btnMostrarTodos = new JButton("Mostrar Todos");
        btnMostrarTodos.setBackground(new Color(46, 204, 113));
        btnMostrarTodos.setForeground(Color.WHITE);
        panelBusqueda.add(btnMostrarTodos);
        
        panel.add(panelBusqueda, BorderLayout.NORTH);
        
        // Tabla
        String[] columnas = {"ID", "Título", "Género", "Plataforma", "Desarrollador", 
                            "Año", "Precio", "Clasificación"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaVideojuegos = new JTable(modeloTabla);
        tablaVideojuegos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaVideojuegos.getTableHeader().setReorderingAllowed(false);
        tablaVideojuegos.setRowHeight(25);
        
        JScrollPane scrollTabla = new JScrollPane(tablaVideojuegos);
        panel.add(scrollTabla, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBackground(new Color(236, 240, 241));
        
        // Botón Insertar
        btnInsertar = new JButton("INSERTAR");
        btnInsertar.setPreferredSize(new Dimension(130, 40));
        btnInsertar.setBackground(new Color(46, 204, 113));
        btnInsertar.setForeground(Color.WHITE);
        btnInsertar.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(btnInsertar);
        
        // Botón Actualizar
        btnActualizar = new JButton("ACTUALIZAR");
        btnActualizar.setPreferredSize(new Dimension(130, 40));
        btnActualizar.setBackground(new Color(52, 152, 219));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(btnActualizar);
        
        // Botón Eliminar
        btnEliminar = new JButton("ELIMINAR");
        btnEliminar.setPreferredSize(new Dimension(130, 40));
        btnEliminar.setBackground(new Color(231, 76, 60));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(btnEliminar);
        
        // Botón Limpiar
        btnLimpiar = new JButton("LIMPIAR");
        btnLimpiar.setPreferredSize(new Dimension(130, 40));
        btnLimpiar.setBackground(new Color(149, 165, 166));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(btnLimpiar);
        
        return panel;
    }
    
    // Getters para los componentes
    public JTextField getTxtId() { return txtId; }
    public JTextField getTxtTitulo() { return txtTitulo; }
    public JTextField getTxtGenero() { return txtGenero; }
    public JTextField getTxtPlataforma() { return txtPlataforma; }
    public JTextField getTxtDesarrollador() { return txtDesarrollador; }
    public JTextField getTxtAnio() { return txtAnio; }
    public JTextField getTxtPrecio() { return txtPrecio; }
    public JTextField getTxtClasificacion() { return txtClasificacion; }
    public JTextArea getTxtDescripcion() { return txtDescripcion; }
    public JTextField getTxtBuscar() { return txtBuscar; }
    public JTable getTablaVideojuegos() { return tablaVideojuegos; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }
    public JButton getBtnInsertar() { return btnInsertar; }
    public JButton getBtnActualizar() { return btnActualizar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnMostrarTodos() { return btnMostrarTodos; }
}