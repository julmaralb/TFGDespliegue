package repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import security.UserAccount;

@Repository
public interface UsuarioRepository extends MongoRepository<UserAccount, String> {

	UserAccount findById(String id);

}
