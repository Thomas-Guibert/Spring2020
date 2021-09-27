package sample.data.jpa.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.data.jpa.domain.Professionnel;
import sample.data.jpa.domain.RendezVous;
import sample.data.jpa.domain.User;
import sample.data.jpa.domain.RendezVous;
import sample.data.jpa.service.RendezVousDao;

@Controller
public class RendezVousController {

	/**
	 * GET /create  --> Create a new rendezvous and save it in the database.
	 */
	@SuppressWarnings("deprecation")
	//TODO Create a faire
	@RequestMapping("/rdv/create")
	@ResponseBody
	public String create(int year,int month,int date,int hrs, int min, int durer,User u, Professionnel p, String desc) {
		try {
			Date d = new Date(year, month, date, hrs, min);
			rendezvousDao.save(new RendezVous(d, durer, u, p, desc));
		}
		catch (Exception ex) {
			return "Error creating the rendezvous: " + ex.toString();
		}
		return "RendezVous succesfully created with id = " /*+ rendezvousId*/;
	}


	/**
	 * GET /delete  --> Delete the rendezvous having the passed id.
	 *//*
		@RequestMapping("/rdv/delete")
		@ResponseBody
		public String delete(long id) {
			try {
				RendezVous rendezvous = new RendezVous(id);
				rendezvousDao.delete(rendezvous);
			}
			catch (Exception ex) {
				return "Error deleting the rendezvous:" + ex.toString();
			}
			return "RendezVous succesfully deleted!";
		}*/

	/**
	 * GET /get-by-email  --> Return the id for the rendezvous having the passed
	 * email.
	 *//*
		@RequestMapping("/rdv/get-by-email/{email}")
		@ResponseBody
		public String getByEmail(@PathVariable("email") String email) {
			String rendezvousId = "";
			try {
				RendezVous rendezvous = rendezvousDao.findByEmail(email);
				rendezvousId = String.valueOf(rendezvous.getId());
			}
			catch (Exception ex) {
				return "RendezVous not found";
			}
			return "The rendezvous id is: " + rendezvousId;
		}*/

	/**
	 * GET /update  --> Update the email and the name for the rendezvous in the 
	 * database having the passed id.
	 */
	/*@RequestMapping("/rdv/updateName")
	@ResponseBody
	public String updateRendezVous(String mail, String name) {
		try {

			RendezVous rendezvous = rendezvousDao.(mail);
			rendezvous.setName(name);
			rendezvousDao.save(rendezvous);
		}
		catch (Exception ex) {
			return "Error updating the rendezvous: " + ex.toString();
		}
		return "RendezVous succesfully updated!";
	}*/

	// Private fields

	/**
	 * GET /create  --> Create a new rendezvous and save it in the database.
	 */
	@RequestMapping("/rdv/showAllRendezVous")
	@ResponseBody
	public String showAllRendezVous() {
		try {
			List<RendezVous> rendezvouss =rendezvousDao.getAllRendezVous();
			String res= "";
			for (RendezVous next : rendezvouss) {
				res += next.getDate() + " " + next.getDate() + " - User : " + next.getUser().getName() + " - Professionnel : " + next.getProfessionnel().getName() + "  - Description :  " + next.getDescription();
			}
			System.out.println(res);
		}
		catch (Exception ex) {
			return "Error creating the rendezvous: " /*+ ex.toString()*/;
		}
		return "RendezVous succesfully created with id = " /*+ rendezvousId*/;
	}

	@Autowired
	private RendezVousDao rendezvousDao;

}