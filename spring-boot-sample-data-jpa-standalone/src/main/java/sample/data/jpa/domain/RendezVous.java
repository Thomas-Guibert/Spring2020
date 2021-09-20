package sample.data.jpa.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class RendezVous implements Serializable {
	private long id;
	private Date date;
	private int durer;
	private User user;
	private Professionnel professionnel;
	private String description;

	public RendezVous() {}
	public RendezVous(Date date, int durer, User user, Professionnel professionnel, String description) {
		this.date = date;
		this.durer = durer;
		this.user = user;
		this.professionnel = professionnel;
		this.description = description;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDurer() {
		return durer;
	}

	public void setDurer(int durer) {
		this.durer = durer;
	}

	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	public Professionnel getProfessionnel() {
		return professionnel;
	}

	public void setProfessionnel(Professionnel professionnel) {
		this.professionnel = professionnel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}



