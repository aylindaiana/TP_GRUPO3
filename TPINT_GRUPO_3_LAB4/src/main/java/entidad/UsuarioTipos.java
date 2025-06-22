package entidad;

public class UsuarioTipos {
	private int Id;
	private String descripcion;
	
	public UsuarioTipos() {
		super();
	}

	public UsuarioTipos(int id, String descripcion) {
		super();
		Id = id;
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Usuario de tipo " + descripcion;
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
