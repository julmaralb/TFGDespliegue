package repositories;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import domain.Propuesta;

@Repository
public interface PropuestaRepository extends MongoRepository<Propuesta, String> {

	Collection<Propuesta> findAllByRemitenteId(String remitenteId);
	
	Collection<Propuesta> findAllByDestinatarioId(String destinatarioId);
}
