package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Coincidencia;
import domain.PlazaPropia;
import domain.ZonaDeseada;
import forms.GoogleMapCircle;
import repositories.ZonaDeseadaRepository;
import security.UserAccount;

@Service
@Transactional
public class ZonaDeseadaService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ZonaDeseadaRepository zonaDeseadaRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private PlazaPropiaService plazaPropiaService;

	@Autowired
	private UsuarioService usuarioService;

	// Constructors -----------------------------------------------------------

	public ZonaDeseadaService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public ZonaDeseada create() {
		ZonaDeseada result;

		result = new ZonaDeseada();

		return result;
	}

	public ZonaDeseada findOne(String zonaDeseadaId) {
		Assert.notNull(zonaDeseadaId);

		ZonaDeseada result;

		result = zonaDeseadaRepository.findOne(zonaDeseadaId);

		return result;

	}

	public Collection<ZonaDeseada> findAll() {

		Collection<ZonaDeseada> result;

		result = zonaDeseadaRepository.findAll();

		return result;
	}

	public void save(ZonaDeseada zonaDeseada) {
		Assert.notNull(zonaDeseada);

		zonaDeseadaRepository.save(zonaDeseada);
	}

	public void delete(ZonaDeseada zonaDeseada) {
		Assert.notNull(zonaDeseada);

		zonaDeseadaRepository.delete(zonaDeseada);

	}

	// Other business methods -------------------------------------------------

	public Collection<ZonaDeseada> findAllByUsuarioId(String usuarioId) {
		Collection<ZonaDeseada> res;

		res = zonaDeseadaRepository.findAllByUsuarioId(usuarioId);

		return res;
	}

	public Collection<ZonaDeseada> findAllByPrincipal() {
		Collection<ZonaDeseada> res;
		UserAccount principal;
		String id;

		principal = usuarioService.findPrincipal();
		id = principal.getId();
		res = findAllByUsuarioId(id);

		return res;
	}

	public Collection<ZonaDeseada> reconstruct(GoogleMapCircle[] circles) {
		Collection<ZonaDeseada> res;

		res = new ArrayList<ZonaDeseada>();

		for (int i = 0; i < circles.length; i++) {
			ZonaDeseada z = create();
			z.setLatitud(circles[i].getLatitude());
			z.setLongitud(circles[i].getLongitude());
			z.setRadio(circles[i].getRadius());
			z.setUsuarioId(usuarioService.findPrincipal().getId());

			save(z);
			res.add(z);
		}
		return res;
	}

	public static double distance(double lat1, double lat2, double lon1, double lon2) {

		final int R = 6371; // Radius of the earth

		Double latDistance = Math.toRadians(lat2 - lat1);
		Double lonDistance = Math.toRadians(lon2 - lon1);
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters

		double height = 0.0;

		distance = Math.pow(distance, 2) + Math.pow(height, 2);

		return Math.sqrt(distance);
	}

	public Collection<Coincidencia> compruebaCoincidencias() {
		Collection<Coincidencia> res;
		Collection<ZonaDeseada> principalZonas;
		Collection<PlazaPropia> allPlazas;
		Collection<PlazaPropia> candidatos;
		PlazaPropia plazaPropia;

		res = new ArrayList<Coincidencia>();
		principalZonas = findAllByPrincipal();
		allPlazas = plazaPropiaService.findAll();
		candidatos = new ArrayList<PlazaPropia>();
		plazaPropia = plazaPropiaService.findByPrincipal();

		for (Iterator<PlazaPropia> iterator = allPlazas.iterator(); iterator.hasNext();) {
			PlazaPropia currentPlaza = iterator.next();
			if (currentPlaza.getId().equals(plazaPropia.getId())) {
				iterator.remove();
			}
		}

		for (PlazaPropia p : allPlazas) {
			for (ZonaDeseada z : principalZonas) {
				if (distance(p.getLatitud(), z.getLatitud(), p.getLongitud(), z.getLongitud()) < z.getRadio()) {

					// Si la distancia entre las coordenadas de la plaza y las
					// coordenadas de la zona es menos que el radio, se
					// considera que la plaza esta dentro de la zona deseada y
					// se considera como candidato.

					candidatos.add(p);
				}
			}
		}
		for (PlazaPropia pc : candidatos) {

			// Por cada plaza candidata se obtienen todas las zonas deseadas del
			// dueño y se comprueba si la plaza del usuario logeado estan en su
			// rango. Si es asi es que ha habido una coincidencia y se crea.

			Collection<ZonaDeseada> zonasDelCandidato = zonaDeseadaRepository.findAllByUsuarioId(pc.getUsuarioId());
			for (ZonaDeseada zc : zonasDelCandidato) {
				if (distance(plazaPropia.getLatitud(), zc.getLatitud(), plazaPropia.getLongitud(),
						zc.getLongitud()) < zc.getRadio()) {
					if (compruebaNoContiene(res, pc)) {
						Coincidencia c = new Coincidencia();
						UserAccount userAccount = usuarioService.findOne(pc.getUsuarioId());

						c.setId(pc.getId());
						c.setIdUsuarioDestino(pc.getUsuarioId());
						c.setNombreUsuarioDestino(userAccount.getNombre() + " " + userAccount.getApellidos());
						c.setTitulo(pc.getTitulo());

						res.add(c);
					}
				}
			}
		}
		return res;
	}

	// Este método comprueba si ya hay una coincidencia relacionada con una
	// plaza para que no haya coincidencias duplicadas.

	public static boolean compruebaNoContiene(Collection<Coincidencia> c, PlazaPropia p) {
		boolean res = true;

		for (Coincidencia coincidencia : c) {
			if (coincidencia.getId().equals(p.getId())) {
				res = false;
			}
		}
		return res;
	}

	public boolean checkPrincipal(ZonaDeseada zonaDeseada) {
		boolean res;
		UserAccount principal;

		principal = usuarioService.findPrincipal();
		res = zonaDeseada.getUsuarioId().equals(principal.getId());

		return res;
	}

}