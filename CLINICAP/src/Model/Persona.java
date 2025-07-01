package model;

import java.io.Serializable;

public abstract class Persona implements Serializable {
    private String nombre;
    private String documento;
    private String direccion;
    String genero;

    public Persona(String nombre, String documento, String direccion, String genero) {
        this.nombre = nombre;
        this.documento = documento;
        this.direccion = direccion;
        this.genero = genero;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public String getDocumento() { return documento; }
    public String getDireccion() { return direccion; }
    public String getGenero() { return genero; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDocumento(String documento) { this.documento = documento; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setGenero(String genero) { this.genero = genero; }
    
    public abstract String getInfoResumen();
}
