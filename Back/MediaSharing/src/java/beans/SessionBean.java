/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Entities.Users;
import Entities.Video;
import Entities.Vote;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author danie
 */
@Stateless
public class SessionBean {
    
    private EntityManager em;
    
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
}
