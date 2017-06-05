package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import domain.Permuta;
import services.PermutaService;

@RestController
@RequestMapping(value = "/api/permuta")
public class PermutaController {

	@Autowired
	private PermutaService permutaService;

	// Devuelve todas las permutas del usuario logeado.

	@RequestMapping(value = "/principal", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Permuta>> findAllByPrincipal() {

		Collection<Permuta> res;

		res = permutaService.findAllByPrincipal();

		if (res == null) {
			return new ResponseEntity<Collection<Permuta>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Collection<Permuta>>(res, HttpStatus.OK);
	}

	// Devuelve todas las permutas.

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Permuta>> findAll() {

		Collection<Permuta> res;

		res = permutaService.findAll();

		if (res == null) {
			return new ResponseEntity<Collection<Permuta>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Collection<Permuta>>(res, HttpStatus.OK);
	}

}
