package sample.data.jpa.service;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sample.data.jpa.domain.User;

@Transactional
@Component
public interface UserDao extends JpaRepository<User, Long> {

	/**
	 * This method will find an User instance in the database by its email.
	 * Note that this method is not implemented and its working code will be
	 * automagically generated from its signature by Spring Data JPA.
	 */

	@Query("Select a From User a")
	public List<User> getAllUser();

	@Query("Select name From User")
	public List<String> getAllUserName();
	
	@Query("Select nameF From User")
	public List<String> getAllUserNameF();
	
	@Query("Select mail From User")
	public List<String> getAllUserMail();
	
	@Query("Select mdp From User")
	public List<String> getAllUserPwd();

	@Query("Select a From User a where a.mail = ?1")
	public User findUserByMail(String email);
	
	@Query("Select a From User a where a.name = ?1 and a.nameF = ?2")
	public User findUserByNames(String name,String nameF);
	
	@Query("Select a.rendezvous From User as a where a.mail = ?1")
	public User findUserRendezVous(String mail);
	
	//@Query("Select a From User as a where a.id = ?1")
	//public User findUserById(long id);
	 
	 
}