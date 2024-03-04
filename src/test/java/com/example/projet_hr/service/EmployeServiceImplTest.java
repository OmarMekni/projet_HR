package com.example.projet_hr.service;

import com.example.projet_hr.exeption.NotFoundException;
import com.example.projet_hr.model.Employe;
import com.example.projet_hr.repository.EmployeRepository;
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
 * Voici la classe des tests EmployeServiceImplTest avec l'utilisation de Mockito
 */
@ExtendWith(MockitoExtension.class)
class EmployeServiceImplTest {
    @Mock
    private EmployeRepository employeRepository;

    @InjectMocks
    private EmployeServiceImpl employeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveEmploye_WhenEmployeSaved_ReturnEmploye() {
        Employe employe = new Employe();
        employe.setNom("TestLastName");
        employe.setPrenom("TestFirstName");

        when(employeRepository.save(employe)).thenReturn(employe);

        Employe savedEmploye = employeService.saveEmploye(employe);

        assertNotNull(savedEmploye);
    }

    @Test
    void testFindEmployeById_WhenEmployeWithIdExist_ReturnEmploye() {
        Employe employe = new Employe();
        employe.setNom("TestLastName");
        employe.setPrenom("TestFirstName");
        employe.setId(10);

        when(employeRepository.findById(10)).thenReturn(Optional.of(employe));

        Employe foundEmploye = employeService.findEmployeById(10);

        assertNotNull(foundEmploye);
        assertEquals(employe.getId(), foundEmploye.getId());
    }

    @Test
    void testFindEmployeById_WhenEmployeWithIdNonExisting_ReturnNull() {
        Employe employe = new Employe();
        employe.setNom("TestLastName");
        employe.setPrenom("TestFirstName");
        employe.setId(10);

        when(employeRepository.findById(15)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            employeService.findEmployeById(15);
        });
    }

    @Test
    void testDeleteEmployeById_WhenEmployeExists_ReturnsTrue() {
        Employe employe = new Employe();
        employe.setNom("TestLastName");
        employe.setPrenom("TestFirstName");
        employe.setId(10);
        when(employeRepository.findById(10)).thenReturn(Optional.of(employe));

        boolean result = employeService.deleteEmployeById(10);

        assertTrue(result);
        verify(employeRepository, times(1)).deleteById(10);
    }

    @Test
    void testDeleteEmployeById_WhenEmployeDoesNotExist_ReturnsFalse() {
        Employe employe = new Employe();
        employe.setNom("TestLastName");
        employe.setPrenom("TestFirstName");
        employe.setId(10);
        when(employeRepository.findById(15)).thenReturn(Optional.empty());

        boolean result = employeService.deleteEmployeById(15);

        assertFalse(result);
        verify(employeRepository, never()).deleteById(15);
    }

    @Test
    void testFindAllEmployes_WhenListIsNotEmpty_ReturnsListOfEmployes(){
        Employe employe1 = new Employe();
        employe1.setId(1);
        employe1.setPrenom("John");

        Employe employe2 = new Employe();
        employe2.setId(2);
        employe2.setPrenom("Jane");

        List<Employe> employeList = new ArrayList<>();
        employeList.add(employe1);
        employeList.add(employe2);

        when(employeRepository.findAll()).thenReturn(employeList);

        List<Employe> foundEmployes = employeService.findAll();

        assertNotNull(foundEmployes);
        assertEquals(2, foundEmployes.size());
        assertEquals(employe1, foundEmployes.get(0));
        assertEquals(employe2, foundEmployes.get(1));
    }

    @Test
    void testFindAllEmployes_WhenListIsEmpty_ReturnsEmptyList() {
        List<Employe> employeList = new ArrayList<>();
        when(employeRepository.findAll()).thenReturn(employeList);

        List<Employe> foundEmployes = employeService.findAll();

        assertNotNull(foundEmployes);
        assertEquals(0, foundEmployes.size());
        assertTrue(foundEmployes.isEmpty());
    }

    @Test
    void testUpdateLastNameEmployeById_WhenEmployeExists_ReturnUpdatedEmploye() {
        Employe employe1 = new Employe();
        employe1.setPrenom("John");
        employe1.setNom("OriginalLastName");
        employe1.setId(1);

        String newLastName = "NewLastName";

        when(employeRepository.findById(1)).thenReturn(Optional.of(employe1));
        when(employeRepository.save(any(Employe.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Employe updatedEmploye = employeService.updateNomEmployeById(1, newLastName);

        assertNotNull(updatedEmploye);
        assertEquals(newLastName, updatedEmploye.getNom());

        verify(employeRepository, times(1)).findById(1);
        verify(employeRepository, times(1)).save(any(Employe.class));
    }

    @Test
    void testUpdateLastNameEmployeById_WhenEmployeDoesNotExist_ReturnNull() {
        when(employeRepository.findById(1)).thenReturn(Optional.empty());

        String newLastName = "NewLastName";

        Employe updatedEmploye = employeService.updateNomEmployeById(1, newLastName);

        assertNull(updatedEmploye);

        verify(employeRepository, times(1)).findById(1);
        verify(employeRepository, never()).save(any(Employe.class));
    }

}