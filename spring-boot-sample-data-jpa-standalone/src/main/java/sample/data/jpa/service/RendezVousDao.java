package sample.data.jpa.service;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import sample.data.jpa.domain.Professionnel;
import sample.data.jpa.domain.RendezVous;
import sample.data.jpa.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Component
public interface RendezVousDao extends JpaRepository<RendezVous, Long> {

	@Query("Select a From RendezVous a")
	public List<RendezVous> getAllRendezVous();
	
	@Query("Select a From RendezVous a where a.user = ?1")
	public List<RendezVous> getAllRendezVousOfOneUser(User user);
	
	@Query("Select a From RendezVous a where a.professionnel = ?1")
	public List<RendezVous> getAllRendezVousOfOneProfessionnel(Professionnel professionnel);
	
	@Query("Select a From RendezVous a where a.user = ?1 and a.professionnel = ?2")
	public RendezVous getAllRendezVousOfOneProfessionnelAndOneProfessionnel(User user, Professionnel professionnel);
	
}
