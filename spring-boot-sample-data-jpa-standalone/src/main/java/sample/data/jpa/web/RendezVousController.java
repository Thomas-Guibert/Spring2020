package sample.data.jpa.web;

import java.util.ArrayList;
import java.util.Date;
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

import ch.qos.logback.classic.Logger;
import io.swagger.annotations.ApiOperation;
import sample.data.jpa.domain.Professionnel;
import sample.data.jpa.domain.RendezVous;
import sample.data.jpa.domain.User;
import sample.data.jpa.domain.RendezVous;
import sample.data.jpa.service.ProfessionnelDao;
import sample.data.jpa.service.RendezVousDao;
import sample.data.jpa.service.UserDao;

@Controller
public class RendezVousController {

	//Create
	@ApiOperation(value = "CreateRendezVousDefault : Crée des rendez-vous par default")
	@SuppressWarnings("deprecation")
	@PostMapping("/rdv/createdefault")
	@ResponseBody
	public String createdefault() {
		try {
			rendezvousDao.save(new RendezVous(new Date(2021, 10, 15, 18, 30), 45, userDao.findUserByMail("ThomasG@gmail.com"), professionnelDao.findProfessionnelByEmail("ax@gmail.com"), "Rendez vous test 1"));
			rendezvousDao.save(new RendezVous(new Date(2021, 10, 17, 16, 00), 30, userDao.findUserByMail("j.r@gmail.com"), professionnelDao.findProfessionnelByEmail("jc@orange.fr"), "Rendez vous test"));
		}
		catch (Exception ex) {
			return "Error creating the rendezvous";
		}
		return "RendezVous succesfully";
	}

	@ApiOperation(value = "CreateRendezVous : Crée un rendez vous avec information renseigner")
	@SuppressWarnings("deprecation")
	@PostMapping("/rdv/create/{year}/{month}/{date}/{hrs}/{min}/{durer}/{umail}/{pmail}/{desc}")
	@ResponseBody
	public String create(@PathVariable("year") int year,@PathVariable("month") int month, @PathVariable("date") int date, @PathVariable("hrs") int hrs, @PathVariable("min") int min, @PathVariable("durer") int durer, @PathVariable("umail") String umail, @PathVariable("pmail") String pmail, @PathVariable("desc") String desc) {
		try {
			Date d = new Date(year, month, date, hrs, min);
			rendezvousDao.save(new RendezVous(d, durer, userDao.findUserByMail(umail), professionnelDao.findProfessionnelByEmail(pmail), desc));
		}
		catch (Exception ex) {
			return "Error creating the rendezvous";
		}
		return "RendezVous succesfully";
	}


	//Delete
	@ApiOperation(value = "DeleteRendezVous : Supprime un rendez vous entre un professionnel et un professionnel tout les deux identifier par leurs mail")
	@DeleteMapping("/rdv/deleteRdv/{umail}/{pmail}")
	@ResponseBody
	public String delete(@PathVariable("umail") String umail, @PathVariable("pmail") String pmail) {
		try {
			rendezvousDao.delete(rendezvousDao.getAllRendezVousOfOneProfessionnelAndOneProfessionnel(userDao.findUserByMail(umail), professionnelDao.findProfessionnelByEmail(pmail)));
		}
		catch (Exception ex) {
			return "Error deleting the rendezvous:" + ex.toString();
		}
		return "RendezVous succesfully deleted!";
	}

	@ApiOperation(value = "DeleteAllRendezVous : Supprime tout les rendezvous de la base de données")
	@DeleteMapping("/rdv/deleteRdvAll")
	@ResponseBody
	public String deleteAll() {
		try {
			List<RendezVous> list = rendezvousDao.getAllRendezVous();
			for(RendezVous next : list) {
				rendezvousDao.delete(next);
			}
		}
		catch (Exception ex) {
			return "Error deleting the rendezvous:" + ex.toString();
		}
		return "RendezVous succesfully deleted!";
	}

	@ApiOperation(value = "DeleteRendezVousUser : Supprime tout les rendezvous d'un user grace a sont mail")
	@DeleteMapping("/rdv/deleteRdvUser/{umail}")
	@ResponseBody
	public String deleteUser(@PathVariable("umail") String umail) {
		try {
			List<RendezVous> list = rendezvousDao.getAllRendezVousOfOneUser(userDao.findUserByMail(umail));
			for(RendezVous next : list) {
				rendezvousDao.delete(next);
			}
		}
		catch (Exception ex) {
			return "Error deleting the rendezvous:" + ex.toString();
		}
		return "RendezVous succesfully deleted!";
	}

