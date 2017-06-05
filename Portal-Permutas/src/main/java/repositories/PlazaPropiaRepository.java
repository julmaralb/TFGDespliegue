package repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import domain.PlazaPropia;

@Repository
public interface PlazaPropiaRepository extends MongoRepository<PlazaPropia, String> {

	public PlazaPropia findByCiudad(String ciudad);

	public PlazaPropia findByUsuarioId(String id);
}
