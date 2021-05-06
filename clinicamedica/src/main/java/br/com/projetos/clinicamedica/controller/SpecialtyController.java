package br.com.projetos.clinicamedica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetos.clinicamedica.entity.Specialty;
import br.com.projetos.clinicamedica.service.SpecialtyService;

@RestController
@RequestMapping("/api")
public class SpecialtyController {
    @Autowired
    private SpecialtyService specialtyService;

	@GetMapping("/specialties")
	public List<Specialty> findAll() {
		return specialtyService.findAll();
	}

	@GetMapping("/specialties/{specialtyId}")
	public Specialty getSpecialty(@PathVariable Long specialtyId) {

		Specialty theSpecialty = specialtyService.findById(specialtyId);

		if (theSpecialty == null) {
			throw new RuntimeException("Id da especialidade não encontrada: " + specialtyId);
		}

		return theSpecialty;
	}

	@PostMapping("/specialties")
	public Specialty addSpecialty(@RequestBody Specialty theSpecialty) {

		// Caso o usuário envie um ID do frontend pelo JSON,
        // esse resguardo seta ele como 0, para o sistema forçar a entender como um novo
        // ao invés de fazer o update - Boas práticas!
		theSpecialty.setId(0L);

		specialtyService.save(theSpecialty);

		return theSpecialty;
	}

	@PutMapping("/specialties")
	public Specialty updateSpecialty(@RequestBody Specialty theSpecialty) {

		specialtyService.save(theSpecialty);

		return theSpecialty;
	}

	@DeleteMapping("/specialties/{specialtyId}")
	public String deleteSpecialty(@PathVariable Long specialtyId) {
		
		Specialty theSpecialty = specialtyService.findById(specialtyId);

		if (theSpecialty == null) {
			throw new RuntimeException("Id da especialidade não encontrada: " + specialtyId);
		}
		
		specialtyService.deleteById(specialtyId);
		
		return "Especialidade com id deletada: " + specialtyId;
	}
}
