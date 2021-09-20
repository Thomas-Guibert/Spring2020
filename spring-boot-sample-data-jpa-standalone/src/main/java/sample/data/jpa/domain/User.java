package sample.data.jpa.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

