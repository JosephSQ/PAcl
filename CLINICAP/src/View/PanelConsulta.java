package view;

import javax.swing.*;
import java.awt.*;
import model.Consulta;
import model.Medico;
import model.Paciente;
import viewmodel.ClinicaViewModel;
import java.time.LocalDate;

public class PanelConsulta extends JPanel {

    public PanelConsulta(ClinicaViewModel vm, VentanaPrincipal ventana) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 20, 10, 20); // márgenes externos uniformes

        int y = 0;

        // Estilo para campos
        JTextField docPaciente = campoConPadding();
        JTextField docMedico = campoConPadding();
        JTextField sintomas = campoConPadding();
        JTextField diagnostico = campoConPadding();
        JTextField tratamiento = campoConPadding();

        // Documento paciente
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel("Documento Paciente:"), gbc);
        gbc.gridx = 1;
        add(docPaciente, gbc);

        // Documento médico
        y++;
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel("Documento Médico:"), gbc);
        gbc.gridx = 1;
        add(docMedico, gbc);

        // Síntomas
        y++;
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel("Síntomas:"), gbc);
        gbc.gridx = 1;
        add(sintomas, gbc);

        // Diagnóstico
        y++;
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel("Diagnóstico:"), gbc);
        gbc.gridx = 1;
        add(diagnostico, gbc);

        // Tratamiento
        y++;
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel("Tratamiento:"), gbc);
        gbc.gridx = 1;
        add(tratamiento, gbc);

        // Botón registrar
        y++;
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton registrar = new JButton("Registrar Consulta");
        add(registrar, gbc);

        // Botón volver
        y++;
        gbc.gridy = y;
        JButton volver = new JButton("Volver");
        volver.addActionListener(e -> ventana.mostrarPantallaPrincipal());
        add(volver, gbc);

        // Acción del botón registrar
        registrar.addActionListener(e -> {
            try {
                Paciente p = vm.getPacientePorDocumento(docPaciente.getText().trim());
                Medico m = vm.getMedicoPorDocumento(docMedico.getText().trim());
                Consulta c = new Consulta(p, m, sintomas.getText().trim(), diagnostico.getText().trim(), tratamiento.getText().trim(), LocalDate.now());
                vm.registrarConsulta(c);
                JOptionPane.showMessageDialog(this, "Consulta registrada correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private JTextField campoConPadding() {
        JTextField field = new JTextField(20);
        field.setBorder(BorderFactory.createCompoundBorder(
            field.getBorder(),
            BorderFactory.createEmptyBorder(5, 10, 5, 10) // padding interno
        ));
        return field;
    }
}