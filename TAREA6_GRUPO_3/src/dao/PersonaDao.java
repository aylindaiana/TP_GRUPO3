package dao;

import java.util.List;

import entidad.Persona;

public interface PersonaDao {
    public boolean insertar(Persona persona);
    public boolean eliminar(String dni);
    public boolean existeDni(String dni);
    public List<Persona> obtenerTodas();
}