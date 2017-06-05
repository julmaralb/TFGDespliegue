package domain;

public class Coincidencia {

	// Constructors -----------------------------------------------------------

	public Coincidencia() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String id;
	private String idUsuarioDestino;
	private String titulo;
	private String nombreUsuarioDestino;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdUsuarioDestino() {
		return idUsuarioDestino;
	}

	public void setIdUsuarioDestino(String idUsuarioDestino) {
		this.idUsuarioDestino = idUsuarioDestino;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNombreUsuarioDestino() {
		return nombreUsuarioDestino;
	}

	public void setNombreUsuarioDestino(String nombreUsuarioDestino) {
		this.nombreUsuarioDestino = nombreUsuarioDestino;
	}
	
}