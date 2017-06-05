package domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "permuta")
public class Permuta {

	// Constructors -----------------------------------------------------------

	public Permuta() {
		super();
	}

	// Attributes -------------------------------------------------------------

	@Id
	private String id;
	private Date fecha;
	private String plazaPropiaId;
	private String plazaRecibidaId;
	private String usuarioId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getPlazaPropiaId() {
		return plazaPropiaId;
	}

	public void setPlazaPropiaId(String plazaPropiaId) {
		this.plazaPropiaId = plazaPropiaId;
	}

	public String getPlazaRecibidaId() {
		return plazaRecibidaId;
	}

	public void setPlazaRecibidaId(String plazaRecibidaId) {
		this.plazaRecibidaId = plazaRecibidaId;
	}

	public String getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

}