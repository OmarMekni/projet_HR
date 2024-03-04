package com.example.projet_hr.service;

import com.example.projet_hr.model.Departement;
import com.example.projet_hr.repository.DepartementRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Voici la classe des tests DepartementServiceImplTest avec l'utilisation de Mockito
 */
@ExtendWith(MockitoExtension.class)
class DepartementServiceImplTest {

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementServiceImpl departementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSaveDepartement_WhenDepartementExists_ReturnDepartement() {
        Departement departement = new Departement();
        departement.setNomDepartement("TestDepartementName");
        when(departementRepository.save(departement)).thenReturn(departement);
        Departement savedDepartement = departementService.saveDepartement(departement);
        assertNotNull(savedDepartement);
    }

    @Test
    void testFindDepartementById_WhenDepartementExists_ReturnDepartement() {
        Departement departement = new Departement();
        departement.setNomDepartement("TestDepartementName");
        departement.setId(15);
        when(departementRepository.findById(15)).thenReturn(Optional.of(departement));
        Departement foundDepartement = departementService.findDepartementById(15);
        assertNotNull(foundDepartement);
        assertEquals(15, foundDepartement.getId());
    }

    @Test
    void testDeleteDepartementById_WhenDepartementExists_ReturnDepartement() {
        Departement departement = new Departement();
        departement.setNomDepartement("TestDepartementName");
        departement.setId(15);
        when(departementRepository.findById(15)).thenReturn(Optional.of(departement));
        boolean reesult = departementService.deleteDepartementById(15);
        assertTrue(reesult);
        verify(departementRepository, times(1)).deleteById(15);
    }

    @Test
    void testFindAll_WhenDepartementExists_ReturnDepartement() {
        Departement departement1 = new Departement();
        departement1.setNomDepartement("TestDepartementName1");
        departement1.setId(15);
        Departement departement2 = new Departement();
        departement2.setNomDepartement("TestDepartementName2");
        departement2.setId(20);
        List<Departement> departementList = new ArrayList<>();
        departementList.add(departement1);
        departementList.add(departement2);
        when(departementRepository.findAll()).thenReturn(departementList);
        List<Departement> foundDepartements = departementService.findAll();
        assertNotNull(foundDepartements);
        assertEquals(2, foundDepartements.size());
        assertEquals(departement1, foundDepartements.get(0));
        assertEquals(departement2, foundDepartements.get(1));
    }

    @Test
    void testUpdateDepartementNameById_WhenDepartementExists_ReturnUpdatedDepartement() {
        Departement departement1 = new Departement();
        departement1.setNomDepartement("TestDepartementName1");
        departement1.setId(15);
        String newName = "TestNewName";

        when(departementRepository.findById(15)).thenReturn(Optional.of(departement1));
        when(departementRepository.save(any(Departement.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Departement updateDepartement = departementService.updateDepartementNameById(15, newName);

        assertNotNull(updateDepartement);
        assertEquals(newName, updateDepartement.getNomDepartement());
        verify(departementRepository, times(1)).findById(15);
        verify(departementRepository, times(1)).save(any(Departement.class));
    }

}