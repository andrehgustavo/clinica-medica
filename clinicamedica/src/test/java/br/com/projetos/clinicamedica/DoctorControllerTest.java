package br.com.projetos.clinicamedica;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.projetos.clinicamedica.controller.DoctorController;

public class DoctorControllerTest extends ClinicaMedicaApplicationTests {
    
    private MockMvc mockMvc;

    @Autowired
    private DoctorController doctorController;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(doctorController).build();
    }

    @Test
    public void testGetAllDoctorsReturnsStatus200() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8084/api/doctors"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
