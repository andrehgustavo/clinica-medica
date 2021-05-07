package br.com.projetos.clinicamedica.service;

import java.util.List;

import br.com.projetos.clinicamedica.entity.Doctor;

public interface DoctorService {
    public List<Doctor> findAll();

	public Doctor findById(Long theId);

	public void save(Doctor theDoctor);

	public Doctor update(Doctor theDoctor);

	public boolean deleteById(Long theId);
}
