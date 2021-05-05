package br.com.projetos.clinicamedica.entity;

import java.util.List;

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

@Entity
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private boolean active;

    @ManyToMany(fetch = FetchType.LAZY,
			   cascade = {CascadeType.DETACH, CascadeType.MERGE,
			   CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name = "doctor_specialty",
			joinColumns = @JoinColumn(name = "specialty_id"),
			inverseJoinColumns = @JoinColumn(name = "doctor_id")
			)
	private List<Doctor> doctors;

//################  Constructors #################
    public Specialty() {
    }

    public Specialty(String description, boolean active, List<Doctor> doctors) {
        this.description = description;
        this.active = active;
        this.doctors = doctors;
    }

//################  Getters/Setters #################
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
    
//################  To String/Others #################
    @Override
    public String toString() {
        return "Specialty [active=" + active + ", description=" + description + ", doctors=" + doctors + ", id=" + id
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (active ? 1231 : 1237);
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((doctors == null) ? 0 : doctors.hashCode());
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
        if (active != other.active)
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (doctors == null) {
            if (other.doctors != null)
                return false;
        } else if (!doctors.equals(other.doctors))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}
