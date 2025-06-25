package negocio;

import java.util.List;

import entidad.Prestamo;

public interface NegocioPrestamo {

	public boolean solicitarPrestamo(Prestamo prestamo);                   
    public List<Prestamo> obtenerPorIdCliente(int id);                 
    public List<Prestamo> listarPrestamos(); 
}
