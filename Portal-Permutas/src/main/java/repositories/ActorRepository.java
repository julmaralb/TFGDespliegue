package repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import domain.Actor;

@Repository
public interface ActorRepository extends MongoRepository<Actor, String> {

	Actor findByUserAccountId(String userAccountId);

}
