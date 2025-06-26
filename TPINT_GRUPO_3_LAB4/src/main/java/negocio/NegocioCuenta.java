package negocio;

import java.util.List;

import entidad.Cuenta;

public interface NegocioCuenta {
    public boolean insertar(Cuenta cuenta);
    public boolean modificar(Cuenta cuenta);
    public Cuenta obtenerPorId(int id);
    public List<Cuenta> listar();
    public boolean inactivarCuenta(int id);
    public boolean activarCuenta(int id);

    public boolean cantidadCuentas(int id);
	public void bajaCuentasUsuario(int id);
}