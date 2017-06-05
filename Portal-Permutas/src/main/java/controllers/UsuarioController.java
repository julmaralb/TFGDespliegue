package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import forms.UsuarioForm;
import security.UserAccount;
import services.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	// Devuelve todos los usuarios registrados.

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<UserAccount>> findAll() {

		Collection<UserAccount> userAccount = usuarioService.findAll();

		return new ResponseEntity<Collection<UserAccount>>(userAccount, HttpStatus.OK);

	}

	// Registra un nuevo usuario

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserAccount> register(@RequestBody UsuarioForm user) {

		UserAccount res;

		res = usuarioService.reconstruct(user);

		return new ResponseEntity<UserAccount>(res, HttpStatus.CREATED);
	}

	// Busca el usuario logeado

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<UserAccount> findPrincipal() {

		UserAccount res;

		res = usuarioService.findPrincipal();

		if (res == null) {
			return new ResponseEntity<UserAccount>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UserAccount>(res, HttpStatus.OK);
	}

	// Modifica el perfil del usuario logeado

	@RequestMapping(value = "/modifica", method = RequestMethod.PUT)
	public ResponseEntity<UserAccount> modificaUserAccount(@RequestBody UserAccount usuario) {

		if (usuario == null) {
			return new ResponseEntity<UserAccount>(HttpStatus.NOT_FOUND);
		}

		usuarioService.modificaUserAccount(usuario);
		return new ResponseEntity<UserAccount>(usuario, HttpStatus.OK);
	}

}