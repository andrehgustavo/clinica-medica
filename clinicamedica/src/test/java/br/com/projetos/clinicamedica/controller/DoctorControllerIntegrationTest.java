package br.com.projetos.clinicamedica.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import br.com.projetos.clinicamedica.entity.Doctor;
import br.com.projetos.clinicamedica.service.DoctorService;

@SpringBootTest
@AutoConfigureMockMvc
class DoctorControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private DoctorService doctorService;
	
	@Test
	public void testCreateNewDoctorReturnsStatus201() throws JsonProcessingException, Exception {
		Doctor newDoctor = new Doctor("Medico Teste 1", Date.valueOf("1986-08-11"), true);
				
		String url = "/api/doctors";
		MvcResult mvcResult = mockMvc.perform(
				post(url).
				contentType("application/json").
				content(objectMapper.writeValueAsString(newDoctor))
		).andExpect(status()
		.isCreated())
		.andReturn();
		
		String response = mvcResult.getResponse().getContentAsString();
		Long doctorId = Long.parseLong(response);
		
		Doctor savedDoctor = doctorService.findById(doctorId);
		
		assertThat(savedDoctor.getName()).isEqualTo(newDoctor.getName());
		
	}
	
	@Test
	public void testUpdateDoctorReturnsStatus200() throws JsonProcessingException, Exception {

		Doctor existDoctor = new Doctor(14L, "Medico Updated 2", Date.valueOf("1989-05-25"), true);
		
		
		String url = "/api/doctors";
		MvcResult mvcResult = mockMvc.perform(
				put(url).
				contentType("application/json").
				content(objectMapper.writeValueAsString(existDoctor))
		).andExpect(status()
		.isOk())
		.andReturn();
		
		String response = mvcResult.getResponse().getContentAsString();
		Integer doctorId = JsonPath.parse(response).read("id");
		Long longId = Long.valueOf(doctorId.longValue());
		
		Doctor savedDoctor = doctorService.findById(longId);
		
		assertThat(savedDoctor.getName()).isEqualTo(existDoctor.getName());
	}
	
	@Test 
	public void testDeleteDoctorReturnStatus200() throws Exception {
		Long doctorId = 14L;

		String url = "/api/doctors/" + doctorId;
		
		mockMvc.perform(delete(url)).andExpect(status().isOk());
		
	}
}
