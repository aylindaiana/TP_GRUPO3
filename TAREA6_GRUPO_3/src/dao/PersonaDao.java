package dao;

import entidad.Persona;

public interface PersonaDao {
    public boolean insertar(Persona persona);
    public boolean eliminar(String dni);
    public boolean existeDni(String dni);
}