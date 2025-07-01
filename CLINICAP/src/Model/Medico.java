package model;

import javax.swing.JTextField;

public class Medico extends Persona {
    private String especialidad;

    public Medico(String nombre, String documento, String direccion, String genero) {
        super(nombre, documento, direccion, genero);
        this.especialidad = especialidad;
    }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    @Override
    public String getInfoResumen() {
        return "Médico: " + getNombre() + " | Especialidad: " + especialidad + " | Género: " + getGenero();
    }
}
