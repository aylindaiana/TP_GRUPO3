package negocio;

import java.util.List;

import entidad.Persona;

public interface PersonaNegocio {
    public boolean agregarPersona(Persona persona);
    public boolean eliminarPersona(String dni);
    public List<Persona> obtenerTodas();
}