package br.com.projetos.clinicamedica.api;

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

import br.com.projetos.clinicamedica.entity.Doctor;
import br.com.projetos.clinicamedica.exception.ErrorRegisterNotFoundInDataBase;
import br.com.projetos.clinicamedica.service.DoctorService;

@RestController
@RequestMapping("/api")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@GetMapping("/doctors")
	public ResponseEntity<List<Doctor>> findAll() {
		return new ResponseEntity<>(doctorService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/doctors/{doctorId}")
	public ResponseEntity<?> getDoctor(@PathVariable Long doctorId) {
		try {
			Doctor theDoctor = doctorService.findById(doctorId);
			return new ResponseEntity<>(theDoctor, HttpStatus.OK);
		}catch (ErrorRegisterNotFoundInDataBase e) {
			return ResponseEntity.accepted().body(e.toString());
		}		
	}

	@PostMapping("/doctors")
	public ResponseEntity<?> createDoctor(@RequestBody Doctor theDoctor) {
		try {
			Doctor savedDoctor = doctorService.save(theDoctor);
			return new ResponseEntity<>(savedDoctor.getId(), HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.toString());
		}
	}

	@PutMapping("/doctors")
	public ResponseEntity<?> updateDoctor(@RequestBody Doctor theDoctor) {
		try {
			doctorService.update(theDoctor);
			return new ResponseEntity<>(theDoctor, HttpStatus.OK);
		}catch (ErrorRegisterNotFoundInDataBase e) {
			return ResponseEntity.accepted().body(e.toString());
		}
	}

	@DeleteMapping("/doctors/{doctorId}")
	public ResponseEntity<?> deleteDoctor(@PathVariable Long doctorId) {
		try {
			doctorService.deleteById(doctorId);
			return new ResponseEntity<>("Médico com id " + doctorId + " deletado com sucesso.", HttpStatus.OK);
		}catch (ErrorRegisterNotFoundInDataBase e) {
			return ResponseEntity.accepted().body(e.toString());
		}
	}

	@GetMapping("/doctors/byspecialty/{specialtyId}")
	public ResponseEntity<?> findAllBySpecialty(@PathVariable Long specialtyId) {
		try {
			List<Doctor> theDoctors = doctorService.findAllBySpecialty(specialtyId);
			return new ResponseEntity<>(theDoctors, HttpStatus.OK);
		}catch (ErrorRegisterNotFoundInDataBase e) {
			return ResponseEntity.accepted().body(e.toString());
		}		
	}

}
