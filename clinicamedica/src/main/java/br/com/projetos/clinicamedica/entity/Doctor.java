package br.com.projetos.clinicamedica.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

   
    @ManyToMany
	@JoinTable(
			name = "doctor_specialty",
			joinColumns = @JoinColumn(name = "doctor_id"),
			inverseJoinColumns = @JoinColumn(name = "specialty_id")
			)
    @JsonIgnoreProperties("doctors")
    private Set<Specialty> specialties = new HashSet<>();

    //################  Constructors #################
    public Doctor(){

    }

    public Doctor(String name, Date birthday, boolean active) {
        this.name = name;
        this.birthday = birthday;
        this.active = active;
    }
    
    
    public Doctor(Long id, String name, Date birthday, boolean active) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.active = active;
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

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    public void addSpecialty(Specialty theSpecialty) {
        if(specialties == null) {
            specialties = new HashSet<>();
        }
        specialties.add(theSpecialty);
    }

    public void removeSpecialty(Specialty theSpecialty) {
        if(specialties == null) {
            specialties = new HashSet<>();
        }
        specialties.remove(theSpecialty);
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
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

   
       
    

}
