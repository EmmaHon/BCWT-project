/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author danie
 */
@Entity
@Table(name = "topvideos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Topvideos.findAll", query = "SELECT t FROM Topvideos t")
    , @NamedQuery(name = "Topvideos.findByVidid", query = "SELECT t FROM Topvideos t WHERE t.vidid = :vidid")
    , @NamedQuery(name = "Topvideos.findByVotes", query = "SELECT t FROM Topvideos t WHERE t.votes = :votes")})
public class Topvideos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VIDID")
    @Id
    private int vidid;
    @Column(name = "VOTES")
    private Integer votes;

    public Topvideos() {
    }

    public int getVidid() {
        return vidid;
    }

    public void setVidid(int vidid) {
        this.vidid = vidid;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
    
}
