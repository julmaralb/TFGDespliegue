package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import domain.Propuesta;
import forms.PropuestaDTO;
import services.PlazaPropiaService;
import services.PropuestaService;
import services.UsuarioService;

@RestController
@RequestMapping(value = "/api/propuesta")
public class PropuestaController {

	@Autowired
	private PropuestaService propuestaService;

	@Autowired
	private PlazaPropiaService plazaPropiaService;
	
	@Autowired
	private UsuarioService usuarioService;

	// Busca todas las propuestas enviadas del usuario logeado.

	@RequestMapping(value = "/enviadas", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<PropuestaDTO>> findAllEnviadas() {

		Collection<PropuestaDTO> res;

		res = propuestaService.findAllPropuestasEnviadasDTO();

		if (res == null) {
			return new ResponseEntity<Collection<PropuestaDTO>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Collection<PropuestaDTO>>(res, HttpStatus.OK);
	}

	// Devuelve el número de propuestas enviadas por el usuario logeado.

	@RequestMapping(value = "/enviadasN", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Integer> findAllEnviadasN() {

		Integer res;

		res = propuestaService.findAllPropuestasEnviadasDTO().size();

		return new ResponseEntity<Integer>(res, HttpStatus.OK);
	}

	// Busca todas las propuestas recibidas del usuario logeado.

	@RequestMapping(value = "/recibidas", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<PropuestaDTO>> findAllRecibidas() {

		Collection<PropuestaDTO> res;

		res = propuestaService.findAllPropuestasRecibidasDTO();

		if (res == null) {
			return new ResponseEntity<Collection<PropuestaDTO>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Collection<PropuestaDTO>>(res, HttpStatus.OK);
	}

	// Devuelve el número de todas las propuestas recibidas por el usuario
	// logeado.

	@RequestMapping(value = "/recibidasN", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Integer> findAllRecibidasN() {

		Integer res;

		res = propuestaService.countRecibidas(propuestaService.findAllPropuestasRecibidasDTO());

		return new ResponseEntity<Integer>(res, HttpStatus.OK);
	}

	// Crea una nueva propuesta.

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Propuesta> crearPropuesta(@RequestBody Propuesta propuesta) {
		Propuesta res;
		
		if(propuesta.getDestinatarioId().equals(usuarioService.findPrincipal().getId())){
			return new ResponseEntity<Propuesta>(HttpStatus.FORBIDDEN);
		}

		res = propuestaService.creaPropuesta(propuesta);

		return new ResponseEntity<Propuesta>(res, HttpStatus.CREATED);
	}

	// Acepta una propuesta.

	@RequestMapping(value = "aceptar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Propuesta> aceptaPropuesta(@PathVariable("id") String id) {

		// Comprueba que el usuario logeado sea el destinatario.
		if (propuestaService.checkPrincipal(id) == false) {
			return new ResponseEntity<Propuesta>(HttpStatus.FORBIDDEN);
		}
		Propuesta res;

		res = propuestaService.aceptaPropuesta(id);

		propuestaService.rechazaResto(res);
		plazaPropiaService.intercambiaPlazasYCreaPermutas(res);

		return new ResponseEntity<Propuesta>(res, HttpStatus.OK);
	}

	// Rechaza una propuesta.

	@RequestMapping(value = "rechazar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Propuesta> rechazaPropuesta(@PathVariable("id") String id) {

		// Comprueba que el usuario logeado sea el destinatario.
		if (propuestaService.checkPrincipal(id) == false) {
			return new ResponseEntity<Propuesta>(HttpStatus.FORBIDDEN);
		}
		Propuesta res;

		res = propuestaService.rechazaPropuesta(id);

		return new ResponseEntity<Propuesta>(res, HttpStatus.OK);
	}
}