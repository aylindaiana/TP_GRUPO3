package entidad;

public class UsuarioCredenciales {
	private int id;
	private int IDCliente;
	private int IDTipo;
	private String usuario;
	private String password;
	private int Estado; //enum pronto o booleano?
	
	public UsuarioCredenciales() {
		super();
	}

	public UsuarioCredenciales(int id, int iDCliente, int iDTipo, String usuario, String password, int estado) {
		super();
		this.id = id;
		IDCliente = iDCliente;
		IDTipo = iDTipo;
		this.usuario = usuario;
		this.password = password;
		Estado = estado;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIDCliente() {
		return IDCliente;
	}
	public void setIDCliente(int iDCliente) {
		IDCliente = iDCliente;
	}
	public int getIDTipo() {
		return IDTipo;
	}
	public void setIDTipo(int iDTipo) {
		IDTipo = iDTipo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEstado() {
		return Estado;
	}
	public void setEstado(int estado) {
		Estado = estado;
	}
	
	
}
