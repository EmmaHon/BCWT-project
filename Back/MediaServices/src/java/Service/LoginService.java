/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Bean.SessionBean;
import Entity.Users;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author erkki
 */
@Path("login")
public class LoginService {

    
    private SessionBean seshBean;

    /**
     * Creates a new instance of LoginService
     */
    public LoginService() {
    }

    /**
     * Logs in the user if the credentials match an entry in the database.
     * Sends a Cookie object to the client. Creates a Token entry for a user in
     * the database
     * @param email
     * @param password
     * @return 
     */
    @PUT
    @Path("/trylogin")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response login(@FormParam("email") String email, @FormParam("password") String password) {
        
        Users possibleUser = seshBean.loginUser(email, password);
        
        NewCookie cookie;
        
        if(possibleUser != null) {
           cookie = new NewCookie("token", Integer.toString(seshBean.getToken(email, password)));
           return Response.ok("Login successful").cookie(cookie).build();
        } else {
            return Response.ok("OK - No session").build();
        }
    }

    /**
     * PUT method for updating or creating an instance of LoginService
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }
}
