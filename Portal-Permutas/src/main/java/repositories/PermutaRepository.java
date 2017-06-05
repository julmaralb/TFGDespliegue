package repositories;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;

import domain.Permuta;

public interface PermutaRepository extends MongoRepository<Permuta, String> {
	
	Collection<Permuta> findAllByUsuarioId(String usuarioId);

}
