/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BPII_ders1;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BurakGulsen
 */
@Entity
@Table(name = "KAYIT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kayit.findAll", query = "SELECT k FROM Kayit k")
    , @NamedQuery(name = "Kayit.findById", query = "SELECT k FROM Kayit k WHERE k.id = :id")
    , @NamedQuery(name = "Kayit.findByAd", query = "SELECT k FROM Kayit k WHERE k.ad = :ad")
    , @NamedQuery(name = "Kayit.findBySoyad", query = "SELECT k FROM Kayit k WHERE k.soyad = :soyad")
    , @NamedQuery(name = "Kayit.findByKullaniciAdi", query = "SELECT k FROM Kayit k WHERE k.kullaniciAdi = :kullaniciAdi")
    , @NamedQuery(name = "Kayit.findBySiFre", query = "SELECT k FROM Kayit k WHERE k.siFre = :siFre")
    , @NamedQuery(name = "Kayit.findByEPosta", query = "SELECT k FROM Kayit k WHERE k.ePosta = :ePosta")})
public class Kayit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "AD")
    private String ad;
    @Column(name = "SOYAD")
    private String soyad;
    @Column(name = "KULLANICI_ADI")
    private String kullaniciAdi;
    @Column(name = "S\u0130FRE")
    private String siFre;
    @Column(name = "E_POSTA")
    private String ePosta;

    public Kayit() {
    }

    public Kayit(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSiFre() {
        return siFre;
    }

    public void setSiFre(String siFre) {
        this.siFre = siFre;
    }

    public String getEPosta() {
        return ePosta;
    }

    public void setEPosta(String ePosta) {
        this.ePosta = ePosta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kayit)) {
            return false;
        }
        Kayit other = (Kayit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BPII_ders1.Kayit[ id=" + id + " ]";
    }
    
}
