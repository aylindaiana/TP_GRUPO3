package negocioImpl;

import java.util.List;

import dao.PersonaDao;
import daoImpl.PersonaDaoImpl;
import entidad.Persona;
import negocio.PersonaNegocio;

public class PersonaNegocioImpl implements PersonaNegocio {
    private PersonaDao personaDao = new PersonaDaoImpl();

    @Override
    public boolean agregarPersona(Persona persona) {
        if (persona != null && !personaDao.existeDni(persona.getDni())) {
            return personaDao.insertar(persona);
        }
        return false;
    }

    @Override
    public boolean eliminarPersona(String dni) {
        if (dni != null && !dni.isEmpty() && personaDao.existeDni(dni)) {
            return personaDao.eliminar(dni);
        }
        return false;
    }


	@Override
	public boolean modificarPersona(Persona persona) {
		if(persona.getNombre() != null && persona.getApellido() != null) {
			return personaDao.modificar(persona);
		}
		return false;
	}
	
    @Override
    public List<Persona> obtenerTodas() {
        return personaDao.obtenerTodas();
    }
    
}