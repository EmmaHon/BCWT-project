/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Bean.SessionBean;
import Entity.Video;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.core.Cookie;

/**
 *
 * @author danie
 */
@WebServlet(name = "FileUpload", urlPatterns = {"/upload"})
@MultipartConfig(location = "/media/videos")
public class Upload extends HttpServlet {
    @EJB
    private SessionBean sb;
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log("Uploading file");
        log(request.getParameter("videotitle"));
        log(request.getParameter("videodescr"));
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        
        try (PrintWriter out = response.getWriter()) {
            Part p = request.getPart("video");
            p.write(p.getSubmittedFileName());
            out.print("{\"test\" : \"" + p.getSubmittedFileName() + "\"}");
             
            Video vid = new Video();
            vid.setDescr(request.getParameter("videodescr"));
            vid.setTitle(request.getParameter("videotitle"));
            vid.setFilepath("/media/videos"+p.getSubmittedFileName());
            vid.setUpload(Calendar.getInstance().getTime());
            //vid.setUserid();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
