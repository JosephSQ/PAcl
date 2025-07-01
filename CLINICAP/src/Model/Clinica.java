package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Clinica implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Paciente> pacientes;
    private List<Medico> medicos;
    private List<Consulta> consultas;

    public Clinica() {
        pacientes = new ArrayList<>();
        medicos = new ArrayList<>();
        consultas = new ArrayList<>();
    }

    // Métodos CRUD
    public void agregarPaciente(Paciente p) {
        pacientes.add(p);
    }

    public void agregarMedico(Medico m) {
        medicos.add(m);
    }

    public void registrarConsulta(Consulta c) {
        consultas.add(c);
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }
    
    public List<Consulta> buscarConsultasPorPaciente(String docPaciente) {
        return consultas.stream()
                .filter(c -> c.getPaciente().getDocumento().equals(docPaciente))
                .collect(Collectors.toList());
    }
    public List<Consulta> buscarConsultasPorMedico(String docMedico) {
        return consultas.stream()
                .filter(c -> c.getMedico().getDocumento().equals(docMedico))
                .collect(Collectors.toList());
    }
}