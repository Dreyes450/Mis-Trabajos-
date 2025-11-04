/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudvideojuegos;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Daniel
 */
    public class LoginGUI extends JFrame {
    
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIngresar, btnRegistrar, btnSalir;
    private JLabel lblMensaje;
    
    public LoginGUI() {
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        // Configuración de la ventana
        setTitle("Login - Sistema de Videojuegos");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        
        // Panel superior con título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(41, 128, 185));
        panelTitulo.setPreferredSize(new Dimension(450, 80));
        
        JLabel lblTitulo = new JLabel("SISTEMA DE VIDEOJUEGOS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        
        JLabel lblSubtitulo = new JLabel("Inicio de Sesión");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSubtitulo.setForeground(Color.WHITE);
        
        panelTitulo.setLayout(new GridLayout(2, 1));
        panelTitulo.add(lblTitulo);
        panelTitulo.add(lblSubtitulo);
        
        add(panelTitulo, BorderLayout.NORTH);
        
        // Panel central con formulario
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridBagLayout());
        panelFormulario.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Usuario
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        panelFormulario.add(lblUsuario, gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        txtUsuario = new JTextField(20);
        txtUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        panelFormulario.add(txtUsuario, gbc);
        
        // Contraseña
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setFont(new Font("Arial", Font.BOLD, 14));
        panelFormulario.add(lblContrasena, gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        txtContrasena = new JPasswordField(20);
        txtContrasena.setFont(new Font("Arial", Font.PLAIN, 14));
        panelFormulario.add(txtContrasena, gbc);
        
        // Mensaje de error/éxito
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        lblMensaje = new JLabel("");
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 12));
        lblMensaje.setForeground(Color.RED);
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        panelFormulario.add(lblMensaje, gbc);
        
        add(panelFormulario, BorderLayout.CENTER);
        
        // Panel inferior con botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        panelBotones.setBackground(new Color(236, 240, 241));
        
        btnIngresar = new JButton("Ingresar");
        btnIngresar.setPreferredSize(new Dimension(120, 35));
        btnIngresar.setBackground(new Color(46, 204, 113));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFont(new Font("Arial", Font.BOLD, 12));
        btnIngresar.setFocusPainted(false);
        panelBotones.add(btnIngresar);
        
        btnRegistrar = new JButton("Registrarse");
        btnRegistrar.setPreferredSize(new Dimension(120, 35));
        btnRegistrar.setBackground(new Color(52, 152, 219));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 12));
        btnRegistrar.setFocusPainted(false);
        panelBotones.add(btnRegistrar);
        
        btnSalir = new JButton("Salir");
        btnSalir.setPreferredSize(new Dimension(120, 35));
        btnSalir.setBackground(new Color(231, 76, 60));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFont(new Font("Arial", Font.BOLD, 12));
        btnSalir.setFocusPainted(false);
        panelBotones.add(btnSalir);
        
        add(panelBotones, BorderLayout.SOUTH);
    }
    
    // Getters
    public JTextField getTxtUsuario() {
        return txtUsuario;
    }
    
    public JPasswordField getTxtContrasena() {
        return txtContrasena;
    }
    
    public JButton getBtnIngresar() {
        return btnIngresar;
    }
    
    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }
    
    public JButton getBtnSalir() {
        return btnSalir;
    }
    
    public JLabel getLblMensaje() {
        return lblMensaje;
    }
}

