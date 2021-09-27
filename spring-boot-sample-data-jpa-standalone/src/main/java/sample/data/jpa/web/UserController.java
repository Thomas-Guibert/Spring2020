package sample.data.jpa.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.data.jpa.domain.RendezVous;
import sample.data.jpa.domain.User;
import sample.data.jpa.service.UserDao;

@Controller
public class UserController {

	/**
	 * GET /create  --> Create a new user and save it in the database.
	 */
	@RequestMapping("user/create")
	@ResponseBody
	public String create() {
		try {
			userDao.save(new User("Thomas","Guibert","ThomasG@gmail.com","azerty123",new ArrayList<RendezVous>()));	
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
	 

	
	/**
	 * GET /delete  --> Delete the user having the passed id.
	 */
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

	/**
	 * GET /get-by-email  --> Return the id for the user having the passed
	 * email.
	 *//*
	@RequestMapping("/get-by-email/{email}")
	@ResponseBody
	public String getByEmail(@PathVariable("email") String email) {
		String userId = "";
		try {
			User user = userDao.findByEmail(email);
			userId = String.valueOf(user.getId());
		}
		catch (Exception ex) {
			return "User not found";
		}
		return "The user id is: " + userId;
	}*/

	/**
	 * GET /update  --> Update the email and the name for the user in the 
	 * database having the passed id.
	 */
	@RequestMapping("user/updateName")
	@ResponseBody
	public String updateUser(String mail, String name) {
		try {
			
			User user = userDao.findUserByMail(mail);
			user.setName(name);
			userDao.save(user);
		}
		catch (Exception ex) {
			return "Error updating the user: " + ex.toString();
		}
		return "User succesfully updated!";
	}

	// Private fields
	
	/**
	 * GET /create  --> Create a new user and save it in the database.
	 */
	@RequestMapping("user/showAllUser")
	@ResponseBody
	public String showAllUser() {
		try {
			List<User> users =userDao.getAllUser();
			String res= "";
			for (User next : users) {
				res += next.getName() + " " + next.getNameF() + " - mail : " + next.getMail() + " - pwd : " + next.getMdp();
			}
			return res;
		}
		catch (Exception ex) {
			return "Error creating the user: " + ex.toString();
		}
	}

	@Autowired
	private UserDao userDao;

}