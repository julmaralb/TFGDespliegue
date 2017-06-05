package repositories;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import domain.ZonaDeseada;

@Repository
public interface ZonaDeseadaRepository extends MongoRepository<ZonaDeseada, String> {

	public Collection<ZonaDeseada> findAllByUsuarioId(String usuarioId);

}
