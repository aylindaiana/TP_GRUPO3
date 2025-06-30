package negocio;

import java.util.List;

import entidad.Cuota;

public interface NegocioCuota {
	public boolean insertar(Cuota cuota);              
	public List<Cuota> listarCuotasPorPrestamo(int id);
	public boolean pagarCuota(int id);          
}