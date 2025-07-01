package model;

public interface IPersistencia {
    void guardarClinica(Clinica clinica, String ruta) throws Exception;
    Clinica cargarClinica(String ruta) throws Exception;
}
