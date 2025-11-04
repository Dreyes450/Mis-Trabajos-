/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudvideojuegos;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
/**
 *
 * @author Daniel
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                // Iniciar con la ventana de Login
                LoginGUI vistaLogin = new LoginGUI();
                UsuarioDAO modeloUsuario = new UsuarioDAO();
                LoginControlador controladorLogin = new LoginControlador(vistaLogin, modeloUsuario);
                
                controladorLogin.iniciar();
            }
        });
    }
}