package sample.data.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.naming.ldap.ManageReferralControl;

import sample.data.jpa.domain.RendezVous;
import sample.data.jpa.domain.User;

@SuppressWarnings("unused")
public class UserDAO extends AbstractJpaDao <Long,User>{

	public UserDAO() {
		super();
		this.setClazz(User.class);
	} 
	
	public void createUser() {
		save(new User("Thomas", "Guibert", "thomas.g@etudiant.univ-rennes1.fr", "Univ1mdp", new ArrayList<RendezVous>()));
		save(new User("Julie", "Reina","j.r@gmail.com", "JuliePwd", new ArrayList<RendezVous>()));
		save(new User("Henri", "Poupard", "henri@orange.fr", "HenriPwd", new ArrayList<RendezVous>()));
		save(new User("Arnaud", "Goxe", "a@gmail.com", "ArnaudPwd", new ArrayList<RendezVous>()));
	}
	
	public void createUser(String name, String nameF, String mail, String pwd) {
		save(new User(name,nameF,mail,pwd,new ArrayList<RendezVous>()));
	}
	
	public void deleteUserByMail(String mail) {
		delete( EntityManagerHelper.getEntityManager().
				createQuery("Select a From User a where a.mail = :var", User.class).
				setParameter("var", mail).getResultList().get(0));
	}
	
	public List<User> getAllUser() {
		return EntityManagerHelper.getEntityManager().
				createQuery("Select a From User a", User.class).
				getResultList();
	}
	
	public void getAllUserNameFam() {
		List<String> resultList = EntityManagerHelper.getEntityManager().createQuery("Select nameF From User", String.class).getResultList();
		System.out.println("Nombre d'utilisateur:" + resultList.size());
		for (String next : resultList) {
			System.out.println("next employee: " + next);
		}
	}
	
	public void getAllUserName() {
		List<String> resultList = EntityManagerHelper.getEntityManager().createQuery("Select name From User", String.class).getResultList();
		System.out.println("Nombre d'utilisateur:" + resultList.size());
		for (String next : resultList) {
			System.out.println("next employee: " + next);
		}
	}
	
	public String getAllUserNametoString() {
		List<String> resultList = EntityManagerHelper.getEntityManager().createQuery("Select name From User", String.class).getResultList();
		System.out.println("Nombre d'utilisateur:" + resultList.size());
		String res = "Liste Utilisateur dans la base : ";
		for (String next : resultList) {
			res = res + "  " + next;
		}
		return res;
	}
	
	public void getAllUserMail() {
		List<String> resultList = EntityManagerHelper.getEntityManager().createQuery("Select mail From User", String.class).getResultList();
		System.out.println("Liste Mail:" + resultList.size());
		for (String next : resultList) {
			System.out.println("next mail: " + next);
		}
	}
	
	public void getAllUserMdp() {
		List<String> resultList = EntityManagerHelper.getEntityManager().createQuery("Select mdp From User", String.class).getResultList();
		System.out.println("Liste mot de passe:" + resultList.size());
		for (String next : resultList) {
			System.out.println("next mdp: " + next);
		}
	}
	
	public void updateMdp(User u,String mdp) {
		u.setMdp(mdp);
		update(u);
	}
	
	public void updateName(User u,String name) {
		u.setName(name);
		update(u);
	}
	
	public void updateNameFam(User u,String name) {
		u.setNameF(name);
		update(u);
	}
	
	public void updateMail(User u,String mail) {
		u.setMail(mail);
		update(u);
	}
	
	//TODO
	public void getUserRendezVous(String mail) {
		List<RendezVous> resultList = entityManager.
				createQuery("Select a.rendezvous From User as a where a.mail = :var", RendezVous.class).
				setParameter("var", mail).getResultList();
		System.out.println("Liste mot de passe:" + resultList.size());
		for (RendezVous next : resultList) {
			System.out.println("next rendezVous: " + next);
		}
		/*return EntityManagerHelper.getEntityManager().
				createQuery("Select rendezvous From User a where a.mail = :var", RendezVous.class).
				setParameter("var", mail).getResultList();
		*/
	}

	public User getUserByMail(String mail) {
		return EntityManagerHelper.getEntityManager().
				createQuery("Select a From User a where a.mail = :var", User.class).
				setParameter("var", mail).getResultList().get(0);
	}
	
	public String showUser(User u) {
		return u.getName()+ " " + u.getNameF() + " - mail : " + u.getMail() + " - pwd : " + u.getMdp();
	}
	
	public String showAllUser(List<User> u) {
		String res = "";
		for (User next : u) {
			res += next.getName() + " " + next.getNameF() + " - mail : " + next.getMail() + " - pwd : " + next.getMdp();
		}
		return res;
	}

}

