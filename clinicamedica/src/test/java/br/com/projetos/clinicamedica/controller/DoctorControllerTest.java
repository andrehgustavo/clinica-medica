package br.com.projetos.clinicamedica.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.projetos.clinicamedica.api.DoctorController;
import br.com.projetos.clinicamedica.entity.Doctor;
import br.com.projetos.clinicamedica.service.DoctorService;

@WebMvcTest(DoctorController.class)
class DoctorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private DoctorService doctorService;

	@Test
	public void testListDoctorsReturnsStatus200() throws Exception {
		List<Doctor> listDoctors = new ArrayList<>();
		listDoctors.add(new Doctor(1L, "Medico Teste 1", Date.valueOf("1986-08-11"), true));
		listDoctors.add(new Doctor(2L, "Medico Teste 2", Date.valueOf("1987-08-11"), true));
		listDoctors.add(new Doctor(3L, "Medico Teste 3", Date.valueOf("1988-08-11"), true));
		listDoctors.add(new Doctor(4L, "Medico Teste 4", Date.valueOf("1989-08-11"), true));
		listDoctors.add(new Doctor(5L, "Medico Teste 5", Date.valueOf("1990-08-11"), true));

		Mockito.when(doctorService.findAll()).thenReturn(listDoctors);

		String url = "/api/doctors";

		MvcResult mvcResult = mockMvc.perform(
				get(url))
				.andExpect(status()
				.isOk()
			).andReturn();
		
		String actualJsonResponse = mvcResult.getResponse().getContentAsString();
		
		String expectedJsonResponse = objectMapper.writeValueAsString(listDoctors);
		
		assertThat(actualJsonResponse).isEqualToIgnoringCase(expectedJsonResponse);
	}
	
	@Test
	public void testCreateNewDoctorReturnsStatus201() throws JsonProcessingException, Exception {
		Doctor newDoctor = new Doctor("Medico Teste 1", Date.valueOf("1986-08-11"), true);
		Doctor savedDoctor = new Doctor(1L, "Medico Teste 1", Date.valueOf("1986-08-11"), true);
		
		Mockito.when(doctorService.save(newDoctor)).thenReturn(savedDoctor);
		
		String url = "/api/doctors";
		mockMvc.perform(
				post(url).
				contentType("application/json").
				content(objectMapper.writeValueAsString(newDoctor))
		).andExpect(status().isCreated())
		.andExpect(content().string("1"));
	}
	
	@Test
	public void testUpdateDoctorReturnsStatus200() throws JsonProcessingException, Exception {

		Doctor newDoctor = new Doctor(89L, "Medico", Date.valueOf("1989-05-25"), true);
		Doctor savedDoctor = new Doctor(89L, "Medico", Date.valueOf("1989-05-25"), true);
		
		Mockito.when(doctorService.update(newDoctor)).thenReturn(savedDoctor);
		
		String url = "/api/doctors";
		mockMvc.perform(put(url)
				.content(objectMapper.writeValueAsString(newDoctor))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("id", is((int) (long) newDoctor.getId())));
	}
	
	@Test
	public void testDeleteDoctorReturnStatus200() throws Exception {
		Long doctorId = 89L;
		
		Mockito.doNothing().when(doctorService).deleteById(doctorId);
		
		String url = "/api/doctors/" + doctorId;
		
		mockMvc.perform(delete(url)).andExpect(status().isOk());
		
		Mockito.verify(doctorService, times(1)).deleteById(doctorId);
		
	}
	
	

}
