package domain;

import java.util.Collection;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuario")
public class Usuario extends Actor {

	// Constructors -----------------------------------------------------------

	public Usuario() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private Collection<String> plazasDeseadasId;
	
	public Collection<String> getPlazasDeseadasId() {
		return plazasDeseadasId;
	}

	public void setPlazasDeseadasId(Collection<String> plazasDeseadasId) {
		this.plazasDeseadasId = plazasDeseadasId;
	}
}