	@ApiOperation(value = "DeleteRendezVousProfessionel : Supprime tout les rendezvous d'un professionnel grace a sont mail")
	@DeleteMapping("/rdv/deleteRdvPro/{pmail}")
	@ResponseBody
	public String deletePro(@PathVariable("pmail") String pmail) {
		try {
			List<RendezVous> list = rendezvousDao.getAllRendezVousOfOneProfessionnel(professionnelDao.findProfessionnelByEmail(pmail));
			for(RendezVous next : list) {
				rendezvousDao.delete(next);
			}
		}
		catch (Exception ex) {
			return "Error deleting the rendezvous:" + ex.toString();
		}
		return "RendezVous succesfully deleted!";
	}


	//Update 
	@ApiOperation(value = "UpdateDate : Met a jour la date d'un rendezvous entre un professionnel et un user")
	@SuppressWarnings("deprecation")
	@PutMapping("/rdv/updateDate/{year}/{month}/{date}/{hrs}/{min}/{umail}/{pmail}")
	@ResponseBody
	public String updateDate(@PathVariable("year") int year,@PathVariable("month") int month, @PathVariable("date") int date, @PathVariable("hrs") int hrs, @PathVariable("min") int min, @PathVariable("umail") String umail, @PathVariable("pmail") String pmail) {
		try {
			RendezVous rdv = rendezvousDao.getAllRendezVousOfOneProfessionnelAndOneProfessionnel(userDao.findUserByMail(umail), professionnelDao.findProfessionnelByEmail(pmail));
			Date d = new Date(year, month, date, hrs, min);
			rdv.setDate(d);
			rendezvousDao.save(rdv);
		}
		catch (Exception ex) {
			return "Error creating the rendezvous";
		}
		return "RendezVous update date succesfully";
	}

	@ApiOperation(value = "UpdateDurer: Met a jour la durer d'un rendezvous entre un professionnel et un user")
	@PutMapping("/rdv/updateDate/{durer}/{umail}/{pmail}")
	@ResponseBody
	public String updateDurer(@PathVariable("durer") int durer, @PathVariable("umail") String umail, @PathVariable("pmail") String pmail) {
		try {
			RendezVous rdv = rendezvousDao.getAllRendezVousOfOneProfessionnelAndOneProfessionnel(userDao.findUserByMail(umail), professionnelDao.findProfessionnelByEmail(pmail));
			rdv.setDurer(durer);
			rendezvousDao.save(rdv);
		}
		catch (Exception ex) {
			return "Error creating the rendezvous";
		}
		return "RendezVous update durer succesfully";
	}

	@ApiOperation(value = "UpdateDescription : Met a jour la desciption d'un rendezvous entre un professionnel et un user")
	@PutMapping("/rdv/updateDate/{desc}/{umail}/{pmail}")
	@ResponseBody
	public String updateDesc(@PathVariable("desc") String desc, @PathVariable("umail") String umail, @PathVariable("pmail") String pmail) {
		try {
			RendezVous rdv = rendezvousDao.getAllRendezVousOfOneProfessionnelAndOneProfessionnel(userDao.findUserByMail(umail), professionnelDao.findProfessionnelByEmail(pmail));
			rdv.setDescription(desc);
			rendezvousDao.save(rdv);
		}
		catch (Exception ex) {
			return "Error creating the rendezvous";
		}
		return "RendezVous update descritption succesfully";
	}



	// Show
	@ApiOperation(value = "ShowAllRendezVous : Montre tout les rendez vous contenus dans la base de données")
	@GetMapping("/rdv/showAllRendezVous")
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<RendezVous> showAllRendezVous() {
		return rendezvousDao.getAllRendezVous();
	}
	
	@ApiOperation(value = "ShowRendezVousUser : Montre tout les rendez vous d'un user")
	@GetMapping("/rdv/showRendezVousUser/{umail}")
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<RendezVous> showAllRendezVousUser(@PathVariable("umail") String umail) {
		return rendezvousDao.getAllRendezVousOfOneUser(userDao.findUserByMail(umail));
	}

	@ApiOperation(value = "ShowRendezVousProfessionnel : Montre tout les rendez vous d'un professionnel")
	@GetMapping("/rdv/showRendezVousPro/{pmail}")
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<RendezVous> showAllRendezVousPro(@PathVariable("pmail") String pmail) {
		return rendezvousDao.getAllRendezVousOfOneUser(professionnelDao.findProfessionnelByEmail(pmail));
	}

	@ApiOperation(value = "ShowRendezVousUserProfessionnel : Montre tout les rendez vous entre un user et un professionnel")
	@GetMapping("/rdv/showRendezVousUserPro/{umail}/{pmail}")
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public RendezVous showAllRendezVousUserPro(@PathVariable("umail") String umail,@PathVariable("pmail") String pmail) {
		return rendezvousDao.getAllRendezVousOfOneProfessionnelAndOneProfessionnel(userDao.findUserByMail(umail), professionnelDao.findProfessionnelByEmail(pmail));
	}

	@Autowired
	private RendezVousDao rendezvousDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProfessionnelDao professionnelDao; 

}