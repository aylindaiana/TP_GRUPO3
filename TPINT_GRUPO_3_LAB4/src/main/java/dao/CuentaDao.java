package dao;
import java.util.List;
import entidad.Cuenta;

public interface CuentaDao {
    public boolean insertar(Cuenta cuenta);            
    public boolean modificar(Cuenta cuenta);                    
    public Cuenta obtenerPorId(int id);                 
    public List<Cuenta> listar();          
    public int insertarYObtenerId(Cuenta cuenta);
}