/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author erkki
 */
@Entity
@Table(name = "video")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v")
    , @NamedQuery(name = "Video.findByVidid", query = "SELECT v FROM Video v WHERE v.vidid = :vidid")
    , @NamedQuery(name = "Video.findByTitle", query = "SELECT v FROM Video v WHERE v.title = :title")
    , @NamedQuery(name = "Video.findByFilepath", query = "SELECT v FROM Video v WHERE v.filepath = :filepath")
    , @NamedQuery(name = "Video.findByVotes", query = "SELECT v FROM Video v WHERE v.votes = :votes")
    , @NamedQuery(name = "Video.findByUpload", query = "SELECT v FROM Video v WHERE v.upload = :upload")
    , @NamedQuery(name = "Video.findByDescr", query = "SELECT v FROM Video v WHERE v.descr = :descr")})
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "VIDID")
    private Integer vidid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1028)
    @Column(name = "FILEPATH")
    private String filepath;
    @Column(name = "VOTES")
    private Integer votes;
    @Column(name = "UPLOAD")
    @Temporal(TemporalType.DATE)
    private Date upload;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1028)
    @Column(name = "DESCR")
    private String descr;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne
    private Users userid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "video")
    private Collection<Vote> voteCollection;

    public Video() {
    }

    public Video(Integer vidid) {
        this.vidid = vidid;
    }

    public Video(Integer vidid, String title, String filepath, String descr) {
        this.vidid = vidid;
        this.title = title;
        this.filepath = filepath;
        this.descr = descr;
    }

    public Integer getVidid() {
        return vidid;
    }

    public void setVidid(Integer vidid) {
        this.vidid = vidid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public Date getUpload() {
        return upload;
    }

    public void setUpload(Date upload) {
        this.upload = upload;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Users getUserid() {
        return userid;
    }

    public void setUserid(Users userid) {
        this.userid = userid;
    }

    @XmlTransient
    public Collection<Vote> getVoteCollection() {
        return voteCollection;
    }

    public void setVoteCollection(Collection<Vote> voteCollection) {
        this.voteCollection = voteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vidid != null ? vidid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Video)) {
            return false;
        }
        Video other = (Video) object;
        if ((this.vidid == null && other.vidid != null) || (this.vidid != null && !this.vidid.equals(other.vidid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Video[ vidid=" + vidid + " ]";
    }
    
}
