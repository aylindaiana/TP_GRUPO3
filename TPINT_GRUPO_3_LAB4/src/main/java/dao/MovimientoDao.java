package dao;

import java.util.List;

import entidad.Movimiento;


public interface MovimientoDao {

	public boolean insertar(Movimiento movimiento);              
    public List<Movimiento> listarMovimientosPorCliente(int id);                 
    public List<Movimiento> listarMovimientos();
    public int ultimoIDGenerado();
    public List<Movimiento> listarUltimosMovimientos(int idCliente, int limite);
}
