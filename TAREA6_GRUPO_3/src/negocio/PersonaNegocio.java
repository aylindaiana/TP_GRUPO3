package negocio;

import entidad.Persona;

public interface PersonaNegocio {
    public boolean agregarPersona(Persona persona);
    public boolean eliminarPersona(String dni);
}