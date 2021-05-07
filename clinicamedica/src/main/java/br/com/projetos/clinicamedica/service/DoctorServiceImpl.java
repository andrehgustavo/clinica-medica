package br.com.projetos.clinicamedica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetos.clinicamedica.entity.Doctor;
import br.com.projetos.clinicamedica.exception.ErrorRegisterNotFoundInDataBase;
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

		if(result.isPresent()) {
			return result.get();
		}else {
			//Médico não encontrado
			throw new ErrorRegisterNotFoundInDataBase("Médico com Id " + theId + " não existe no banco de dados.");
		}
	}

	@Override
	public Doctor save(Doctor theDoctor) {
		// Caso o usuário envie um ID do frontend pelo JSON,
		// esse resguardo seta ele como 0, para o sistema forçar a entender como um novo
		// ao invés de fazer o update - Boas práticas!
		theDoctor.setId(0L);
		return doctorRepository.save(theDoctor);		
	}

	@Override
	public void deleteById(Long theId) {
		Optional<Doctor> optionalDoctor = doctorRepository.findById(theId);

		if(optionalDoctor.isPresent()){
			doctorRepository.deleteById(theId);
		}else {		
			throw new ErrorRegisterNotFoundInDataBase("Médico com Id " + theId + " não existe no banco de dados.");
		}
	}

	@Override
	public Doctor update(Doctor theDoctor) {
		Optional<Doctor> optionalDoctor = doctorRepository.findById(theDoctor.getId());

		if(optionalDoctor.isPresent()){
			return doctorRepository.save(theDoctor);
		}else{
			throw new ErrorRegisterNotFoundInDataBase("Médico com Id " + theDoctor.getId() + " não existe no banco de dados.");
		}
		
	}
    
}
