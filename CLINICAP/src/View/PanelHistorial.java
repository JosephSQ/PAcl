package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.*;
import viewmodel.ClinicaViewModel;

public class PanelHistorial extends JPanel {

    private ClinicaViewModel vm;

    public PanelHistorial(ClinicaViewModel vm, VentanaPrincipal ventana) {
        this.vm = vm;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        // Campo de documento único
        gbc.gridx = 0; gbc.gridy = y;
        add(new JLabel("Documento de Paciente o Médico:"), gbc);

        gbc.gridx = 1;
        JTextField docCampo = new JTextField(15);
        docCampo.setBorder(BorderFactory.createCompoundBorder(
            docCampo.getBorder(),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        add(docCampo, gbc);

        // Botón buscar
        y++;
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton buscar = new JButton("Buscar Historial");
        add(buscar, gbc);

        // Área de resultados
        y++;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        JTextArea resultados = new JTextArea();
        resultados.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultados.setEditable(false);
        JScrollPane scroll = new JScrollPane(resultados);
        scroll.setPreferredSize(new Dimension(600, 400));
        add(scroll, gbc);

        // Botón volver
        y++;
        gbc.gridy = y;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        JButton volver = new JButton("Volver");
        volver.addActionListener(e -> ventana.mostrarPantallaPrincipal());
        add(volver, gbc);

        // Acción del botón buscar
        buscar.addActionListener(e -> {
            String doc = docCampo.getText().trim();
            if (doc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un documento.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                List<Consulta> consultas = vm.getClinica().getConsultas();

                // Primero intenta como paciente
                List<Consulta> porPaciente = consultas.stream()
                        .filter(c -> c.getPaciente().getDocumento().equals(doc))
                        .toList();

                if (!porPaciente.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    for (Consulta c : porPaciente) {
                        sb.append("Fecha: ").append(c.getFecha())
                          .append("\nPaciente: ").append(c.getPaciente().getNombre())
                          .append("\nTipo de sangre: ").append(c.getPaciente().getTipoSangre())
                          .append("\nSíntomas: ").append(c.getSintomas())
                          .append("\nDiagnóstico: ").append(c.getDiagnostico())
                          .append("\nTratamiento: ").append(c.getTratamiento())
                          .append("\nMédico: ").append(c.getMedico().getNombre())
                          .append("\n\n");
                    }
                    resultados.setText(sb.toString());
                    return;
                }

                // Si no, intenta como médico
                List<Consulta> porMedico = consultas.stream()
                        .filter(c -> c.getMedico().getDocumento().equals(doc))
                        .toList();

                if (!porMedico.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    for (Consulta c : porMedico) {
                        sb.append("Fecha: ").append(c.getFecha())
                          .append("\nPaciente: ").append(c.getPaciente().getNombre())
                          .append("\nSíntomas: ").append(c.getSintomas())
                          .append("\nDiagnóstico: ").append(c.getDiagnostico())
                          .append("\nTratamiento: ").append(c.getTratamiento())
                          .append("\n\n");
                    }
                    resultados.setText(sb.toString());
                    return;
                }

                // Si no encuentra nada
                resultados.setText("No se encontraron consultas para el documento ingresado.");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
