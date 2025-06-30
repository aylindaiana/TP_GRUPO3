package dao;

import java.util.List;

import entidad.Cuota;


public interface CuotaDao {

	public boolean insertar(Cuota cuota);              
	public List<Cuota> listarCuotasPorPrestamo(int id) ;  
	public boolean pagarCuota(int id);          
}
