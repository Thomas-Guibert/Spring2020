# TP3 MMM : Le framework Spring

###### Guibert Thomas & Koopman Valentin

## GitHub du TP3

Lien : https://github.com/Thomas-Guibert/Spring2020/tree/GuibertDev

## GitHub des autres TP

#### TP1 + TP2 partie 1 :

Lien : https://github.com/Thomas-Guibert/tpjpa2021/tree/GuibertDevTP2

#### TP2 partie 2 :

Lien : https://github.com/Thomas-Guibert/JaxRSOpenAPI/tree/GuibertDevT2Part2

## But du TP

Ce TP a pour but de ce TP est de se familiariser avec Spring et d'adapter les TP 1 et 2 à celui-ci.

Sur les conseils d'Adrien Le Roch nous n'avons pas réellement fait la première partie du TP qui portait sur le modèle de la banque, nous nous sommes contenté de regarder comment cela était mis en place grace aux exemples donnés dans les liens GitHub.

Pour ce TP, nous nous sommes donc concentré sur la partie 3, l'adaptation des deux Tp précédent à Spring et nous avons mis un peu de Spring AOP commence la partie 2 du TP le montrait.

## Comment lancer le TP

Avant tout pour lancer le projet, il faut mettre en place une base de données, comme une base mysql avec docker par exemple :

```
docker run --name some-mysql  -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123 -d mysql:latest
```

```
docker run --name myadmin -d --link some-mysql:db -p 8082:80 phpmyadmin
```

De préférence avec le mot de passe "123" étant utilisé de base dans la configuration du TP.

Ensuite, il suffit d'exécuter la class *"SampleDataJpaApplication"* situer dans la package *"sample.data.jpa"*.

Puis enfin d'utiliser Postman pour utiliser les controller et ainsi modifier 

## Les classes métiers

Tout le long de TAA nous avons utilisé un modèle à trois entités:

- User : qui ont un ID, un prénom, un nom, un mail, un mot de passe et une liste de rendez-vous
- Professionnel : qui hérite d'User, ils ont tous les paramètre de ceci en plus d'avoir une URL et un job
- Rendez-Vous : qui ont un ID, une date, une durer, un user, un professionnel et une description

Ainsi un Professionnel et un User peuvent avoir plusieurs rendez-vous et un rendez-vous à un seul User et un seul professionnel (ManyToOne - OneToMany)



Les classes métier n'ont pas vraiment changé en passant sur Spring. Elle garde les annotations telles que Entity, Id ou ManyToOne. 

Voici la classe métier de User :

```Java

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TypeUtilisateur")
@DiscriminatorValue("Patient")
public class User implements Serializable{
	private long id;
	private String name;
	private String nameF;
	private String mail;
	private String mdp;
	private List<RendezVous> rendezvous = new ArrayList<RendezVous>(); 

	public User() {}
	public User(String name, String nameF, String mail, String mdp, List<RendezVous> rendezvous) {
		this.name=name;
		this.nameF=nameF;
		this.mail=mail;
		this.mdp=mdp;
		this.rendezvous=rendezvous;
	}
	
	public String getNameF() {
		return nameF;
	}

	public void setNameF(String nameF) {
		this.nameF = nameF;
	}
	

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@JsonIgnore
	//@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	public List<RendezVous> getRendezvous() {
		return rendezvous;
	}

	public void setRendezvous(List<RendezVous> rendezvous) {
		this.rendezvous = rendezvous;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(unique = true)
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
}

```

Lors du TP2 un problème du chargement infini a été rencontré, en effet le Json qui chargeait un user, chargeait le rendez-vous rattacher, mais il chargeait à nouveau l'User car il était contenu dans celui-ci. C'est pour cela que l'annotation "@JsonIgnore" a été ajoutée.

## Les DAO avec Spring

Comme vu en cours les DAO en string sont plus simples à mettre en place. Plus besoin de code, seulement des requêtes JPQL.

Une nouvelle fois voici celle de User :

```Java
@Transactional
@Component
public interface UserDao extends JpaRepository<User, Long> {
    
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
}
```

## Les controllers

Pour les Controller, nous nous basons sur l'API Rest developper au TP2, ils servent à agir sur la base de donnée en utilisant la DAO crée plutôt. Les controllers permettent de faire 4 actions : ajoutées de nouveau element, mettre à jour les attributs des elements, récupérer information contenue dans la base et enfin supprimer des éléments.

Pour passer des Rest à Spring, il a juste fallus dapter le fichier avec les bonnes annotations.

Voici le controller de User :

