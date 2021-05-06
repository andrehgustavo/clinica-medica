package br.com.projetos.clinicamedica.service;


import java.util.List;

import br.com.projetos.clinicamedica.entity.Specialty;

public interface SpecialtyService {
    public List<Specialty> findAll();

	public Specialty findById(Long theId);

	public void save(Specialty theSpecialty);

	public void deleteById(Long theId);
}
