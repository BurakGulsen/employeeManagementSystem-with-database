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
@Table(name = "PERSONEL_GRS_CKS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonelGrsCks.findAll", query = "SELECT p FROM PersonelGrsCks p")
    , @NamedQuery(name = "PersonelGrsCks.findByPersonelId", query = "SELECT p FROM PersonelGrsCks p WHERE p.personelId = :personelId")
    , @NamedQuery(name = "PersonelGrsCks.findByPersonelGiris", query = "SELECT p FROM PersonelGrsCks p WHERE p.personelGiris = :personelGiris")
    , @NamedQuery(name = "PersonelGrsCks.findByPersonelCikis", query = "SELECT p FROM PersonelGrsCks p WHERE p.personelCikis = :personelCikis")
    , @NamedQuery(name = "PersonelGrsCks.findByAdi", query = "SELECT p FROM PersonelGrsCks p WHERE p.adi = :adi")
    , @NamedQuery(name = "PersonelGrsCks.findBySoyadi", query = "SELECT p FROM PersonelGrsCks p WHERE p.soyadi = :soyadi")
    , @NamedQuery(name = "PersonelGrsCks.findByYas", query = "SELECT p FROM PersonelGrsCks p WHERE p.yas = :yas")
    , @NamedQuery(name = "PersonelGrsCks.findByCinsiyet", query = "SELECT p FROM PersonelGrsCks p WHERE p.cinsiyet = :cinsiyet")
    , @NamedQuery(name = "PersonelGrsCks.findByUnvanId", query = "SELECT p FROM PersonelGrsCks p WHERE p.unvanId = :unvanId")})
public class PersonelGrsCks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PERSONEL_ID")
    private Integer personelId;
    @Column(name = "PERSONEL_GIRIS")
    private String personelGiris;
    @Column(name = "PERSONEL_CIKIS")
    private String personelCikis;
    @Column(name = "ADI")
    private String adi;
    @Column(name = "SOYADI")
    private String soyadi;
    @Column(name = "YAS")
    private Integer yas;
    @Column(name = "CINSIYET")
    private String cinsiyet;
    @Column(name = "UNVAN_ID")
    private Integer unvanId;

    public PersonelGrsCks() {
    }

    public PersonelGrsCks(Integer personelId) {
        this.personelId = personelId;
    }

    public Integer getPersonelId() {
        return personelId;
    }

    public void setPersonelId(Integer personelId) {
        this.personelId = personelId;
    }

    public String getPersonelGiris() {
        return personelGiris;
    }

    public void setPersonelGiris(String personelGiris) {
        this.personelGiris = personelGiris;
    }

    public String getPersonelCikis() {
        return personelCikis;
    }

    public void setPersonelCikis(String personelCikis) {
        this.personelCikis = personelCikis;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public Integer getYas() {
        return yas;
    }

    public void setYas(Integer yas) {
        this.yas = yas;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public Integer getUnvanId() {
        return unvanId;
    }

    public void setUnvanId(Integer unvanId) {
        this.unvanId = unvanId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personelId != null ? personelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonelGrsCks)) {
            return false;
        }
        PersonelGrsCks other = (PersonelGrsCks) object;
        if ((this.personelId == null && other.personelId != null) || (this.personelId != null && !this.personelId.equals(other.personelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BPII_ders1.PersonelGrsCks[ personelId=" + personelId + " ]";
    }
    
}
