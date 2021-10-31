package sample.data.jpa.web;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import sample.data.jpa.domain.Professionnel;
import sample.data.jpa.domain.RendezVous;
import sample.data.jpa.domain.User;
import sample.data.jpa.domain.Professionnel;
import sample.data.jpa.service.ProfessionnelDao;

@Controller
public class ProfessionnelController {

	//Create
	@ApiOperation(value = "CreateDefault : Crée des professionnel par defaut")
	@PostMapping("pro/createdefault")
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
	
	@ApiOperation(value = "Create : Crée un professionnel avec des information donner")
	@PostMapping("/pro/create/{Name}/{NameF}/{Mail}/{Pwd}/{Job}/{Url}")
	@ResponseBody
	public String create(@PathVariable("Name") String name,@PathVariable("NameF") String nameF,@PathVariable("Mail") String mail,@PathVariable("Pwd") String pwd, @PathVariable("Job") String job,@PathVariable("Url") String url ) {
		try {
			professionnelDao.save(new Professionnel(name,nameF,mail,pwd,new ArrayList<RendezVous>(),job,url));
		}
		catch (Exception ex) {
			return "Error creating the professionnel:";
		}
		return "Professionnel succesfully created";
	}

	
	
	//Delete
		@ApiOperation(value = "Delete : Supprime un professionnel a partir du mail")
		@DeleteMapping("pro/delete/{email}")
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
		
		@ApiOperation(value = "DeleteAll : Supprime tout les professionnel")
		@DeleteMapping("pro/deleteAll")
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
		@ApiOperation(value = "UpdateName : Met a jour le prenom du professionnel renseigner avec son mail")
		@PutMapping("/pro/updateNamebyMail/{mail}/{nameF}")
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
		
		@ApiOperation(value = "UpdateNameF : Met a jour le nom de famille du professionnel renseigner avec son mail")
		@PutMapping("/pro/updateNameFbyMail/{mail}/{nameF}")
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
		
		
		@ApiOperation(value = "UpdateMdp : Met a jour le mot de passe du professionnel renseigner avec son mail")
		@PutMapping("/pro/updateMdpbyMail/{mail}/{mdp}")
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
		
		@ApiOperation(value = "UpdateUrl : Met a jour l'url du professionnel renseigner avec son mail")
		@PutMapping("/pro/updateUrlbyMail/{mail}/{url}")
		@ResponseBody
		public String updateUrlbyMail(@PathVariable("mail") String mail, @PathVariable("url") String url) {
			try {
				Professionnel u = professionnelDao.findProfessionnelByEmail(mail);
				u.setUrl(url);
				professionnelDao.save(u);
			}
			catch (Exception ex) {
				return "Error updating the professionnel: " + ex.toString();
			}
			return "Professionnel succesfully updated!";
		}
	
		@ApiOperation(value = "UpdateUrl : Met a jour l'url du professionnel renseigner avec son mail")
		@PutMapping("/pro/updateJobbyMail/{mail}/{job}")
		@ResponseBody
		public String updateJobbyMail(@PathVariable("mail") String mail, @PathVariable("job") String job) {
			try {
				Professionnel u = professionnelDao.findProfessionnelByEmail(mail);
				u.setJob(job);
				professionnelDao.save(u);
			}
			catch (Exception ex) {
				return "Error updating the professionnel: " + ex.toString();
			}
			return "Professionnel succesfully updated!";
		}
	
	//show
	@ApiOperation(value = "ShowAll : Affiche tous les professionnel dans la base de données")
	@GetMapping("pro/showAllProfessionnel")
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Professionnel> showAllProfessionnel() {
			return professionnelDao.getAllProfessionnel();
	}
	
	@ApiOperation(value = "Show : Affiche les informations d'un professionnel a partir d'un mail")
	@GetMapping("pro/showProfessionnel/{mail}")
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public Professionnel showProfessionnel(@PathVariable("mail") String mail) {
			return professionnelDao.findProfessionnelByEmail(mail);
	}

	@Autowired
	private ProfessionnelDao professionnelDao;

}