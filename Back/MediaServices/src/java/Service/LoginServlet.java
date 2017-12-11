/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Bean.SessionBean;
import Entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author danie
 */
@WebServlet(name="Login", urlPatterns = {"/loginServlet"})
public class LoginServlet extends HttpServlet {
    
    @EJB
    private SessionBean sb;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");
        Users possibleUser;
        
        try{
        possibleUser = sb.loginUser(email, pwd);}
        catch(Exception pokemon){
        possibleUser = null;
        }
        
        if(possibleUser!=null){
            response.setStatus(200);
            Cookie cookie;
            cookie = new Cookie("loginCookie",Integer.toString(sb.getToken(email, pwd)));
            response.addCookie(cookie);
        }else{
            response.setStatus(418);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
