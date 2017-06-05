package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Permuta;
import domain.PlazaPropia;
import domain.Propuesta;
import repositories.PlazaPropiaRepository;
import security.UserAccount;

@Service
@Transactional
public class PlazaPropiaService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private PlazaPropiaRepository plazaPropiaRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PermutaService permutaService;

	// Constructors -----------------------------------------------------------

	public PlazaPropiaService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public PlazaPropia create() {
		PlazaPropia result;

		result = new PlazaPropia();

		return result;
	}

	public PlazaPropia findOne(String plazaPropiaId) {
		Assert.notNull(plazaPropiaId);

		PlazaPropia result;

		result = plazaPropiaRepository.findOne(plazaPropiaId);

		return result;

	}

	public Collection<PlazaPropia> findAll() {

		Collection<PlazaPropia> result;

		result = plazaPropiaRepository.findAll();

		return result;
	}

	public void save(PlazaPropia plazaPropia) {
		Assert.notNull(plazaPropia);

		plazaPropiaRepository.save(plazaPropia);
	}

	public void delete(PlazaPropia plazaPropia) {
		Assert.notNull(plazaPropia);

		plazaPropiaRepository.delete(plazaPropia);

	}

	// Other business methods -------------------------------------------------

	public PlazaPropia findByCiudad(String ciudad) {
		PlazaPropia result;

		result = plazaPropiaRepository.findByCiudad(ciudad);

		return result;
	}

	public PlazaPropia findByUserId(String id) {
		PlazaPropia res;

		res = plazaPropiaRepository.findByUsuarioId(id);

		return res;
	}

	public PlazaPropia findByPrincipal() {
		PlazaPropia res;

		res = findByUserId(usuarioService.findPrincipal().getId());

		return res;
	}

	public void modificaPlaza(PlazaPropia plazaPropia) {

		PlazaPropia plazaActual;

		plazaActual = findByPrincipal();

		plazaActual.setCentro(plazaPropia.getCentro());
		plazaActual.setCiudad(plazaPropia.getCiudad());
		plazaActual.setDireccion(plazaPropia.getDireccion());
		plazaActual.setTitulo(plazaPropia.getTitulo());
		plazaActual.setLatitud(plazaPropia.getLatitud());
		plazaActual.setLongitud(plazaPropia.getLongitud());

		save(plazaActual);
	}
	
	public void intercambiaPlazasYCreaPermutas(Propuesta propuesta){
		UserAccount remitente;
		UserAccount destinatario;
		PlazaPropia plazaRemitente;
		PlazaPropia plazaDestinatario;
		Permuta permuta1;
		Permuta permuta2;
		
		remitente = usuarioService.findByUserId(propuesta.getRemitenteId());
		destinatario = usuarioService.findByUserId(propuesta.getDestinatarioId());
		plazaRemitente = findByUserId(remitente.getId());
		plazaDestinatario = findByUserId(destinatario.getId());
		permuta1 = permutaService.create();
		permuta2 = permutaService.create();
		
		// Primero se crean las permutas para los dos usuarios implicados.
		permuta1.setPlazaPropiaId(plazaDestinatario.getId());
		permuta1.setPlazaRecibidaId(plazaRemitente.getId());
		permuta1.setUsuarioId(destinatario.getId());
		permutaService.save(permuta1);
		
		permuta2.setPlazaPropiaId(plazaRemitente.getId());
		permuta2.setPlazaRecibidaId(plazaDestinatario.getId());
		permuta2.setUsuarioId(remitente.getId());
		permutaService.save(permuta2);
		
		// Por último se intecambian las plazas.
		plazaRemitente.setUsuarioId(destinatario.getId());
		plazaDestinatario.setUsuarioId(remitente.getId());
		
		save(plazaDestinatario);
		save(plazaRemitente);
		
	}

}