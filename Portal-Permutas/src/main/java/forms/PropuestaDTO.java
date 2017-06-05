package forms;

import java.util.Date;

public class PropuestaDTO {

	private String id;
	private String titulo;
	private String texto;
	private int estado;
	private String nombreRemitente;
	private String nombreDestinatario;
	private Date fecha;
	private Date fechaAcepRech;
	private String idRemitente;
	private String idPlazaRemitente;
	private double latitudRemitente;
	private double longitudRemitente;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getNombreRemitente() {
		return nombreRemitente;
	}

	public void setNombreRemitente(String nombreRemitente) {
		this.nombreRemitente = nombreRemitente;
	}

	public String getNombreDestinatario() {
		return nombreDestinatario;
	}

	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaAcepRech() {
		return fechaAcepRech;
	}

	public void setFechaAcepRech(Date fechaAcepRech) {
		this.fechaAcepRech = fechaAcepRech;
	}

	public String getIdRemitente() {
		return idRemitente;
	}

	public void setIdRemitente(String idRemitente) {
		this.idRemitente = idRemitente;
	}

	public String getIdPlazaRemitente() {
		return idPlazaRemitente;
	}

	public void setIdPlazaRemitente(String idPlazaRemitente) {
		this.idPlazaRemitente = idPlazaRemitente;
	}

	public double getLatitudRemitente() {
		return latitudRemitente;
	}

	public void setLatitudRemitente(double latitudRemitente) {
		this.latitudRemitente = latitudRemitente;
	}

	public double getLongitudRemitente() {
		return longitudRemitente;
	}

	public void setLongitudRemitente(double longitudRemitente) {
		this.longitudRemitente = longitudRemitente;
	}

}
