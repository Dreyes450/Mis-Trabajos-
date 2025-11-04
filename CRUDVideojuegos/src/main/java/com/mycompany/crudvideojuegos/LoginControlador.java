/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudvideojuegos;
import javax.swing.*;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Daniel
 */
public class LoginControlador {
private LoginGUI vista;
    private UsuarioDAO modelo;
    
    public LoginControlador(LoginGUI vista, UsuarioDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        // Listener para el botón Ingresar
        this.vista.getBtnIngresar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });
        
        // Listener para Enter en el campo de contraseña
        this.vista.getTxtContrasena().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });
        
        // Listener para el botón Registrar
        this.vista.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarRegistro();
            }
        });
        
        // Listener para el botón Salir
        this.vista.getBtnSalir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    
    private void iniciarSesion() {
        String usuario = vista.getTxtUsuario().getText().trim();
        String contrasena = new String(vista.getTxtContrasena().getPassword());
        
        // Validar campos vacíos
        if (usuario.isEmpty() || contrasena.isEmpty()) {
            vista.getLblMensaje().setText("Por favor, completa todos los campos");
            vista.getLblMensaje().setForeground(Color.RED);
            return;
        }
        
        // Validar usuario en la base de datos
        Usuario usuarioValidado = modelo.validarUsuario(usuario, contrasena);
        
        if (usuarioValidado != null) {
            vista.getLblMensaje().setText("¡Bienvenido " + usuarioValidado.getNombreCompleto() + "!");
            vista.getLblMensaje().setForeground(new Color(46, 204, 113));
            
            // Esperar un momento antes de abrir la ventana principal
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    abrirVentanaPrincipal(usuarioValidado);
                }
            });
            timer.setRepeats(false);
            timer.start();
            
        } else {
            vista.getLblMensaje().setText("Usuario o contraseña incorrectos");
            vista.getLblMensaje().setForeground(Color.RED);
            vista.getTxtContrasena().setText("");
        }
    }
    
    private void abrirVentanaPrincipal(Usuario usuario) {
        // Cerrar ventana de login
        vista.dispose();
        
        // Abrir ventana principal del CRUD
        VideojuegoGUI vistaVideojuegos = new VideojuegoGUI();
        VideojuegoDAO modeloVideojuegos = new VideojuegoDAO();
        VideojuegoControlador controladorVideojuegos = 
            new VideojuegoControlador(vistaVideojuegos, modeloVideojuegos);
        
        // Mostrar mensaje de bienvenida
        vistaVideojuegos.setTitle("CRUD Videojuegos - Usuario: " + usuario.getNombreCompleto());
        controladorVideojuegos.iniciar();
    }
    
    private void mostrarRegistro() {
        // Crear diálogo de registro
        JDialog dialogoRegistro = new JDialog(vista, "Registrar Nuevo Usuario", true);
        dialogoRegistro.setSize(400, 350);
        dialogoRegistro.setLocationRelativeTo(vista);
        dialogoRegistro.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Campos del formulario
        JTextField txtNuevoUsuario = new JTextField(20);
        JPasswordField txtNuevaContrasena = new JPasswordField(20);
        JPasswordField txtConfirmarContrasena = new JPasswordField(20);
        JTextField txtNombreCompleto = new JTextField(20);
        
        gbc.gridx = 0; gbc.gridy = 0;
        dialogoRegistro.add(new JLabel("Usuario:"), gbc);
        gbc.gridx = 1;
        dialogoRegistro.add(txtNuevoUsuario, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        dialogoRegistro.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        dialogoRegistro.add(txtNuevaContrasena, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        dialogoRegistro.add(new JLabel("Confirmar:"), gbc);
        gbc.gridx = 1;
        dialogoRegistro.add(txtConfirmarContrasena, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        dialogoRegistro.add(new JLabel("Nombre Completo:"), gbc);
        gbc.gridx = 1;
        dialogoRegistro.add(txtNombreCompleto, gbc);
        
        // Botón registrar
        JButton btnConfirmarRegistro = new JButton("Registrar");
        btnConfirmarRegistro.setBackground(new Color(46, 204, 113));
        btnConfirmarRegistro.setForeground(Color.WHITE);
        
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        dialogoRegistro.add(btnConfirmarRegistro, gbc);
        
        btnConfirmarRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevoUsuario = txtNuevoUsuario.getText().trim();
                String nuevaContrasena = new String(txtNuevaContrasena.getPassword());
                String confirmarContrasena = new String(txtConfirmarContrasena.getPassword());
                String nombreCompleto = txtNombreCompleto.getText().trim();
                
                // Validaciones
                if (nuevoUsuario.isEmpty() || nuevaContrasena.isEmpty() || nombreCompleto.isEmpty()) {
                    JOptionPane.showMessageDialog(dialogoRegistro, 
                        "Todos los campos son obligatorios", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (!nuevaContrasena.equals(confirmarContrasena)) {
                    JOptionPane.showMessageDialog(dialogoRegistro, 
                        "Las contraseñas no coinciden", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (modelo.existeUsuario(nuevoUsuario)) {
                    JOptionPane.showMessageDialog(dialogoRegistro, 
                        "El usuario ya existe", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Registrar usuario
                Usuario usuario = new Usuario(nuevoUsuario, nuevaContrasena, nombreCompleto, "usuario");
                if (modelo.registrarUsuario(usuario)) {
                    JOptionPane.showMessageDialog(dialogoRegistro, 
                        "Usuario registrado exitosamente", 
                        "Éxito", 
                        JOptionPane.INFORMATION_MESSAGE);
                    dialogoRegistro.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialogoRegistro, 
                        "Error al registrar usuario", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        dialogoRegistro.setVisible(true);
    }
    
    public void iniciar() {
        vista.setVisible(true);
    }
}