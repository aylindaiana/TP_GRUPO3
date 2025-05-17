package negocioImpl;

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
}