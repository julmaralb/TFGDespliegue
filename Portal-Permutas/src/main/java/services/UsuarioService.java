package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.PlazaPropia;
import forms.UsuarioForm;
import repositories.UsuarioRepository;
import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;

@Service
@Transactional
public class UsuarioService {

	// Managed repository-----------------------------------------------------

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	// Supporting services----------------------------------------------------

	@Autowired
	private LoginService loginService;

	@Autowired
	private PlazaPropiaService plazaPropiaService;

	// Constructors-----------------------------------------------------------

	public UsuarioService() {
		super();
	}

	// Simple CRUD methods----------------------------------------------------

	public UserAccount create() {

		UserAccount res;

		res = new UserAccount();

		res.setAccountNonExpired(true);
		res.setAccountNonLocked(true);
		res.setCredentialsNonExpired(true);
		res.setEnabled(true);
		res.addRole("ROLE_USER");

		return res;
	}

	public UserAccount findOne(String usuarioId) {
		Assert.notNull(usuarioId);

		UserAccount result;

		result = userAccountRepository.findOne(usuarioId);

		return result;

	}

	public Collection<UserAccount> findAll() {

		Collection<UserAccount> result;

		result = userAccountRepository.findAll();

		return result;
	}

	public void save(UserAccount userAccount) {
		Assert.notNull(userAccount);

		Assert.notNull(userAccount.getUsername());
		Assert.notNull(userAccount.getPassword());
		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();

		String password = userAccount.getPassword();
		password = encoder.encodePassword(password, null);
		userAccount.setPassword(password);

		userAccountRepository.save(userAccount);
	}

	public void delete(UserAccount usuario) {
		Assert.notNull(usuario);

		userAccountRepository.delete(usuario);

	}

	// Other business methods-------------------------------------------------
	public UserAccount findByUserId(String id) {
		UserAccount res;

		res = usuarioRepository.findById(id);

		return res;
	}

	public UserAccount findPrincipal() {
		UserAccount res;
		User user;

		user = loginService.getPrincipal2();

		res = userAccountRepository.findByUsername(user.getUsername());

		return res;
	}

	public UserAccount reconstruct(UsuarioForm usuarioForm) {
		UserAccount userAccount;
		PlazaPropia plazaPropia;

		userAccount = create();
		plazaPropia = new PlazaPropia();

		userAccount.setApellidos(usuarioForm.getApellidos());
		userAccount.setEmail(usuarioForm.getEmail());
		userAccount.setNombre(usuarioForm.getNombre());
		userAccount.setTelefono(usuarioForm.getTelefono());
		userAccount.setUsername(usuarioForm.getUsername());
		userAccount.setPassword(usuarioForm.getPassword());

		save(userAccount);

		plazaPropia.setCentro(usuarioForm.getCentro());
		plazaPropia.setCiudad(usuarioForm.getCiudad());
		plazaPropia.setDireccion(usuarioForm.getDireccion());
		plazaPropia.setTitulo(usuarioForm.getTitulo());
		plazaPropia.setUsuarioId(userAccount.getId());
		plazaPropia.setLatitud(usuarioForm.getLatitud());
		plazaPropia.setLongitud(usuarioForm.getLongitud());

		plazaPropiaService.save(plazaPropia);

		return userAccount;

	}

	public void modificaUserAccount(UserAccount usuario) {

		UserAccount userAccount = findPrincipal();

		userAccount.setApellidos(usuario.getApellidos());
		userAccount.setEmail(usuario.getEmail());
		userAccount.setNombre(usuario.getNombre());
		userAccount.setTelefono(usuario.getTelefono());

		userAccountRepository.save(userAccount);
	}

}