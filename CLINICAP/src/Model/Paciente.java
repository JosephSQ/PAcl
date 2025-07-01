package model;

import javax.swing.JTextField;

public class Paciente extends Persona {
    private String tipoSangre;

public Paciente(String nombre, String documento, String direccion, String genero) {
    super(nombre, documento, direccion, genero);
}

    public String getTipoSangre() { return tipoSangre; }
    public void setTipoSangre(String tipoSangre) { this.tipoSangre = tipoSangre; }

    @Override
    public String getInfoResumen() {
        return "Paciente: " + getNombre() + " | Sangre: " + tipoSangre + " | Género: " + getGenero();
    }
}