```Java

@Controller
public class UserController {

	//Create
	
	@RequestMapping("user/createdefault")
	@ResponseBody
	public String create() {
		try {
			userDao.save(new User("Thomas","Guibert","ThomasG@gmail.com","azerty123",new ArrayList<RendezVous>()));	
			userDao.save(new User("Julie", "Reina","j.r@gmail.com", "JuliePwd", new ArrayList<RendezVous>()));
			userDao.save(new User("Henri", "Poupard", "henri@orange.fr", "HenriPwd", new ArrayList<RendezVous>()));
			userDao.save(new User("Arnaud", "Goxe", "a@gmail.com", "ArnaudPwd", new ArrayList<RendezVous>()));
		}
		catch (Exception ex) {
			return "Error creating the user";
		}
		return "User succesfully created with id" ;
	}
	
	
	@RequestMapping("user/create/{UserName}/{UserNameF}/{UserMail}/{UserPwd}")
	@ResponseBody
	public String create(@PathVariable("UserName") String name,@PathVariable("UserNameF") String nameF,@PathVariable("UserMail") String mail,@PathVariable("UserPwd") String pwd) {
		try {
			userDao.save(new User(name,nameF,mail,pwd,new ArrayList<RendezVous>()));	
		}
		catch (Exception ex) {
			return "Error creating the user";
		}
		return "User succesfully created with id" ;
	}
	 

	//Delete
	
	@RequestMapping("user/delete/{email}")
	@ResponseBody
	public String delete(@PathVariable("email") String email) {
		try {
			userDao.delete(userDao.findUserByMail(email));
		}
		catch (Exception ex) {
			return "Error deleting the user:" + ex.toString();
		}
		return "User succesfully deleted!";
	}
	
	@RequestMapping("user/deleteAll")
	@ResponseBody
	public String deleteAll() {
		try {
			List<User> users = userDao.getAllUser();
			for(User next : users) {
				userDao.delete(userDao.findUserByMail(next.getMail()));
			}
		}
		catch (Exception ex) {
			return "Error deleting the user:" + ex.toString();
		}
		return "User succesfully deleted!";
	}

	
	//Update

	@RequestMapping("/user/updateNamebyMail/{mail}/{nameF}")
	@ResponseBody
	public String updateNamebyMail(@PathVariable("mail") String mail, @PathVariable("nameF") String name) {
		try {
			User u = userDao.findUserByMail(mail);
			u.setName(name);
			userDao.save(u);
		}
		catch (Exception ex) {
			return "Error updating the user: " + ex.toString();
		}
		return "User succesfully updated!";
	}
	
	@RequestMapping("/user/updateNameFbyMail/{mail}/{nameF}")
	@ResponseBody
	public String updateNameFbyMail(@PathVariable("mail") String mail, @PathVariable("nameF") String nameF) {
		try {
			User u = userDao.findUserByMail(mail);
			u.setNameF(nameF);
			userDao.save(u);
		}
		catch (Exception ex) {
			return "Error updating the user: " + ex.toString();
		}
		return "User succesfully updated!";
	}
	
	@RequestMapping("/user/updateMdpbyMail/{mail}/{mdp}")
	@ResponseBody
	public String updateMdpbyMail(@PathVariable("mail") String mail, @PathVariable("mdp") String mdp) {
		try {
			User u = userDao.findUserByMail(mail);
			u.setMdp(mdp);
			userDao.save(u);
		}
		catch (Exception ex) {
			return "Error updating the user: " + ex.toString();
		}
		return "User succesfully updated!";
	}
	
	
	//Show
	@RequestMapping("user/showAllUser")
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<User> showAllUser() {
			return userDao.getAllUser();
	}
	
	@RequestMapping("user/showUser/{mail}")
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public User showUser(@PathVariable("mail") String mail) {
			return userDao.findUserByMail(mail);
	}

	@Autowired
	private UserDao userDao;

}
```

## AOP

La partie AOP est située dans le package *"sample.data.jpa.monitor"*. Celui-ci a pour but d'afficher un Logger après l'exécution d'un service dans un controller, en affichant quelle méthode a été executé que ce soit pour User, Professionnel ou RendezVous.

## Amélioration possible et conclusion

À la fin de ce TP nous obtenons le côté backend complet de l'application, il ne manquerait donc que le frontend avec une interface web. Celle-ci n'a pas été faite quand elle n'était techniquqment pas demandée mais grace au servelet vu au TP2, nous aurons pu mettre cela en place assez facilement.

Au final, l'UE TAA nous a permis de comprendre et de savoir développer le côté backend d'une application, mais aussi un petit peu frontend avec les Servelets. Ils sont à aussi permet de comprendre le framework Spring (Spring Boot, Spring Data), ce qui nous sera très utile plus tard dans le développement d'applications.
