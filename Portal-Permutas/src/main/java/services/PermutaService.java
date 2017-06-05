package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Permuta;
import repositories.PermutaRepository;
import security.UserAccount;

@Service
@Transactional
public class PermutaService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private PermutaRepository permutaRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UsuarioService usuarioService;

	// Constructors -----------------------------------------------------------

	public PermutaService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Permuta create() {
		Permuta result;

		result = new Permuta();

		return result;
	}

	public Permuta findOne(String permutaId) {
		Assert.notNull(permutaId);

		Permuta result;

		result = permutaRepository.findOne(permutaId);

		return result;

	}

	public Collection<Permuta> findAll() {

		Collection<Permuta> result;

		result = permutaRepository.findAll();

		return result;
	}

	public void save(Permuta permuta) {
		Assert.notNull(permuta);

		long milliseconds;
		Date moment;

		milliseconds = System.currentTimeMillis() - 100;
		moment = new Date(milliseconds);

		permuta.setFecha(moment);

		permutaRepository.save(permuta);
	}

	public void delete(Permuta permuta) {
		Assert.notNull(permuta);

		permutaRepository.delete(permuta);

	}

	// Other business methods -------------------------------------------------

	public Collection<Permuta> findAllByUsuarioId(String usuarioId) {
		Collection<Permuta> res;

		res = permutaRepository.findAllByUsuarioId(usuarioId);

		return res;
	}

	public Collection<Permuta> findAllByPrincipal() {
		Collection<Permuta> res;
		UserAccount principal;

		principal = usuarioService.findPrincipal();
		res = findAllByUsuarioId(principal.getId());

		return res;
	}

}