package sample.data.jpa.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import sample.data.jpa.domain.Professionnel;

@Transactional
@Component
public interface ProfessionnelDao extends JpaRepository<Professionnel, Long> {
	
	@Query("Select a From Professionnel a")
	public List<Professionnel> getAllProfessionnel();

	@Query("Select name From Professionnel")
	public List<String> getAllProfessionnelName();
	
	@Query("Select nameF From Professionnel")
	public List<String> getAllProfessionnelNameF();
	
	@Query("Select mail From Professionnel")
	public List<String> getAllProfessionnelMail();
	
	@Query("Select mdp From Professionnel")
	public List<String> getAllProfessionnelPwd();
	
	@Query("Select job From Professionnel")
	public List<String> getAllProfessionnelJob();
	
	@Query("Select url From Professionnel")
	public List<String> getAllProfessionnelUrl();
	
	@Query("Select a From Professionnel a where a.mail = ?1")
	public Professionnel findProfessionnelByEmail(String email);

	@Query("Select a From Professionnel a where a.name = ?1 AND a.nameF = ?2")
	public Professionnel findProfessionnelByNames(String name,String nameF);
	
	@Query("Select a.rendezvous From Professionnel as a where a.mail = ?1")
	public Professionnel findProfessionnelRendezVous(String mail);
	
	@Query("Select a From Professionnel a where a.job = ?1")
	public List<Professionnel> findProfessionnelListByJob(String job);
	
	@Query("Select a From Professionnel a where a.job = ?1")
	public List<Professionnel> getProfessionnelJobByMail(String job);
	
}
