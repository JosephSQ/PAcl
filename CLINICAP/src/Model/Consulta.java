package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Consulta implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDate fecha;
    private String sintomas;
    private String diagnostico;
    private String tratamiento;

    private Paciente paciente;
    private Medico medico;

    public Consulta(Paciente paciente, Medico medico, String sintomas, String diagnostico, String tratamiento, LocalDate fecha) {
        this.fecha = fecha;
        this.paciente = paciente;
        this.medico = medico;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public String getSintomas() {
        return sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }
}
