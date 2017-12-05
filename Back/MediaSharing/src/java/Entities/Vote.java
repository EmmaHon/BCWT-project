/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author danie
 */
@Entity
@Table(name = "vote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vote.findAll", query = "SELECT v FROM Vote v")
    , @NamedQuery(name = "Vote.findByUserid", query = "SELECT v FROM Vote v WHERE v.votePK.userid = :userid")
    , @NamedQuery(name = "Vote.findByVidid", query = "SELECT v FROM Vote v WHERE v.votePK.vidid = :vidid")
    , @NamedQuery(name = "Vote.findByScore", query = "SELECT v FROM Vote v WHERE v.score = :score")})
public class Vote implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VotePK votePK;
    @Column(name = "SCORE")
    private Integer score;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;
    @JoinColumn(name = "VIDID", referencedColumnName = "VIDID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Video video;

    public Vote() {
    }

    public Vote(VotePK votePK) {
        this.votePK = votePK;
    }

    public Vote(int userid, int vidid) {
        this.votePK = new VotePK(userid, vidid);
    }

    public VotePK getVotePK() {
        return votePK;
    }

    public void setVotePK(VotePK votePK) {
        this.votePK = votePK;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (votePK != null ? votePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vote)) {
            return false;
        }
        Vote other = (Vote) object;
        if ((this.votePK == null && other.votePK != null) || (this.votePK != null && !this.votePK.equals(other.votePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Vote[ votePK=" + votePK + " ]";
    }
    
}
