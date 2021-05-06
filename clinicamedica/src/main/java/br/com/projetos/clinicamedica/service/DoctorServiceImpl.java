package br.com.projetos.clinicamedica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetos.clinicamedica.entity.Doctor;
import br.com.projetos.clinicamedica.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    private DoctorRepository doctorRepository;	
	
	
	@Override
	public List<Doctor> findAll() {
		return doctorRepository.findAll();
	}

	@Override
	public Doctor findById(Long theId) {		
		Optional<Doctor> result = doctorRepository.findById(theId);
		
		Doctor theDoctor = null;
		if(result.isPresent()) {
			theDoctor = result.get();
		}else {
			//Médico não encontrado
			throw new RuntimeException("Noi possível encontrar o médico pelo id: " + theId);
		}
		return theDoctor;
	}

	@Override
	public void save(Doctor theDoctor) {
		doctorRepository.save(theDoctor);		
	}

	@Override
	public void deleteById(Long theId) {
		doctorRepository.deleteById(theId);
	}
    
}
