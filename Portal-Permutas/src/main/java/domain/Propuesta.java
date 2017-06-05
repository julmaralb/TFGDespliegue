package domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "propuesta")
public class Propuesta {

	// Constructors -----------------------------------------------------------

	public Propuesta() {
		super();
	}

	// Attributes -------------------------------------------------------------

	@Id
	private String id;
	private String titulo;
	private String texto;
	private int estado;
	private String remitenteId;
	private String destinatarioId;
	private String plazaRemitenteId;
	private Date fecha;
	private Date fechaAcepRech;

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

	public String getRemitenteId() {
		return remitenteId;
	}

	public void setRemitenteId(String remitenteId) {
		this.remitenteId = remitenteId;
	}

	public String getDestinatarioId() {
		return destinatarioId;
	}

	public void setDestinatarioId(String destinatarioId) {
		this.destinatarioId = destinatarioId;
	}

	public String getPlazaRemitenteId() {
		return plazaRemitenteId;
	}

	public void setPlazaRemitenteId(String plazaRemitenteId) {
		this.plazaRemitenteId = plazaRemitenteId;
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

}