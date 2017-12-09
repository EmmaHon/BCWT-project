/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Bean.SessionBean;
import Entity.Freshvideos;
import Entity.Hotvideos;
import Entity.Topvideos;
import Entity.Video;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author danie
 */
@Path("getVideo")
public class VideoService {
    
    @EJB
    private SessionBean sb;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of VideoService
     */
    public VideoService() {
    }

    /**
     * Retrieves representation of an instance of Service.VideoService
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of VideoService
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    @GET
    @Path("getVidByID")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVideo(@QueryParam("vid_id")int nr){
        return Response.ok(sb.vidByID(nr)).cookie(new NewCookie("test", "123")).build();
    }
    
    @GET
    @Path("getVidByDescr")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Video> getVidFromDescr (@QueryParam("description")String descr){
        return sb.videoListFromSearch(descr);
    }
    
    @GET
    @Path("getFreshVideos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Freshvideos> getFreshVideosList(){
        return sb.getFreshVideosList();
    }
    
    @GET
    @Path("getHotVideos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hotvideos> getHotVideosList(){
        return sb.getHotVideosList();
    }
    
    @GET
    @Path("getTopVideos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Topvideos> getTopVideosList(){
        return sb.getTopVideosList();
    }
}
