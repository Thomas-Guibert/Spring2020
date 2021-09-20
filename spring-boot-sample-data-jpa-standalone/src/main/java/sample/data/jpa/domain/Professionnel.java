package sample.data.jpa.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Professionnel")
public class Professionnel extends User implements Serializable{

	private String url;
	private String job;
	
	public Professionnel() {}
	public Professionnel(String name,String nameF, String mail, String mdp, List<RendezVous> rendezvous, String job, String url) {
		super(name, nameF, mail, mdp, rendezvous);
		this.url=url;
		this.job=job;
	}

	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getJob() {
		return job;
	}


	public void setJob(String job) {
		this.job = job;
	}
}

