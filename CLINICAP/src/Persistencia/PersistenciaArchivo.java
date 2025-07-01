package persistencia;

import model.Clinica;
import model.IPersistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PersistenciaArchivo implements IPersistencia {

    @Override
    public void guardarClinica(Clinica clinica, String ruta) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(clinica);
        }
    }

    @Override
    public Clinica cargarClinica(String ruta) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            return (Clinica) ois.readObject();
        }
    }
}
