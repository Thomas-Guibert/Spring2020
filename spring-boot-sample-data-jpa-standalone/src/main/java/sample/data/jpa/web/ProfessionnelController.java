package sample.data.jpa.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.data.jpa.domain.Professionnel;
import sample.data.jpa.domain.RendezVous;
import sample.data.jpa.service.ProfessionnelDao;

@Controller
public class ProfessionnelController {

	/**
	 * GET /create  --> Create a new professionnel and save it in the database.
	 */
	@RequestMapping("/pro/create")
	@ResponseBody
	public String create(String name, String nameF,String mail, String pwd, String job, String url) {
		try {
			professionnelDao.save(new Professionnel(name,nameF,mail,pwd,new ArrayList<RendezVous>(),job,url));
		}
		catch (Exception ex) {
			return "Error creating the professionnel: " /*+ ex.toString()*/;
		}
		return "Professionnel succesfully created with id = " /*+ professionnelId*/;
	}

	
	/**
	 * GET /delete  --> Delete the professionnel having the passed id.
	 *//*
	@RequestMapping("/pro/delete")
	@ResponseBody
	public String delete(long id) {
		try {
			Professionnel professionnel = new Professionnel(id);
			professionnelDao.delete(professionnel);
		}
		catch (Exception ex) {
			return "Error deleting the professionnel:" + ex.toString();
		}
		return "Professionnel succesfully deleted!";
	}*/

	/**
	 * GET /get-by-email  --> Return the id for the professionnel having the passed
	 * email.
	 *//*
	@RequestMapping("/pro/get-by-email/{email}")
	@ResponseBody
	public String getByEmail(@PathVariable("email") String email) {
		String professionnelId = "";
		try {
			Professionnel professionnel = professionnelDao.findByEmail(email);
			professionnelId = String.valueOf(professionnel.getId());
		}
		catch (Exception ex) {
			return "Professionnel not found";
		}
		return "The professionnel id is: " + professionnelId;
	}*/

	/**
	 * GET /update  --> Update the email and the name for the professionnel in the 
	 * database having the passed id.
	 */
	@RequestMapping("/pro/updateName")
	@ResponseBody
	public String updateProfessionnel(String mail, String name) {
		try {
			
			Professionnel professionnel = professionnelDao.findProfessionnelByEmail(mail);
			professionnel.setName(name);
			professionnelDao.save(professionnel);
		}
		catch (Exception ex) {
			return "Error updating the professionnel: " + ex.toString();
		}
		return "Professionnel succesfully updated!";
	}

	// Private fields
	
	/**
	 * GET /create  --> Create a new professionnel and save it in the database.
	 */
	@RequestMapping("/pro/showAllProfessionnel")
	@ResponseBody
	public String showAllProfessionnel() {
		try {
			List<Professionnel> professionnels =professionnelDao.getAllProfessionnel();
			String res= "";
			for (Professionnel next : professionnels) {
				res += next.getName() + " " + next.getNameF() + " - mail : " + next.getMail() + " - pwd : " + next.getMdp();
			}
			System.out.println(res);
		}
		catch (Exception ex) {
			return "Error creating the professionnel: " /*+ ex.toString()*/;
		}
		return "Professionnel succesfully created with id = " /*+ professionnelId*/;
	}

	@Autowired
	private ProfessionnelDao professionnelDao;

}