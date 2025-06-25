package dao;

import java.util.List;

import entidad.Cuenta;
import entidad.Prestamo;

public interface PrestamoDao {
	public boolean insertar(Prestamo prestamo);                   
    public List<Prestamo> obtenerPorIdCliente(int id);                 
    public List<Prestamo> listar();
}
