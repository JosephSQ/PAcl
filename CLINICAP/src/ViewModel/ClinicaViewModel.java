package viewmodel;

import model.*;
import persistencia.PersistenciaArchivo;
import java.util.List;
import excepciones.*;


public class ClinicaViewModel {
    private Clinica clinica;
    private IPersistencia persistencia;
    private final String ARCHIVO_DATOS = "clinica.dat";
    public ClinicaViewModel() {
    persistencia = new PersistenciaArchivo();
    try {
        clinica = persistencia.cargarClinica(ARCHIVO_DATOS);
    } catch (Exception e) {
        clinica = new Clinica(); 
        System.out.println("Archivo no encontrado, se creara una nueva clínica.");
    }
}
    
public void registrarPaciente(Paciente p) throws CampoVacioException, Exception {
    if (p.getNombre().isEmpty()) throw new CampoVacioException("nombre");
    if (p.getDocumento().isEmpty()) throw new CampoVacioException("documento");
    clinica.agregarPaciente(p);
    persistencia.guardarClinica(clinica, "clinica.dat");
}

public void registrarMedico(Medico m) throws CampoVacioException, Exception {
    if (m.getEspecialidad().isEmpty()) throw new CampoVacioException("especialidad");
    clinica.agregarMedico(m);
    persistencia.guardarClinica(clinica, "clinica.dat");
}
    public Clinica getClinica() {
    return clinica;
}

    public Paciente getPacientePorDocumento(String documento) throws Exception {
        for (Paciente p : clinica.getPacientes()) {
            if (p.getDocumento().equals(documento)) {
                return p;
            }
        }
        throw new Exception("Paciente no encontrado");
    }
    
    public List<Consulta> obtenerConsultasPaciente(String documento) {
    return clinica.buscarConsultasPorPaciente(documento);
}

    public Medico getMedicoPorDocumento(String documento) throws Exception {
        for (Medico m : clinica.getMedicos()) {
            if (m.getDocumento().equals(documento)) {
                return m;
            }
        }
        throw new Exception("Médico no encontrado");
    }
    
public void registrarConsulta(Consulta c) {
    clinica.registrarConsulta(c);
    try {
        persistencia.guardarClinica(clinica, ARCHIVO_DATOS);
    } catch (Exception e) {
        System.err.println("Error al guardar datos" + e.getMessage());
    }
}

    public Persona loginPorDocumento(String documento) throws UsuarioNoEncontradoException {
    for (Paciente p : clinica.getPacientes()) {
        if (p.getDocumento().equals(documento)) return p;
    }
    for (Medico m : clinica.getMedicos()) {
        if (m.getDocumento().equals(documento)) return m;
    }
    throw new UsuarioNoEncontradoException("Documento no encontrado");
}

}
