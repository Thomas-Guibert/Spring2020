package sample.data.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import sample.data.jpa.domain.Professionnel;
import sample.data.jpa.domain.RendezVous;
import sample.data.jpa.domain.User;

public class ProfessionnelDAO extends AbstractJpaDao <Long,Professionnel>{

	public ProfessionnelDAO() {
		super();
		this.setClazz(Professionnel.class);
	}

	public void createProfessionnel() {
		save(new Professionnel("Axel","lebian", "ax@gmail.com", "AxelPWD", new ArrayList<RendezVous>(), "Docteur", "http://Alfred.fr"));
		save(new Professionnel("Jeanne","Combot", "jc@orange.fr", "JeannePWD", new ArrayList<RendezVous>(), "Medecien", "http://Med.fr"));
		save(new Professionnel("Flo","Madre", "fm@gmail.com", "madremdp", new ArrayList<RendezVous>(), "Docteur", "http://isa.fr"));
	}
	
	public void createProfessionnel(String name, String nameF, String mail, String pwd, String job, String url) {
		save(new Professionnel(name,nameF,mail,pwd,new ArrayList<RendezVous>(),job,url));
	}
	
	public void getAllProfessionnelName() {
		List<String> resultList = EntityManagerHelper.getEntityManager().createQuery("Select name From Professionnel", String.class).getResultList();
		System.out.println("Nombre d'utilisateur:" + resultList.size());
		for (String next : resultList) {
			System.out.println("next employee: " + next);
		}
	}
	
	public String getJobByMail(String mail) {
		return entityManager.
				createQuery("Select a.job From Professionel as a where a.mail = :var", String.class).
				setParameter("var", mail).getResultList().get(0);
	}
	
	public void getAllProfessionnel() {
		List<Professionnel> resultList = EntityManagerHelper.getEntityManager().createQuery("Select a From Professionnel a", Professionnel.class).getResultList();
		System.out.println("Nombre d'utilisateur:" + resultList.size());
		for (Professionnel next : resultList) {
			System.out.println("next employee: " + next);
		}
	}
	
	public void updateJob(Professionnel p, String job) {
		p.setJob(job);
		update(p);
	}
	
	public void updateUrl(Professionnel p, String url) {
		p.setUrl(url);
		update(p);
	}
	
	public void updateName (Professionnel p, String name) {
		p.setName(name);
		update(p);
	}
	
	public void updateNameF (Professionnel p, String nameF) {
		p.setNameF(nameF);
		update(p);
	}
	
	public void updateMail (Professionnel p, String mail) {
		p.setMail(mail);
		update(p);
	}
	
	public void updateMdp (Professionnel p, String mdp) {
		p.setMdp(mdp);
		update(p);
	}
	
	public List<Professionnel> getProfessionnelByMail(String mail) {
		return EntityManagerHelper.getEntityManager().
				createQuery("Select a From Professionnel a where a.mail = :var", Professionnel.class).
				setParameter("var", mail).getResultList();
	}
}
