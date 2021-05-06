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

import br.com.projetos.clinicamedica.entity.Doctor;
import br.com.projetos.clinicamedica.service.DoctorService;

@RestController
@RequestMapping("/api")
public class DoctorController {
    
    @Autowired
    private DoctorService doctorService;

	@GetMapping("/doctors")
	public List<Doctor> findAll() {
		return doctorService.findAll();
	}

	@GetMapping("/doctors/{doctorId}")
	public Doctor getDoctor(@PathVariable Long doctorId) {

		Doctor theDoctor = doctorService.findById(doctorId);

		if (theDoctor == null) {
			throw new RuntimeException("Id do médico não encontrado: " + doctorId);
		}

		return theDoctor;
	}

	@PostMapping("/doctors")
	public Doctor addDoctor(@RequestBody Doctor theDoctor) {

		// Caso o usuário envie um ID do frontend pelo JSON,
        // esse resguardo seta ele como 0, para o sistema forçar a entender como um novo
        // ao invés de fazer o update - Boas práticas!
		theDoctor.setId(0L);

		doctorService.save(theDoctor);

		return theDoctor;
	}

	@PutMapping("/doctors")
	public Doctor updateDoctor(@RequestBody Doctor theDoctor) {

		doctorService.save(theDoctor);

		return theDoctor;
	}

	@DeleteMapping("/doctors/{doctorId}")
	public String deleteDoctor(@PathVariable Long doctorId) {
		
		Doctor theDoctor = doctorService.findById(doctorId);

		if (theDoctor == null) {
			throw new RuntimeException("Id do médico não encontrado: " + doctorId);
		}
		
		doctorService.deleteById(doctorId);
		
		return "Médico com id deletado: " + doctorId;
	}
    
}
