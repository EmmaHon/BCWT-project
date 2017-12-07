/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author erkki
 */
@Embeddable
public class VotePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "USERID")
    private int userid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VIDID")
    private int vidid;

    public VotePK() {
    }

    public VotePK(int userid, int vidid) {
        this.userid = userid;
        this.vidid = vidid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getVidid() {
        return vidid;
    }

    public void setVidid(int vidid) {
        this.vidid = vidid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userid;
        hash += (int) vidid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VotePK)) {
            return false;
        }
        VotePK other = (VotePK) object;
        if (this.userid != other.userid) {
            return false;
        }
        if (this.vidid != other.vidid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.VotePK[ userid=" + userid + ", vidid=" + vidid + " ]";
    }
    
}
