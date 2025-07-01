package view;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Paciente;
import model.Medico;
import viewmodel.ClinicaViewModel;
import Excepciones.*;

public class PanelRegistro extends JPanel {
    private JTextField nombre;
    private JTextField documento;
    private JTextField direccion;
    private JTextField txtSangre;
    private JTextField txtEspecialidad;
    private JComboBox<String> comboGenero;
    private JComboBox<String> tipoCombo;

    public PanelRegistro(ClinicaViewModel vm, VentanaPrincipal ventana) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 20, 10, 20);
        int y = 0;

        // Tipo de persona
        gbc.gridx = 0; gbc.gridy = y;
        add(new JLabel("Tipo de Persona:"), gbc);
        gbc.gridx = 1;
        tipoCombo = new JComboBox<>(new String[]{"Paciente", "Medico"});
        add(tipoCombo, gbc);

        // Nombre
        y++;
        gbc.gridx = 0; gbc.gridy = y;
        add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        nombre = campoConPadding();
        add(nombre, gbc);

        // Documento
        y++;
        gbc.gridx = 0; gbc.gridy = y;
        add(new JLabel("Documento:"), gbc);
        gbc.gridx = 1;
        documento = campoConPadding();
        add(documento, gbc);

        // Dirección
        y++;
        gbc.gridx = 0; gbc.gridy = y;
        add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1;
        direccion = campoConPadding();
        add(direccion, gbc);

        // Género
        y++;
        gbc.gridx = 0; gbc.gridy = y;
        add(new JLabel("Género:"), gbc);
        gbc.gridx = 1;
        comboGenero = new JComboBox<>(new String[]{"Masculino", "Femenino", "Otro"});
        add(comboGenero, gbc);

        // Especialidad
        y++;
        gbc.gridx = 0; gbc.gridy = y;
        JLabel lblEspecialidad = new JLabel("Especialidad:");
        add(lblEspecialidad, gbc);
        gbc.gridx = 1;
        txtEspecialidad = campoConPadding();
        add(txtEspecialidad, gbc);

        // Tipo de sangre
        y++;
        gbc.gridx = 0; gbc.gridy = y;
        JLabel lblSangre = new JLabel("Tipo de Sangre:");
        add(lblSangre, gbc);
        gbc.gridx = 1;
        txtSangre = campoConPadding();
        add(txtSangre, gbc);

        // Mostrar u ocultar según selección
        lblEspecialidad.setVisible(false);
        txtEspecialidad.setVisible(false);

        tipoCombo.addActionListener(e -> {
            boolean esMedico = tipoCombo.getSelectedItem().equals("Medico");
            lblEspecialidad.setVisible(esMedico);
            txtEspecialidad.setVisible(esMedico);
            lblSangre.setVisible(!esMedico);
            txtSangre.setVisible(!esMedico);
        });

        // Botón registrar
        y++;
        gbc.gridx = 0; gbc.gridy = y;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton registrar = new JButton("Registrar");
        add(registrar, gbc);

        registrar.addActionListener(e -> {
            String nom = nombre.getText().trim();
            String doc = documento.getText().trim();
            String dir = direccion.getText().trim();
            String genero = (String) comboGenero.getSelectedItem();

            if (nom.isEmpty() || doc.isEmpty() || dir.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, llena todos los campos obligatorios.");
                return;
            }

            if (tipoCombo.getSelectedItem().equals("Paciente")) {
                String sangre = txtSangre.getText().trim();
                if (sangre.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El tipo de sangre es obligatorio para pacientes.");
                    return;
                }
                Paciente p = new Paciente(nom, doc, dir, genero);
                p.setTipoSangre(sangre);
                try {
                    vm.registrarPaciente(p);
                    JOptionPane.showMessageDialog(this, "Paciente registrado correctamente.");
                } catch (Exception ex) {
                    Logger.getLogger(PanelRegistro.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                String esp = txtEspecialidad.getText().trim();
                if (esp.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "La especialidad es obligatoria para médicos.");
                    return;
                }
                Medico m = new Medico(nom, doc, dir, genero);
                m.setEspecialidad(esp);
                try {
                    vm.registrarMedico(m);
                    JOptionPane.showMessageDialog(this, "Médico registrado correctamente.");
                } catch (Exception ex) {
                    Logger.getLogger(PanelRegistro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // Limpiar
            nombre.setText("");
            documento.setText("");
            direccion.setText("");
            txtEspecialidad.setText("");
            txtSangre.setText("");
        });

        // Botón volver
        y++;
        gbc.gridy = y;
        JButton volver = new JButton("Volver");
        volver.addActionListener(e -> ventana.mostrarPantallaPrincipal());
        add(volver, gbc);
    }

    private JTextField campoConPadding() {
        JTextField field = new JTextField(20);
        field.setBorder(BorderFactory.createCompoundBorder(
                field.getBorder(),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }
}
