package dao;

import java.time.LocalDate;
import java.util.List;

import entidad.Cuota;


public interface CuotaDao {

	public boolean insertar(Cuota cuota);              
	public List<Cuota> listarCuotasPorPrestamo(int id) ;  
	public boolean pagarCuota(int id);      
	
	/* Prueba para reporte */
	public int contarCuotasPagadas(LocalDate desde, LocalDate hasta);
	public double totalRecaudadoEnCuotas(LocalDate desde, LocalDate hasta);
}
