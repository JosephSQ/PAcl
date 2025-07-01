
package view;

import excepciones.UsuarioNoEncontradoException;
import java.awt.Dimension;
import javax.swing.*;
import model.Persona;
import viewmodel.ClinicaViewModel;

public class LoginPanel extends JPanel {

    public LoginPanel(ClinicaViewModel vm, VentanaPrincipal ventana) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel lbl = new JLabel("Ingrese documento:");
        JTextField campo = new JTextField(15);
        JButton login = new JButton("Ingresar");

        lbl.setAlignmentX(CENTER_ALIGNMENT);
        campo.setMaximumSize(new Dimension(200, 30));
        login.setAlignmentX(CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(30));
        add(lbl);
        add(Box.createVerticalStrut(10));
        add(campo);
        add(Box.createVerticalStrut(10));
        add(login);

login.addActionListener(e -> {
    String doc = campo.getText().trim();

    try {
        Persona persona = vm.loginPorDocumento(doc);
        JOptionPane.showMessageDialog(this, "Bienvenido: " + persona.getNombre());
    } catch (UsuarioNoEncontradoException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage() + ". Continuando como invitado.");
    }

    ventana.mostrarPantallaPrincipal(); // Ir al menú principal en ambos casos
});
    }
}

