package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.PlazaPropia;
import domain.Propuesta;
import forms.PropuestaDTO;
import repositories.PropuestaRepository;
import security.UserAccount;

@Service
@Transactional
public class PropuestaService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private PropuestaRepository propuestaRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PlazaPropiaService plazaPropiaService;

	// Constructors -----------------------------------------------------------

	public PropuestaService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Propuesta create() {
		Propuesta result;

		result = new Propuesta();

		result.setEstado(0);
		result.setFecha(new Date());

		return result;
	}

	public Propuesta findOne(String propuestaId) {
		Assert.notNull(propuestaId);

		Propuesta result;

		result = propuestaRepository.findOne(propuestaId);

		return result;

	}

	public Collection<Propuesta> findAll() {

		Collection<Propuesta> result;

		result = propuestaRepository.findAll();

		return result;
	}

	public void save(Propuesta propuesta) {
		Assert.notNull(propuesta);

		long milliseconds;
		Date moment;

		milliseconds = System.currentTimeMillis() - 100;
		moment = new Date(milliseconds);

		propuesta.setFecha(moment);

		propuestaRepository.save(propuesta);
	}

	public void delete(Propuesta propuesta) {
		Assert.notNull(propuesta);

		propuestaRepository.delete(propuesta);

	}

	// Other business methods -------------------------------------------------

	public Collection<Propuesta> findAllByUserId(String id) {
		Collection<Propuesta> res;
		Collection<Propuesta> todas;

		todas = propuestaRepository.findAll();
		res = new ArrayList<Propuesta>();

		for (Propuesta p : todas) {
			if (p.getRemitenteId().equals(id) || p.getDestinatarioId().equals(id)) {
				res.add(p);
			}
		}
		return res;
	}

	public Propuesta creaPropuesta(Propuesta propuesta) {
		Propuesta res;
		PlazaPropia plazaPropia;

		res = create();
		plazaPropia = plazaPropiaService.findByUserId(usuarioService.findPrincipal().getId());

		res.setDestinatarioId(propuesta.getDestinatarioId());
		res.setRemitenteId(usuarioService.findPrincipal().getId());
		res.setPlazaRemitenteId(plazaPropia.getId());
		res.setTitulo(propuesta.getTitulo());
		res.setTexto(propuesta.getTexto());

		save(res);

		return res;
	}

	public Collection<Propuesta> findAllPropuestasEnviadas() {
		Collection<Propuesta> res;

		res = propuestaRepository.findAllByRemitenteId(usuarioService.findPrincipal().getId());

		return res;
	}

	public Collection<Propuesta> findAllPropuestasRecibidas() {
		Collection<Propuesta> res;

		res = propuestaRepository.findAllByDestinatarioId(usuarioService.findPrincipal().getId());

		return res;
	}

	public Collection<PropuestaDTO> findAllPropuestasEnviadasDTO() {
		Collection<PropuestaDTO> res;
		Collection<Propuesta> propuestas;

		res = new ArrayList<PropuestaDTO>();
		propuestas = findAllPropuestasEnviadas();

		for (Propuesta p : propuestas) {
			PropuestaDTO dto = new PropuestaDTO();
			UserAccount destinatario = usuarioService.findByUserId(p.getDestinatarioId());
			String nombreDestinatario = destinatario.getNombre() + " " + destinatario.getApellidos();

			dto.setEstado(p.getEstado());
			dto.setFecha(p.getFecha());
			dto.setFechaAcepRech(p.getFechaAcepRech());
			dto.setId(p.getId());
			dto.setNombreDestinatario(nombreDestinatario);
			dto.setTitulo(p.getTitulo());

			res.add(dto);
		}
		return res;
	}

	public Collection<PropuestaDTO> findAllPropuestasRecibidasDTO() {
		Collection<PropuestaDTO> res;
		Collection<Propuesta> propuestas;

		res = new ArrayList<PropuestaDTO>();
		propuestas = findAllPropuestasRecibidas();

		for (Propuesta p : propuestas) {
			PropuestaDTO dto = new PropuestaDTO();
			UserAccount remitente = usuarioService.findByUserId(p.getRemitenteId());
			String nombreRemitente = remitente.getNombre() + " " + remitente.getApellidos();
			PlazaPropia plaza = plazaPropiaService.findOne(p.getPlazaRemitenteId());

			dto.setEstado(p.getEstado());
			dto.setFecha(p.getFecha());
			dto.setFechaAcepRech(p.getFechaAcepRech());
			dto.setId(p.getId());
			dto.setNombreRemitente(nombreRemitente);
			dto.setTitulo(p.getTitulo());
			dto.setTexto(p.getTexto());
			dto.setLatitudRemitente(plaza.getLatitud());
			dto.setLongitudRemitente(plaza.getLongitud());
			dto.setIdRemitente(p.getRemitenteId());
			dto.setIdPlazaRemitente(plaza.getId());

			res.add(dto);
		}
		return res;
	}

	public Propuesta aceptaPropuesta(String id) {
		Propuesta propuesta;

		propuesta = findOne(id);

		propuesta.setEstado(1);

		propuesta.setFechaAcepRech(new Date());

		propuestaRepository.save(propuesta);

		return propuesta;

	}

	public Propuesta rechazaPropuesta(String id) {
		Propuesta propuesta;

		propuesta = findOne(id);

		propuesta.setEstado(2);

		propuesta.setFechaAcepRech(new Date());

		propuestaRepository.save(propuesta);

		return propuesta;
	}

	public void rechazaResto(Propuesta propuesta) {
		UserAccount destinatario;
		UserAccount remitente;
		Collection<Propuesta> todasDestinatario;
		Collection<Propuesta> todasRemitente;

		destinatario = usuarioService.findOne(propuesta.getDestinatarioId());
		remitente = usuarioService.findOne(propuesta.getRemitenteId());
		todasDestinatario = findAllByUserId(destinatario.getId());
		todasRemitente = findAllByUserId(remitente.getId());

		// Rechaza todas las propuestas excepto la que se pasa como
		// parámetro que es la que se supone se está aceptando.
		for (Propuesta pd : todasDestinatario) {
			if (!pd.getId().equals(propuesta.getId())) {
				rechazaPropuesta(pd.getId());
			}
		}

		for (Propuesta pr : todasRemitente) {
			if (!pr.getId().equals(propuesta.getId())) {
				rechazaPropuesta(pr.getId());
			}
		}

	}

	// Comprueba que el usuario logeado es el destinatario de la
	// propuesta cuya id que se pasa.

	public boolean checkPrincipal(String propuestaId) {
		boolean res;
		Propuesta propuesta;
		UserAccount principal;

		principal = usuarioService.findPrincipal();
		propuesta = findOne(propuestaId);
		res = propuesta.getDestinatarioId().equals(principal.getId());

		return res;
	}

	public int countRecibidas(Collection<PropuestaDTO> recibidas) {
		int res = 0;

		for (PropuestaDTO p : recibidas) {
			if (p.getEstado() == 0) {
				res++;
			}
		}
		return res;
	}

}