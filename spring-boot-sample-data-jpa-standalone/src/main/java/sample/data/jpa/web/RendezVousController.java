package sample.data.jpa.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.classic.Logger;
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

	@SuppressWarnings("deprecation")
	@RequestMapping("/rdv/createdefault")
	@ResponseBody
	public String createdefault() {
		try {
			//Date d1 = new Date(2021, 10, 15, 18, 30);
			int g = userDao.getAllUser().size();
			
			//rendezvousDao.save(new RendezVous(new Date(2021, 10, 15, 18, 30), 45, userDao.findUserByMail("ThomasG@gmail.com"), professionnelDao.findProfessionnelByEmail("ax@gmail.com"), "Rendez vous test 1"));
			
			//Date d2 = new Date(2021, 10, 17, 16, 00);
			//rendezvousDao.save(new RendezVous(d2, 30, userDao.findUserByMail("j.r@gmail.com"), professionnelDao.findProfessionnelByEmail("jc@orange.fr"), "Rendez vous test"));

		}
		catch (Exception ex) {
			return "Error creating the rendezvous";
		}
		return "RendezVous succesfully";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping("/rdv/create/{year}/{month}/{date}/{hrs}/{min}/{durer}/{umail}/{pmail}/{desc}")
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

	@RequestMapping("/rdv/deleteRdv/{umail}/{pmail}")
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

	@RequestMapping("/rdv/deleteRdvAll")
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

	@RequestMapping("/rdv/deleteRdvUser/{umail}")
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

	@RequestMapping("/rdv/deleteRdvPro/{pmail}")
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

	@SuppressWarnings("deprecation")
	@RequestMapping("/rdv/updateDate/{year}/{month}/{date}/{hrs}/{min}/{umail}/{pmail}")
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

	@RequestMapping("/rdv/updateDate/{durer}/{umail}/{pmail}")
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

	@RequestMapping("/rdv/updateDate/{desc}/{umail}/{pmail}")
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

	@RequestMapping("/rdv/showAllRendezVous")
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<RendezVous> showAllRendezVous() {
		return rendezvousDao.getAllRendezVous();
	}

	@RequestMapping("/rdv/showRendezVousUser/{umail}")
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<RendezVous> showAllRendezVousUser(@PathVariable("umail") String umail) {
		return rendezvousDao.getAllRendezVousOfOneUser(userDao.findUserByMail(umail));
	}

	@RequestMapping("/rdv/showRendezVousPro/{pmail}")
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<RendezVous> showAllRendezVousPro(@PathVariable("pmail") String pmail) {
		return rendezvousDao.getAllRendezVousOfOneUser(professionnelDao.findProfessionnelByEmail(pmail));
	}

	@RequestMapping("/rdv/showRendezVousUserPro/{umail}/{pmail}")
	@Produces(MediaType.APPLICATION_JSON)
	@ResponseBody
	public RendezVous showAllRendezVousUserPro(@PathVariable("umail") String umail,@PathVariable("pmail") String pmail) {
		return rendezvousDao.getAllRendezVousOfOneProfessionnelAndOneProfessionnel(userDao.findUserByMail(umail), professionnelDao.findProfessionnelByEmail(pmail));
	}

	@Autowired
	private RendezVousDao rendezvousDao;
	private UserDao userDao;
	private ProfessionnelDao professionnelDao; 

}