package br.com.projetos.clinicamedica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.projetos.clinicamedica.entity.Doctor;

@RepositoryRestResource(path = "doctors")
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    public Doctor findByName(String name);

    public List<Doctor> findBySpecialties_Id(Long specialtyId);

}
