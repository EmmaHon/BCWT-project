/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Bean.SessionBean;
import Entity.Users;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author danie
 */
@Path("register")
public class RegisterService {
    private SessionBean sb;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RegisterService
     */
    public RegisterService() {
    }

    /**
     * Retrieves representation of an instance of Service.RegisterService
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of RegisterService
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    @PUT
    @Path("/user")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String registerUser(@FormParam("email")String email,@FormParam("username")String username,@FormParam("password")String password){
        if(sb.userExists(email, username)){
            return "Username and/or email is already in use";
        }
        Users user = new Users();
        
        user.setPassword(password);
        user.setEmail(email);
        user.setUsername(username);
        
        String token = Integer.toString(sb.getToken(email, password));
        
        user.setToken(token);
        user.setIsadmin(Boolean.FALSE);
        
        sb.insert(user);
        
        return "User registered successfuly";
    }
}
