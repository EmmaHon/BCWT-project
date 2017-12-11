/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Bean.SessionBean;
import Entity.Users;
import javax.ejb.EJB;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    
    @EJB
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
    @POST
    @Path("trylogin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("email") String email, @FormParam("password") String password) {
        
        try {
        Users possibleUser = seshBean.loginUser(email, password);
        
        NewCookie cookie;
        if(possibleUser != null) {
           cookie = new NewCookie("token", Integer.toString(seshBean.getToken(email, password)));
           String rsp = "{\"status\": \"ok\",\"cookie\": \""+cookie.toString()+"\"}";
           return  Response.ok(rsp,MediaType.APPLICATION_JSON).build();
           
        } else {
            String rsp = "{\"status\": \"fail\"}";
            return Response.ok(rsp, MediaType.APPLICATION_JSON).build();
        }
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
    
}
