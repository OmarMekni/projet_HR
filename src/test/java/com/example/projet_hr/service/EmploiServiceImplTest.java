package com.example.projet_hr.service;


import com.example.projet_hr.model.Emploi;
import com.example.projet_hr.model.Employe;
import com.example.projet_hr.repository.EmploiRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Voici la classe des tests JobServiceImplTest avec l'utilisation de Mockito
 */
@ExtendWith(MockitoExtension.class)
class EmploiServiceImplTest {

    @Mock
    private EmploiRepository emploiRepository;

    @InjectMocks
    private EmploiService emploiService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testSaveJob_WhenJobSaved_ReturnJob() {
        Emploi emploi = new Emploi();
        emploi.setTitreEmploi("TestTitle");
        //Le resultat attendu de la methode
        when(emploiRepository.save(emploi)).thenReturn(emploi);

        Emploi savedJob = emploiService.saveEmploi(emploi);

        assertNotNull(savedJob);
    }

    @Test
    void testFindJobById_WhenJobWithIdExist_ReturnJob() {
        Emploi job = new Emploi();
        job.setTitreEmploi("TestTitle");
        job.setId(10);
        //Le resultat attendu de la methode
        when(emploiRepository.findById(10)).thenReturn(Optional.of(job));
        Emploi foundJob = emploiService.findEmploiById(10);
        //Hypothese de test
        assertNotNull(foundJob);
        assertEquals(job.getId(), foundJob.getId());
    }

    @Test
    void testDeleteJobById_WhenJobWithIdExist_ReturnTrue() {
        Emploi job = new Emploi();
        job.setTitreEmploi("TestTitle");
        job.setId(10);
        //Le resultat attendu de la methode
        when(emploiRepository.findById(10)).thenReturn(Optional.of(job));

        boolean result = emploiService.deleteEmploiById(10);
        //Hypothese de test
        assertTrue(result);
        verify(emploiRepository, times(1)).deleteById(10);

    }

    @Test
    void testFindAll_WhenListIsNotEmpty_ReturnsListOfJobs() {
        Emploi job1 = new Emploi();
        job1.setTitreEmploi("TestTitle1");
        job1.setId(10);

        Emploi job2 = new Emploi();
        job2.setTitreEmploi("TestTitle2");
        job2.setId(11);
        //Créer un list de job
        List<Emploi> jobList = new ArrayList<>();
        jobList.add(job1);
        jobList.add(job2);

//        when(EmploiServiceImpl.findAll()).thenReturn(jobList); ////////////////////

        List<Emploi> foundJobs = emploiService.findAll();
        assertNotNull(foundJobs);
        assertEquals(2, foundJobs.size());
        assertEquals(job1, foundJobs.get(0));
        assertEquals(job2, foundJobs.get(1));
    }

    @Test
    void testUpdateJobTitleById_WhenJobExists_ReturnUpdatedJob() {
        Emploi emploi = new Emploi();
        emploi.setTitreEmploi("TestTitle");
        emploi.setId(10);

        String newTitle = "TestNewTitle";

        //Le resultat attendu de la methode
        when(emploiRepository.findById(10)).thenReturn(Optional.of(emploi));
        when(emploiRepository.save(any(Emploi.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Emploi updateJob = emploiService.updateTitreEmploiById(10, newTitle);

        assertNotNull(updateJob);
        assertEquals(newTitle, updateJob.getTitreEmploi());
        verify(emploiRepository, times(1)).findById(10);
        verify(emploiRepository, times(1)).save(any(Emploi.class));
    }

}