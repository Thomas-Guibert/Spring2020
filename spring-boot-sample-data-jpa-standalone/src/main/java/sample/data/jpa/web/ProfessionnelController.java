package sample.data.jpa.web;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.data.jpa.domain.Professionnel;
import sample.data.jpa.domain.RendezVous;
import sample.data.jpa.domain.User;
import sample.data.jpa.domain.Professionnel;
import sample.data.jpa.service.ProfessionnelDao;

@Controller
public class ProfessionnelController {

	//Create
	
	@RequestMapping("pro/createdefault")
	@ResponseBody
	public String create() {
		try {
			professionnelDao.save(new Professionnel("Axel","lebian", "ax@gmail.com", "AxelPWD", new ArrayList<RendezVous>(), "Docteur", "http://Alfred.fr"));
			professionnelDao.save(new Professionnel("Jeanne","Combot", "jc@orange.fr", "JeannePWD", new ArrayList<RendezVous>(), "Medecien", "http://Med.fr"));
			professionnelDao.save(new Professionnel("Flo","Madre", "fm@gmail.com", "madremdp", new ArrayList<RendezVous>(), "Docteur", "http://isa.fr"));
		
		}
		catch (Exception ex) {
			return "Error creating the user";
		}
		return "Professionnel succesfully created with id" ;
	}
	
	@RequestMapping("/pro/create")
	@ResponseBody
	public String create(String name, String nameF,String mail, String pwd, String job, String url) {
		try {
			professionnelDao.save(new Professionnel(name,nameF,mail,pwd,new ArrayList<RendezVous>(),job,url));
		}
		catch (Exception ex) {
			return "Error creating the professionnel:";
		}
		return "Professionnel succesfully created";
	}

	
	
	//Delete
	
		@RequestMapping("pro/delete/{email}")
		@ResponseBody
		public String delete(@PathVariable("email") String email) {
			try {
				professionnelDao.delete(professionnelDao.findProfessionnelByEmail(email));
			}
			catch (Exception ex) {
				return "Error deleting the professionnel:" + ex.toString();
			}
			return "Professionnel succesfully deleted!";
		}
		
		@RequestMapping("pro/deleteAll")
		@ResponseBody
		public String deleteAll() {
			try {
				List<Professionnel> professionnels = professionnelDao.getAllProfessionnel();
				for(Professionnel next : professionnels) {
					professionnelDao.delete(professionnelDao.findProfessionnelByEmail(next.getMail()));
				}
			}
			catch (Exception ex) {
				return "Error deleting the professionnel:" + ex.toString();
			}
			return "Professionnel succesfully deleted!";
		}

		
		//Update

		@RequestMapping("/pro/updateNamebyMail/{mail}/{nameF}")
		@ResponseBody
		public String updateNamebyMail(@PathVariable("mail") String mail, @PathVariable("nameF") String name) {
			try {
				Professionnel u = professionnelDao.findProfessionnelByEmail(mail);
				u.setName(name);
				professionnelDao.save(u);
			}
			catch (Exception ex) {
				return "Error updating the professionnel: " + ex.toString();
			}
			return "Professionnel succesfully updated!";
		}
		
		@RequestMapping("/pro/updateNameFbyMail/{mail}/{nameF}")
		@ResponseBody
		public String updateNameFbyMail(@PathVariable("mail") String mail, @PathVariable("nameF") String nameF) {
			try {
				Professionnel u = professionnelDao.findProfessionnelByEmail(mail);
				u.setNameF(nameF);
				professionnelDao.save(u);
			}
			catch (Exception ex) {
				return "Error updating the professionnel: " + ex.toString();
			}
			return "Professionnel succesfully updated!";
		}
		
		@RequestMapping("/pro/updateMdpbyMail/{mail}/{mdp}")
		@ResponseBody
		public String updateMdpbyMail(@PathVariable("mail") String mail, @PathVariable("mdp") String mdp) {
			try {
				Professionnel u = professionnelDao.findProfessionnelByEmail(mail);
				u.setMdp(mdp);
				professionnelDao.save(u);
			}
			catch (Exception ex) {
				return "Error updating the professionnel: " + ex.toString();
			}
			return "Professionnel succesfully updated!";
		}
	
	
	//show

	@RequestMapping("pro/showAllProfessionnel")
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Professionnel> showAllProfessionnel() {
			return professionnelDao.getAllProfessionnel();
	}
	
	@RequestMapping("pro/showProfessionnel/{mail}")
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public Professionnel showProfessionnel(@PathVariable("mail") String mail) {
			return professionnelDao.findProfessionnelByEmail(mail);
	}

	@Autowired
	private ProfessionnelDao professionnelDao;

}