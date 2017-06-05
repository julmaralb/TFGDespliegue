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

import domain.PlazaPropia;
import services.PlazaPropiaService;

@RestController
@RequestMapping(value = "/api/plazaPropia")
public class PlazaPropiaController {

	@Autowired
	private PlazaPropiaService plazaPropiaService;

	// Busca todas las plazas

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<PlazaPropia>> findAll() {

		Collection<PlazaPropia> res;

		res = plazaPropiaService.findAll();

		return new ResponseEntity<Collection<PlazaPropia>>(res, HttpStatus.OK);
	}

	// Busca la plaza del usuario logeado

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<PlazaPropia> findByPrincipal() {

		PlazaPropia res;

		res = plazaPropiaService.findByPrincipal();

		if (res == null) {
			return new ResponseEntity<PlazaPropia>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<PlazaPropia>(res, HttpStatus.OK);
	}

	// Busca la plaza cuyo usuario se pasa por id

	@RequestMapping(value = "/usuario/{usuarioId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<PlazaPropia> findByUserId(@PathVariable("usuarioId") String usuarioId) {

		PlazaPropia res;

		res = plazaPropiaService.findByUserId(usuarioId);

		if (res == null) {
			return new ResponseEntity<PlazaPropia>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<PlazaPropia>(res, HttpStatus.OK);
	}

	// Busca la plaza cuyo usuario se pasa por id

	@RequestMapping(value = "/{plazaId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<PlazaPropia> findByPlazaId(@PathVariable("plazaId") String plazaId) {

		PlazaPropia res;

		res = plazaPropiaService.findOne(plazaId);

		if (res == null) {
			return new ResponseEntity<PlazaPropia>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<PlazaPropia>(res, HttpStatus.OK);
	}

	// Modifica la plaza actual del usuario logeado

	@RequestMapping(value = "/modifica", method = RequestMethod.PUT)
	public ResponseEntity<PlazaPropia> modificaPlaza(@RequestBody PlazaPropia plazaPropia) {

		plazaPropiaService.modificaPlaza(plazaPropia);
		return new ResponseEntity<PlazaPropia>(plazaPropia, HttpStatus.OK);
	}
}