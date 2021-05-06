package br.com.projetos.clinicamedica.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "active")
    private boolean active;

   
    @ManyToMany(fetch = FetchType.LAZY,
			   cascade = {CascadeType.DETACH, CascadeType.MERGE,
			   CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name = "doctor_specialty",
			joinColumns = @JoinColumn(name = "doctor_id"),
			inverseJoinColumns = @JoinColumn(name = "specialty_id")
			)
    @Column(name="specialties")
    private List<Specialty> specialties;

    //################  Constructors #################
    public Doctor(){

    }

    public Doctor(Date birthday, boolean active, List<Specialty> specialties) {
        this.birthday = birthday;
        this.active = active;
        this.specialties = specialties;
    }

    //################  Getters/Setters #################
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
    }
//################  To String/Others #################

    @Override
    public String toString() {
        return "Doctor [active=" + active + ", birthday=" + birthday + ", id=" + id + ", specialties=" + specialties
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (active ? 1231 : 1237);
        result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((specialties == null) ? 0 : specialties.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Doctor other = (Doctor) obj;
        if (active != other.active)
            return false;
        if (birthday == null) {
            if (other.birthday != null)
                return false;
        } else if (!birthday.equals(other.birthday))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (specialties == null) {
            if (other.specialties != null)
                return false;
        } else if (!specialties.equals(other.specialties))
            return false;
        return true;
    }
       
    

}
