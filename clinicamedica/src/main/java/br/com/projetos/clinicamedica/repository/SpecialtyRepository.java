package br.com.projetos.clinicamedica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.projetos.clinicamedica.entity.Specialty;

@RepositoryRestResource(path = "specialties")
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    
}