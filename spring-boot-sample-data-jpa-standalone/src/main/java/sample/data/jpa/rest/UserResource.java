package sample.data.jpa.rest;
/*
import java.io.PrintWriter;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import com.fasterxml.jackson.annotation.JsonIgnore;

import sample.data.jpa.dao.RendezVousDAO;
import sample.data.jpa.dao.UserDAO;
import sample.data.jpa.domain.RendezVous;
import sample.data.jpa.domain.User;
import io.swagger.v3.oas.annotations.Parameter;

@Path("/user")
@Produces({"application/json", "application/xml"})
public class UserResource {

  @GET()
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/show/{mailUser}")
  public String showUserByMail(@PathParam("mailUser") String mail)  {
	  UserDAO u = new UserDAO();
      return u.showUser(u.getUserByMail(mail));
  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/get/{mailUser}")
  @JsonIgnore
  public User getUserByMail(@PathParam("mailUser") String mail)  {
	  UserDAO u = new UserDAO();
      return u.getUserByMail(mail);
  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/getAll")
  @JsonIgnore
  public List<User> getAllUser()  {
	  UserDAO u = new UserDAO();
      return u.getAllUser();
  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/getRDV/{UserMail}")
  @JsonIgnore
  public RendezVous getRDVMail(@PathParam("UserMail") String mail)  {
	  RendezVousDAO r = new RendezVousDAO();
	  UserDAO u = new UserDAO();
      return r.getRdvByMail(u.getUserByMail(mail));
  }
/*
  @POST
  @Path("/exemple")
  @Consumes("application/json")
  public Response addPet(
      @Parameter(description = "Pet object that needs to be added to the store", required = true) User pet) {
    // add pet
    return Response.ok().entity("SUCCESS").build();
  }*/
  /*
  @POST
  @Path("/addUser/{UserName}/{UserNameF}/{UserMail}/{UserPwd}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addUser(@PathParam("UserName") String name,@PathParam("UserNameF") String nameF,@PathParam("UserMail") String mail,@PathParam("UserPwd") String pwd) {
	UserDAO u = new UserDAO();
	u.createUser(name, nameF, mail, pwd);
    return Response.ok().entity("SUCCESS").build();
  }
  
  // Autre solution
  /*
  @POST
  @Path("/addUserS2")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addUser(User user) {
	UserDAO u = new UserDAO();
	u.save(user); // Ajouter save dans la DAO
    return Response.ok().entity("SUCCESS").build();
  }
   */
  /*
  
  @POST
  @Path("/addUserBasic")
  @Consumes("form/user-data")
  public Response addUserb() {
	
    UserDAO u = new UserDAO();
    u.createUser("TestAjout", "TestF", "mail", "pwd");
    
    return Response.ok().entity("SUCCESS").build();
  }
}*/
