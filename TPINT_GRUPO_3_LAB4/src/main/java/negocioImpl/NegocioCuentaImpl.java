package negocioImpl;

import java.sql.Date;
import java.util.List;

import dao.CuentaDao;
import daoImpl.CuentaDaoImpl;
import entidad.Cuenta;
import negocio.NegocioCuenta;

public class NegocioCuentaImpl implements NegocioCuenta{
    private CuentaDao cuentaDao;
    
    public NegocioCuentaImpl() {
        this.cuentaDao = new CuentaDaoImpl();
    }
    
    @Override
    public boolean insertar(Cuenta cuenta) {
        if (cuenta == null) {
            return false;
        }
        int cuentasActivas = cuentaDao.cuentasActivasPorCliente(cuenta.getIdCliente());
        
        if (cuentasActivas >= 3) {
            return false;
        }

        if (cuenta.getFechaDeCreacion() == null) {
            cuenta.setFechaDeCreacion(new Date(System.currentTimeMillis()));
        }
        
        cuenta.setSaldo(10000);
        
        cuenta.setEstado(true);
        
        return cuentaDao.insertar(cuenta);
    } 
    
    @Override
    public boolean modificar(Cuenta cuenta) {
        if (cuenta == null || cuenta.getId() <= 0) {
            return false;
        }
        
        Cuenta cuentaExistente = cuentaDao.obtenerPorId(cuenta.getId());
        if (cuentaExistente == null) {
            return false;
        }
        
      //  if (!cuenta.getCbu().equals(cuentaExistente.getCbu()) ) {
        //    return false;
       // }
        
        return cuentaDao.modificar(cuenta);
    }
    
    @Override
    public Cuenta obtenerPorId(int id) {
        if (id <= 0) {
            return null;
        }
        return cuentaDao.obtenerPorId(id);
    }
    
    @Override
    public List<Cuenta> listar() {
        return cuentaDao.listar();
    }
    
    @Override
    public List<Cuenta> listarCliente(int idCliente) {
        return cuentaDao.listarCuentas(idCliente);
    }

    
    @Override
    public boolean inactivarCuenta(int id)
    {
    	return cuentaDao.bajaLogica(id);
    }
    
    @Override
    public boolean activarCuenta(int id)
    {
    	return cuentaDao.activar(id);
    }
    
    @Override
    public boolean existeCBU(String cbu) {
        return cuentaDao.existeCBU(cbu);
    }
    
    @Override
    public boolean existeCBUExceptoId(String cbu, int idCuenta) {
        return cuentaDao.existeCBUExceptoId(cbu, idCuenta);
    }


	@Override
	public boolean cantidadCuentas(int id) {
		CuentaDao dao = new CuentaDaoImpl();
		
		return dao.cantidadCuentas(id);
	}

	@Override
	public void bajaCuentasUsuario(int id) {
		CuentaDao dao = new CuentaDaoImpl();
		
		dao.bajaCuentasUsuario(id);
	}
	
}
