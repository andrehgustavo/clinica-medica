package br.com.projetos.clinicamedica.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.hamcrest.CoreMatchers.*;

import java.sql.Date;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.projetos.clinicamedica.entity.Doctor;
import br.com.projetos.clinicamedica.entity.Specialty;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class DoctorRepositoryTest {
    
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testNewDocktorPersist(){
        //configuration
        Doctor theDoctor = new Doctor("Marreco", Date.valueOf("1986-08-11"), true);
        
        //action
        Doctor savedDoctor = doctorRepository.save(theDoctor);

        //verification
        assertNotNull(savedDoctor.getId());
    }

    @Test
    public void testCreateNewDoctorWithOneSpecialty(){
        //configuration
        Doctor theDoctor = new Doctor("Pedroca", Date.valueOf("1986-08-11"), true);
        Specialty dermato = entityManager.find(Specialty.class, 2L);
        theDoctor.addSpecialty(dermato);
        entityManager.persist(theDoctor);

       
        //action
        Doctor returnedDoctor = doctorRepository.findByName("Pedroca");
        //verification
        assertThat(returnedDoctor, is(theDoctor));
        
    }

    @Test
    public void testCreateNewDoctorWithTwoSpecialties(){
        //configuration
        Doctor theDoctor = new Doctor("Heizer", Date.valueOf("1986-08-11"), true);
        Specialty dermato = entityManager.find(Specialty.class, 2L);
        Specialty otorrino = entityManager.find(Specialty.class, 1L);
        theDoctor.addSpecialty(dermato);
        theDoctor.addSpecialty(otorrino);
        entityManager.persist(theDoctor);

       
        //action
        Doctor returnedDoctor = doctorRepository.findByName("Heizer");
        
        //verification
        assertThat(returnedDoctor, is(theDoctor));
    }
    
    @Test
    public void testAssignSpecialtyToExistingDoctor(){
        //configuration
        Doctor theDoctor = entityManager.find(Doctor.class, 1L);
        Specialty pneumo = entityManager.find(Specialty.class, 5L);
        theDoctor.addSpecialty(pneumo);

        //action
        Optional<Doctor> optDoc = doctorRepository.findById(1L);
        Doctor dbDoctor = optDoc.get();

        //verification
        Assert.assertTrue(dbDoctor.getSpecialties().contains(pneumo));

    }

    @Test
    public void testRemoveSpecialtyToExistingDoctor(){
        //configuration
        Doctor theDoctor = entityManager.find(Doctor.class, 3L);
        Specialty otorrino = entityManager.find(Specialty.class, 1L);
        theDoctor.removeSpecialty(otorrino);

        //action
        Optional<Doctor> optDoc = doctorRepository.findById(1L);
        Doctor dbDoctor = optDoc.get();

        //verification
        Assert.assertFalse(dbDoctor.getSpecialties().contains(otorrino));

    }

    @Test
    public void testRemoveDoctor(){
        Long removeId = 2L;
        Doctor theDoctor = entityManager.find(Doctor.class, removeId);
        entityManager.remove(theDoctor);

        Assert.assertThat(doctorRepository.findById(removeId), is(Optional.empty()));

    }
}
