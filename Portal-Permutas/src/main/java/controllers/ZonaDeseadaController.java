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

import domain.Coincidencia;
import domain.ZonaDeseada;
import forms.GoogleMapCircle;
import services.ZonaDeseadaService;

@RestController
@RequestMapping("/api/zonaDeseada")
public class ZonaDeseadaController {

	@Autowired
	private ZonaDeseadaService zonaDeseadaService;

	// Devuelve todas las zonas deseadas del usuario logeado.

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<ZonaDeseada>> findAll() {

		Collection<ZonaDeseada> res;

		res = zonaDeseadaService.findAllByPrincipal();

		if (res == null) {
			return new ResponseEntity<Collection<ZonaDeseada>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Collection<ZonaDeseada>>(res, HttpStatus.OK);
	}

	// Devuelve las coincidencias que se encuentren.

	@RequestMapping(value = "/matchings", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Coincidencia>> compruebaCoincidencias() {

		Collection<Coincidencia> res;

		res = zonaDeseadaService.compruebaCoincidencias();

		if (res == null) {
			return new ResponseEntity<Collection<Coincidencia>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Collection<Coincidencia>>(res, HttpStatus.OK);
	}

	// Crea una nueva zona deseada.

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Collection<ZonaDeseada>> create(@RequestBody GoogleMapCircle[] circles) {

		Collection<ZonaDeseada> res;

		res = zonaDeseadaService.reconstruct(circles);

		return new ResponseEntity<Collection<ZonaDeseada>>(res, HttpStatus.CREATED);

	}

	// Elimina la zona deseada que cuya id se pasa por parámetro.

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ZonaDeseada> delete(@PathVariable("id") String id) {

		ZonaDeseada zonaDeseada;

		zonaDeseada = zonaDeseadaService.findOne(id);

		// Comprueba que la zona que se quiere eliminar pertenece al usuario
		// logeado.
		if (zonaDeseadaService.checkPrincipal(zonaDeseada) == false) {
			return new ResponseEntity<ZonaDeseada>(HttpStatus.FORBIDDEN);
		}

		if (zonaDeseada == null) {
			System.out.println("Unable to delete. ZonaDeseada with id " + id + " not found");
			return new ResponseEntity<ZonaDeseada>(HttpStatus.NOT_FOUND);
		}

		zonaDeseadaService.delete(zonaDeseada);

		return new ResponseEntity<ZonaDeseada>(HttpStatus.NO_CONTENT);
	}
}