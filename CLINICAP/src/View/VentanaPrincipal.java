
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Persona;
import viewmodel.ClinicaViewModel;

public class VentanaPrincipal extends JFrame {

    private ClinicaViewModel viewModel;

    public VentanaPrincipal() {
        setTitle("Gestión de Clínica Médica");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        viewModel = new ClinicaViewModel();

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opciones");

        JMenuItem registroItem = new JMenuItem("Registrar Persona");
        JMenuItem consultaItem = new JMenuItem("Registrar Consulta");
        JMenuItem historialItem = new JMenuItem("Consultar Historial");

        registroItem.addActionListener(e -> {
            try {
                setContentPane(new PanelRegistro(viewModel, this));
            } catch (Exception ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            recargar();
        });

        consultaItem.addActionListener(e -> {
            setContentPane(new PanelConsulta(viewModel, this));
            recargar();
        });

        historialItem.addActionListener(e -> {
            setContentPane(new PanelHistorial(viewModel, this));
            recargar();
        });

        menu.add(registroItem);
        menu.add(consultaItem);
        menu.add(historialItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Mostrar panel de login primero
        setContentPane(new LoginPanel(viewModel, this));
        setVisible(true);
    }

    public void mostrarPantallaPrincipal() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel("Sistema de Gestión Médica", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        mainPanel.add(titulo, BorderLayout.NORTH);

        JPanel botonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 0, 15, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JButton botonOpciones = new JButton("Registrar Persona");
        botonOpciones.setFont(new Font("SansSerif", Font.BOLD, 22));
        botonOpciones.setPreferredSize(new Dimension(300, 60));
        botonOpciones.addActionListener(e -> {
            try {
                setContentPane(new PanelRegistro(viewModel, this));
            } catch (Exception ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            recargar();
        });
        botonPanel.add(botonOpciones, gbc);

        gbc.gridy++;
        JButton botonOpciones2 = new JButton("Registrar Consulta");
        botonOpciones2.setFont(new Font("SansSerif", Font.BOLD, 22));
        botonOpciones2.setPreferredSize(new Dimension(300, 60));
        botonOpciones2.addActionListener(e -> {
            setContentPane(new PanelConsulta(viewModel, this));
            recargar();
        });
        botonPanel.add(botonOpciones2, gbc);

        gbc.gridy++;
        JButton botonOpciones3 = new JButton("Consultar Historial");
        botonOpciones3.setFont(new Font("SansSerif", Font.BOLD, 22));
        botonOpciones3.setPreferredSize(new Dimension(300, 60));
        botonOpciones3.addActionListener(e -> {
            setContentPane(new PanelHistorial(viewModel, this));
            recargar();
        });
        botonPanel.add(botonOpciones3, gbc);

        mainPanel.add(botonPanel, BorderLayout.CENTER);
        setContentPane(mainPanel);
        recargar();
    }

    public void recargar() {
        revalidate();
        repaint();
    }
}
