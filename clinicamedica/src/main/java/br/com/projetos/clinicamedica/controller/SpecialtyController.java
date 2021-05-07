package br.com.projetos.clinicamedica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetos.clinicamedica.entity.Specialty;
import br.com.projetos.clinicamedica.exception.ErrorRegisterNotFoundInDataBase;
import br.com.projetos.clinicamedica.service.SpecialtyService;

@RestController
@RequestMapping("/api")
public class SpecialtyController {

	@Autowired
	private SpecialtyService specialtyService;

	@GetMapping("/specialties")
	public ResponseEntity<List<Specialty>> findAll() {
		return new ResponseEntity<>(specialtyService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/specialties/{specialtyId}")
	public ResponseEntity<?> getSpecialty(@PathVariable Long specialtyId) {
		try {
			Specialty theSpecialty = specialtyService.findById(specialtyId);
			return new ResponseEntity<>(theSpecialty, HttpStatus.OK);
		}catch (ErrorRegisterNotFoundInDataBase e) {
			return ResponseEntity.accepted().body(e.toString());
		}		
	}

	@PostMapping("/specialties")
	public ResponseEntity<?> addSpecialty(@RequestBody Specialty theSpecialty) {
		try {
			specialtyService.save(theSpecialty);
			return new ResponseEntity<>(theSpecialty, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.toString());
		}
	}

	@PutMapping("/specialties")
	public ResponseEntity<?> updateSpecialty(@RequestBody Specialty theSpecialty) {
		try {
			specialtyService.update(theSpecialty);
			return new ResponseEntity<>(theSpecialty, HttpStatus.OK);
		}catch (ErrorRegisterNotFoundInDataBase e) {
			return ResponseEntity.accepted().body(e.toString());
		}
	}

	@DeleteMapping("/specialties/{specialtyId}")
	public ResponseEntity<?> deleteSpecialty(@PathVariable Long specialtyId) {
		try {
			specialtyService.deleteById(specialtyId);
			return new ResponseEntity<>("Especialidade Médica com id " + specialtyId + " deletada com sucesso.", HttpStatus.OK);
		}catch (ErrorRegisterNotFoundInDataBase e) {
			return ResponseEntity.accepted().body(e.toString());
		}
	}

}
