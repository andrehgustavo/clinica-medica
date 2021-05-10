package br.com.projetos.clinicamedica.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private boolean active;

    @ManyToMany
    @JoinTable(
                name = "doctor_specialty", 
                joinColumns = @JoinColumn(name = "specialty_id"), 
                inverseJoinColumns = @JoinColumn(name = "doctor_id")
                )
    @JsonIgnoreProperties("specialties")
    private Set<Doctor> doctors= new HashSet<>();

    // ################ Constructors #################
    public Specialty() {
    }

    public Specialty(String name, String description, boolean active) {
        this.name = name;
        this.description = description;
        this.active = active;
    }



    // ################ Getters/Setters #################
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public void addDoctor(Doctor theDoctor) {
        if (doctors == null) {
            doctors = new HashSet<>();
        }
        doctors.add(theDoctor);
    }

    public void removeDoctor(Doctor theDoctor){
        if (doctors == null) {
            doctors = new HashSet<>();
        }
        doctors.remove(theDoctor);
    }

    // ################ To String/Others #################
    @Override
    public String toString() {
        return "Specialty [active=" + active + ", description=" + description + ", doctors=" + doctors + ", id=" + id
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
        Specialty other = (Specialty) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

   

}
