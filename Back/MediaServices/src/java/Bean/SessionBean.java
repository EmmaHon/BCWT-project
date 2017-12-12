/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Entity.Freshvideos;
import Entity.Hotvideos;
import Entity.Topvideos;
import Entity.Users;
import Entity.Video;
import Entity.Vote;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author erkki
 */
@Stateless
public class SessionBean {
    
    private List<Freshvideos> freshVideosList;
    private List<Hotvideos> hotVideosList;
    private List<Topvideos> topVideosList;
    
    @PersistenceContext //("MediaServicesPU")
    private EntityManager em;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
    public SessionBean(){
         scheduler.scheduleAtFixedRate(new Runnable(){
             @Override
             public void run() {
                 try{
                     refreshViews();
                 }catch(Exception pokemon){
                     System.out.println("Found a shiny "+pokemon.getLocalizedMessage());
                 }
             }
         }, 1, 1, TimeUnit.HOURS);
    }
    
    /**
     * Tries to find the user credentials corresponding to the user trying to
     * login.
     * @param email
     * @param password
     * @return null Users object if query is null, and a Users object representing
     * the correct user credentials if query is successful
     */
    public Users loginUser(String email, String password) throws Exception {
    
       Query findUser = em.createNamedQuery("Users.findUser");
       
       findUser.setParameter("email", email);
       findUser.setParameter("password", password);
       
       return (Users)findUser.getResultList().get(0);
    }
    
    /**
     * Returns a token for the authentication of the user.
     * @param email
     * @param password
     * @return 
     */
    public int getToken(String email, String password) {
        
        String token = email + password;
        
        Date present = new Date(Calendar.getInstance().getTime().getTime());
        
        token += present.toString();
        int hash = token.hashCode();
        //persist token to DB
        em.createNativeQuery("UPDATE users u SET token = "+hash+" WHERE email = '"+email+"' AND password = '"+password+"'").executeUpdate();
        return hash;
    }
    
    public boolean userExists(String email, String username){
        return (!em.createNamedQuery("Users.findByEmail").setParameter("email",email).getResultList().isEmpty() //email is in db
                ||// OR
                !em.createNamedQuery("Users.findByUsername").setParameter("username",username).getResultList().isEmpty());//username is taken
    }
    
    private void refreshViews(){
        freshVideosList = em.createNamedQuery("Freshvideos.findAll",Freshvideos.class).getResultList();
        topVideosList = em.createNamedQuery("Topvideos.findAll",Topvideos.class).getResultList();
        hotVideosList = em.createNamedQuery("Hotvideos.findAll",Hotvideos.class).getResultList();
    }
    
    public String insert(Users usr){
        try{em.persist(usr);
        }
        catch(Error e){
            return e.getLocalizedMessage();
        };
        return "success";
    }
    
    public String upload(Video vid){
        try{em.persist(vid);
        }
        catch(Error e){
            return e.getLocalizedMessage();
        }
        return "Success";
    }
    
    public Vote giveVote(Vote vote){
        em.persist(vote);
        return vote;
    }
    
    public Video updateVideo(Video vid){
        em.persist(vid);
        return vid;
    }   
    public Video vidByID(int nr){
        return (Video) em.createNamedQuery("Video.findByVidid").setParameter("vidid",nr).getResultList().get(0);
    }
    
    public ArrayList<Video> videoListFromSearch(String str){
        return new ArrayList<>(em.createNamedQuery("Video.Search").setParameter("search", str).getResultList());
    }
    
    public ArrayList<Video> videoListByIDRange(int from, int to){
        return new ArrayList<>(em.createNamedQuery("Video.getRange").setParameter(from, to).getResultList());
    }
    
    public List<Freshvideos> getFreshVideosList(){
        return freshVideosList;
    }
    
    public List<Hotvideos> getHotVideosList(){
        return hotVideosList;
    }
    
    public List<Topvideos> getTopVideosList(){
        return topVideosList;
    }
}
