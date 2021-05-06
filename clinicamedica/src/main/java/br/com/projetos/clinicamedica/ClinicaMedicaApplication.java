package br.com.projetos.clinicamedica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicaMedicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicaMedicaApplication.class, args);
		System.out.println("Aplicação no ar 🚀! Vá para http://localhost:8084/");
	}

}
