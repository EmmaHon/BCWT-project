/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "hotvideos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hotvideos.findAll", query = "SELECT h FROM Hotvideos h")
    , @NamedQuery(name = "Hotvideos.findByVidid", query = "SELECT h FROM Hotvideos h WHERE h.vidid = :vidid")
    , @NamedQuery(name = "Hotvideos.findByKarma", query = "SELECT h FROM Hotvideos h WHERE h.karma = :karma")})
public class Hotvideos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VIDID")
    @Id
    private int vidid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "karma")
    private BigDecimal karma;

    public Hotvideos() {
    }

    public int getVidid() {
        return vidid;
    }

    public void setVidid(int vidid) {
        this.vidid = vidid;
    }

    public BigDecimal getKarma() {
        return karma;
    }

    public void setKarma(BigDecimal karma) {
        this.karma = karma;
    }
    
}
