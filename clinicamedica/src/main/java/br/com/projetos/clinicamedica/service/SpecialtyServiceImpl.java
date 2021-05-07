package br.com.projetos.clinicamedica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetos.clinicamedica.entity.Specialty;
import br.com.projetos.clinicamedica.exception.ErrorRegisterNotFoundInDataBase;
import br.com.projetos.clinicamedica.repository.SpecialtyRepository;

@Service
public class SpecialtyServiceImpl implements SpecialtyService{

    @Autowired
    private SpecialtyRepository doctorRepository;	
	
	
	@Override
	public List<Specialty> findAll() {
		return doctorRepository.findAll();
	}

	@Override
	public Specialty findById(Long theId) {		
		Optional<Specialty> result = doctorRepository.findById(theId);

		if(result.isPresent()) {
			return result.get();
		}else {
			//Especialidade Médica não encontrado
			throw new ErrorRegisterNotFoundInDataBase("Especialidade Médica com Id " + theId + " não existe no banco de dados.");
		}
	}

	@Override
	public void save(Specialty theSpecialty) {
		// Caso o usuário envie um ID do frontend pelo JSON,
		// esse resguardo seta ele como 0, para o sistema forçar a entender como um novo
		// ao invés de fazer o update - Boas práticas!
		theSpecialty.setId(0L);
		doctorRepository.save(theSpecialty);		
	}

	@Override
	public boolean deleteById(Long theId) {
		Optional<Specialty> optionalSpecialty = doctorRepository.findById(theId);

		if(optionalSpecialty.isPresent()){
			doctorRepository.deleteById(theId);
			return true;
		}
		throw new ErrorRegisterNotFoundInDataBase("Especialidade Médica com Id " + theId + " não existe no banco de dados.");
	}

	@Override
	public Specialty update(Specialty theSpecialty) {
		Optional<Specialty> optionalSpecialty = doctorRepository.findById(theSpecialty.getId());

		if(optionalSpecialty.isPresent()){
			return doctorRepository.save(theSpecialty);
		}else{
			throw new ErrorRegisterNotFoundInDataBase("Especialidade Médica com Id " + theSpecialty.getId() + " não existe no banco de dados.");
		}
		
	}
    
}
