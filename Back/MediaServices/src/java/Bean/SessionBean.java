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
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author erkki
 */
@Stateless
public class SessionBean {
    
    private ArrayList<Freshvideos> freshVideosList = new ArrayList<>();
    private ArrayList<Hotvideos> hotVideosList = new ArrayList<>();
    private ArrayList<Topvideos> topVideosList = new ArrayList<>();
    
    private EntityManager em;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
    public SessionBean(){
         scheduler.scheduleAtFixedRate(new Runnable(){
             @Override
             public void run() {
                 try{
                     refreshViews();
                 }catch(Exception pokemon){
                     System.out.println("Found a shiny "+pokemon.getMessage());
                 }
             }
         }, 1, 1, TimeUnit.HOURS);
    }
    
    private void refreshViews(){
        freshVideosList = (ArrayList<Freshvideos>) em.createNamedQuery("Freshvideos.findAll").getResultList();
        topVideosList = (ArrayList<Topvideos>) em.createNamedQuery("Topvideos.findAll").getResultList();
        hotVideosList = (ArrayList<Hotvideos>) em.createNamedQuery("Hotvideos.findAll").getResultList();
    }
    
    public Users insert(Users usr){
        em.persist(usr);
        return usr;
    }
    
    public Video upload(Video vid){
        em.persist(vid);
        return vid;
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
    
    public ArrayList<Freshvideos> getFreshVideosList(){
        return freshVideosList;
    }
    
    public ArrayList<Hotvideos> getHotVideosList(){
        return hotVideosList;
    }
    
    public ArrayList<Topvideos> getTopVideosList(){
        return topVideosList;
    }
}
