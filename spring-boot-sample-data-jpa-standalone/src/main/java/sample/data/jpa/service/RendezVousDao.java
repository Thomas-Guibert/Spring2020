package sample.data.jpa.service;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import sample.data.jpa.domain.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Component
public interface RendezVousDao extends JpaRepository<RendezVous, Long> {

	@Query("Select a From RendezVous a")
	public List<RendezVous> getAllRendezVous();
	
	@Query("Select a From RendezVous a where a.user = ?1")
	public List<String> getAllRendezVousOfOneUser(String user);
	
	@Query("Select a From RendezVous a where a.professionnel = ?1")
	public List<String> getAllRendezVousOfOneProfessionnel(String professionnel);
	
}
