package br.com.projetos.clinicamedica.api;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClinicController {
    
    @GetMapping("/")
    public String sayHeloo(){
        return "Hello World! Time on server is " + LocalDateTime.now();
    }
}
