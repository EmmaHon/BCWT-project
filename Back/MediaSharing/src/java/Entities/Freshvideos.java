/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author danie
 */
@Entity
@Table(name = "freshvideos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Freshvideos.findAll", query = "SELECT f FROM Freshvideos f")
    , @NamedQuery(name = "Freshvideos.findByVidid", query = "SELECT f FROM Freshvideos f WHERE f.vidid = :vidid")
    , @NamedQuery(name = "Freshvideos.findByUpload", query = "SELECT f FROM Freshvideos f WHERE f.upload = :upload")})
public class Freshvideos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VIDID")
    @Id
    private int vidid;
    @Column(name = "UPLOAD")
    @Temporal(TemporalType.DATE)
    private Date upload;

    public Freshvideos() {
    }

    public int getVidid() {
        return vidid;
    }

    public void setVidid(int vidid) {
        this.vidid = vidid;
    }

    public Date getUpload() {
        return upload;
    }

    public void setUpload(Date upload) {
        this.upload = upload;
    }
    
}
