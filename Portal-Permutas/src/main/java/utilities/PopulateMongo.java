package utilities;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import domain.Permuta;
import domain.PlazaPropia;
import domain.Propuesta;
import domain.ZonaDeseada;
import security.UserAccount;

//import domain.PlazaPropia;

//import org.springframework.context.support.GenericXmlApplicationContext;

public class PopulateMongo {

	public static void main(String[] args) {

		ApplicationContext ctx = new GenericXmlApplicationContext("spring/config/SpringMongoConfig.xml");
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

		// -------------------- Borrar todo --------------------------
		
		mongoOperation.dropCollection("userAccount");
		mongoOperation.dropCollection("plazaPropia");
		mongoOperation.dropCollection("zonaDeseada");
		mongoOperation.dropCollection("propuesta");
		mongoOperation.dropCollection("permuta");

		// --------------------USER ACCOUNTS--------------------------

		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();

		UserAccount userAccount1 = new UserAccount();

		userAccount1.setUsername("usuario1");
		userAccount1.setPassword(encoder.encodePassword("usuario1", null));
		userAccount1.setNombre("Julio");
		userAccount1.setApellidos("Martin");
		userAccount1.setEmail("juliom@hotmail.com");
		userAccount1.setTelefono("621165843");
		userAccount1.setEnabled(true);
		userAccount1.setAccountNonExpired(true);
		userAccount1.setAccountNonLocked(true);
		userAccount1.setCredentialsNonExpired(true);
		userAccount1.addRole("ROLE_USER");

		mongoOperation.save(userAccount1);

		UserAccount userAccount2 = new UserAccount();

		userAccount2.setUsername("usuario2");
		userAccount2.setPassword(encoder.encodePassword("usuario2", null));
		userAccount2.setNombre("Pedro");
		userAccount2.setApellidos("Moreno");
		userAccount2.setEmail("pedrom@hotmail.com");
		userAccount2.setTelefono("623362029");
		userAccount2.setEnabled(true);
		userAccount2.setAccountNonExpired(true);
		userAccount2.setAccountNonLocked(true);
		userAccount2.setCredentialsNonExpired(true);
		userAccount2.addRole("ROLE_USER");

		mongoOperation.save(userAccount2);

		UserAccount userAccount3 = new UserAccount();

		userAccount3.setUsername("usuario3");
		userAccount3.setPassword(encoder.encodePassword("usuario3", null));
		userAccount3.setNombre("Carlos");
		userAccount3.setApellidos("Moya");
		userAccount3.setEmail("carlosm@hotmail.com");
		userAccount3.setTelefono("613354963");
		userAccount3.setEnabled(true);
		userAccount3.setAccountNonExpired(true);
		userAccount3.setAccountNonLocked(true);
		userAccount3.setCredentialsNonExpired(true);
		userAccount3.addRole("ROLE_USER");

		mongoOperation.save(userAccount3);
		
		UserAccount userAccount4 = new UserAccount();

		userAccount4.setUsername("usuario4");
		userAccount4.setPassword(encoder.encodePassword("usuario4", null));
		userAccount4.setNombre("Alvaro");
		userAccount4.setApellidos("Navarro");
		userAccount4.setEmail("alvaron@hotmail.com");
		userAccount4.setTelefono("634565752");
		userAccount4.setEnabled(true);
		userAccount4.setAccountNonExpired(true);
		userAccount4.setAccountNonLocked(true);
		userAccount4.setCredentialsNonExpired(true);
		userAccount4.addRole("ROLE_USER");

		mongoOperation.save(userAccount4);

		// --------------------Plazas Propias--------------------------

		PlazaPropia plazaPropia1 = new PlazaPropia();

		plazaPropia1.setCentro("Colegio Buen Pastor");
		plazaPropia1.setCiudad("Sevilla");
		plazaPropia1.setDireccion("Calle Martinez de Medina, 2, Sevilla");
		plazaPropia1.setTitulo("Plaza de 1 año como profesor de lengua");
		plazaPropia1.setLatitud(37.3849985);
		plazaPropia1.setLongitud(-5.973834000000011);
		plazaPropia1.setUsuarioId(userAccount1.getId());

		mongoOperation.save(plazaPropia1);

		PlazaPropia plazaPropia2 = new PlazaPropia();

		plazaPropia2.setCentro("Colegio de San Francisco de Paula");
		plazaPropia2.setCiudad("Sevilla");
		plazaPropia2.setDireccion("Calle Sta. Angela de la Cruz, 11, Sevilla");
		plazaPropia2.setTitulo("Plaza de 1 año como tutor");
		plazaPropia2.setLatitud(37.3933543);
		plazaPropia2.setLongitud(-5.990608699999939);
		plazaPropia2.setUsuarioId(userAccount2.getId());

		mongoOperation.save(plazaPropia2);

		PlazaPropia plazaPropia3 = new PlazaPropia();

		plazaPropia3.setCentro("Colegio Compañia de Maria");
		plazaPropia3.setCiudad("Sevilla");
		plazaPropia3.setDireccion("Calle Colombia, 2, Sevilla");
		plazaPropia3.setTitulo("Plaza de 6 meses como profesor de ingles");
		plazaPropia3.setLatitud(37.36958509999999);
		plazaPropia3.setLongitud(-5.984676799999988);
		plazaPropia3.setUsuarioId(userAccount3.getId());

		mongoOperation.save(plazaPropia3);
		
		PlazaPropia plazaPropia4 = new PlazaPropia();

		plazaPropia4.setCentro("Colegio San Antonio María Claret");
		plazaPropia4.setCiudad("Sevilla");
		plazaPropia4.setDireccion("Av. Padre García Tejero, 8, 41012 Sevilla");
		plazaPropia4.setTitulo("Plaza de 1 año como profesor de historia");
		plazaPropia4.setLatitud(37.3567267);
		plazaPropia4.setLongitud(-5.9841592999999875);
		plazaPropia4.setUsuarioId(userAccount4.getId());

		mongoOperation.save(plazaPropia4);
		
		
		// -------------------- Zonas Deseadas --------------------------
		
		ZonaDeseada zonaDeseada1 = new ZonaDeseada();
		
		zonaDeseada1.setLatitud(37.36958509999999);
		zonaDeseada1.setLongitud(-5.984676799999988);
		zonaDeseada1.setRadio(300.0);
		zonaDeseada1.setUsuarioId(userAccount1.getId());
		
		mongoOperation.save(zonaDeseada1);
		
		
		ZonaDeseada zonaDeseada2 = new ZonaDeseada();
		
		zonaDeseada2.setLatitud(37.37909246284138);
		zonaDeseada2.setLongitud(-5.962958335876465);
		zonaDeseada2.setRadio(300.0);
		zonaDeseada2.setUsuarioId(userAccount1.getId());
		
		mongoOperation.save(zonaDeseada2);
		
		ZonaDeseada zonaDeseada3 = new ZonaDeseada();
		
		zonaDeseada3.setLatitud(37.3933543);
		zonaDeseada3.setLongitud(-5.990608699999939);
		zonaDeseada3.setRadio(300.0);
		zonaDeseada3.setUsuarioId(userAccount1.getId());
		
		mongoOperation.save(zonaDeseada3);
		
		ZonaDeseada zonaDeseada4 = new ZonaDeseada();
		
		zonaDeseada4.setLatitud(37.3849985);
		zonaDeseada4.setLongitud(-5.973834000000011);
		zonaDeseada4.setRadio(300.0);
		zonaDeseada4.setUsuarioId(userAccount2.getId());
		
		mongoOperation.save(zonaDeseada4);
		
		
		ZonaDeseada zonaDeseada5 = new ZonaDeseada();
		
		zonaDeseada5.setLatitud(37.37588679823346);
		zonaDeseada5.setLongitud(-5.959610939025879);
		zonaDeseada5.setRadio(300.0);
		zonaDeseada5.setUsuarioId(userAccount2.getId());
		
		mongoOperation.save(zonaDeseada5);
		
		
		ZonaDeseada zonaDeseada6 = new ZonaDeseada();
		
		zonaDeseada6.setLatitud(37.3849985);
		zonaDeseada6.setLongitud(-5.973834000000011);
		zonaDeseada6.setRadio(300.0);
		zonaDeseada6.setUsuarioId(userAccount3.getId());
		
		mongoOperation.save(zonaDeseada6);
		
		
		// -------------------- Propuestas --------------------------
		
		
		Propuesta propuesta1 = new Propuesta();

		propuesta1.setDestinatarioId(userAccount2.getId());
		propuesta1.setEstado(0);
		propuesta1.setFecha(new Date());
		propuesta1.setRemitenteId(userAccount1.getId());
		propuesta1.setPlazaRemitenteId(plazaPropia1.getId());
		propuesta1.setTitulo("tituloPropuesta1");
		propuesta1.setTexto("Este es el texto de la propuesta 1");

		mongoOperation.save(propuesta1);
		
		Propuesta propuesta2 = new Propuesta();

		propuesta2.setDestinatarioId(userAccount1.getId());
		propuesta2.setEstado(0);
		propuesta2.setFecha(new Date());
		propuesta2.setRemitenteId(userAccount3.getId());
		propuesta2.setPlazaRemitenteId(plazaPropia3.getId());
		propuesta2.setTitulo("tituloPropuesta2");
		propuesta2.setTexto("Este es el texto de la propuesta 2");

		mongoOperation.save(propuesta2);
		
		Propuesta propuesta3 = new Propuesta();

		propuesta3.setDestinatarioId(userAccount2.getId());
		propuesta3.setEstado(0);
		propuesta3.setFecha(new Date());
		propuesta3.setRemitenteId(userAccount4.getId());
		propuesta3.setPlazaRemitenteId(plazaPropia4.getId());
		propuesta3.setTitulo("tituloPropuesta3");
		propuesta3.setTexto("Este es el texto de la propuesta 3");

		mongoOperation.save(propuesta3);
		
		
		// -------------------- Permutas --------------------------
		
		
		Permuta permuta1 = new Permuta();

		permuta1.setFecha(new Date());
		permuta1.setPlazaPropiaId(plazaPropia4.getId());
		permuta1.setPlazaRecibidaId(plazaPropia1.getId());
		permuta1.setUsuarioId(userAccount1.getId());;

		mongoOperation.save(permuta1);
		
		Permuta permuta2 = new Permuta();

		permuta2.setFecha(new Date());
		permuta2.setPlazaPropiaId(plazaPropia1.getId());
		permuta2.setPlazaRecibidaId(plazaPropia4.getId());
		permuta2.setUsuarioId(userAccount4.getId());;

		mongoOperation.save(permuta2);
		
		
		

		// -----------------------------------------FIN-------------------------------------------------

		// now user object got the created id.
		// System.out.println("1. usuario : " + usuario + "2. plazaPropia : " +
		// plazaPropia);

		// // query to search user
		// Query searchUserQuery = new
		// Query(Criteria.where("ciudad").is("Sevilla"));
		//
		// // find the saved user again.
		// PlazaPropia savedPlaza = mongoOperation.findOne(searchUserQuery,
		// PlazaPropia.class);
		// System.out.println("2. find - savedPlaza : " + savedPlaza);

	}
}