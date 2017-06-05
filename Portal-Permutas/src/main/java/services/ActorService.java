package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Actor;
import repositories.ActorRepository;

@Service
@Transactional
public class ActorService {

	// Managed repository-----------------------------------------------------

	@Autowired
	private ActorRepository actorRepository;

	// Supporting services----------------------------------------------------

	// Constructors-----------------------------------------------------------

	public ActorService() {
		super();
	}

	// Simple CRUD methods----------------------------------------------------

	public Actor findOne(String actorId) {
		Assert.notNull(actorId);

		Actor result;

		result = actorRepository.findOne(actorId);

		return result;
	}

	public Collection<Actor> findAll() {
		Collection<Actor> result;

		result = actorRepository.findAll();

		return result;
	}

	// Other business methods-------------------------------------------------

	// public Actor findByPrincipal() {
	// Actor result;
	// UserAccount userAccount;
	//
	// userAccount = LoginService.getPrincipal();
	// result = findByUserAccount(userAccount);
	//
	// Assert.notNull(result);
	//
	// return result;
	// }
	//
	// public Actor findByUserAccount(UserAccount userAccount) {
	// assert userAccount != null;
	//
	// Actor result;
	//
	// result = actorRepository.findByUserAccountId(userAccount.getId());
	// assert result != null;
	//
	// return result;
	// }

}