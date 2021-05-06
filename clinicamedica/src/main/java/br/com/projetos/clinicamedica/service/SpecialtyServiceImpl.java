package br.com.projetos.clinicamedica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetos.clinicamedica.entity.Specialty;
import br.com.projetos.clinicamedica.repository.SpecialtyRepository;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

	@Autowired
	private SpecialtyRepository specialtyRepository;

	@Override
	public List<Specialty> findAll() {
		return specialtyRepository.findAll();
	}

	@Override
	public Specialty findById(Long theId) {
		Optional<Specialty> result = specialtyRepository.findById(theId);

		Specialty theSpecialty = null;
		if (result.isPresent()) {
			theSpecialty = result.get();
		} else {
			// Especialidade não encontrada
			throw new RuntimeException("Não foi possível encontrar a especialidade pelo id: " + theId);
		}
		return theSpecialty;
	}

	@Override
	public void save(Specialty theSpecialty) {
		specialtyRepository.save(theSpecialty);
	}

	@Override
	public void deleteById(Long theId) {
		specialtyRepository.deleteById(theId);
	}

}
