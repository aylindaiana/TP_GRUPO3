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
        
        if (cuenta.getFechaDeCreacion() == null) {
            cuenta.setFechaDeCreacion(new Date(System.currentTimeMillis()));
        }
        
        if (cuenta.getSaldo() == 0) {
            cuenta.setSaldo(0.0);
        }
        
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
        
        if (!cuenta.getCbu().equals(cuentaExistente.getCbu()) ) {
            return false;
        }
        
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
	
}
