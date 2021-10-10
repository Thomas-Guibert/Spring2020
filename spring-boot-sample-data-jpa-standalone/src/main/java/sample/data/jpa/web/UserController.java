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

import sample.data.jpa.domain.RendezVous;
import sample.data.jpa.domain.User;
import sample.data.jpa.service.ProfessionnelDao;
import sample.data.jpa.service.UserDao;

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