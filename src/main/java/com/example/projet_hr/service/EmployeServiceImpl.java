package com.example.projet_hr.service;

import com.example.projet_hr.exeption.NotFoundException;
import com.example.projet_hr.model.Employe;
import com.example.projet_hr.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeServiceImpl implements EmployeService {

    /**
     * @Autowired permet de créer un nouvel objet sans utilisation new
     */
    @Autowired
    private EmployeRepository employeRepository;


    /**
     * Méthode saveEmploye pour enregistrer un employé dans la BD
     *
     * @param employe
     * @return
     */
    @Override
    public Employe saveEmploye(Employe employe) {
        return employeRepository.save(employe);
    }


    /**
     * Méthode findEmployeById pour chercher un employe par son Id
     *
     * @param id
     * @return
     */
    @Override
    public Employe findEmployeById(int id) {
        Employe employe = null;
        Optional<Employe> optionalEmploye = employeRepository.findById(id);
        if(optionalEmploye.isPresent())
        {
            employe = optionalEmploye.get();
        }
        else
        {
            throw new NotFoundException("L'employé avec l'Id " + id + " n'existe pas.");
        }
        return employe;
    }

    /**
     * Méthode deleteEmployeById pour supprimer un employe par son Id
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteEmployeById(int id) {
        Employe employe = null;
        Optional<Employe> optionalEmploye = employeRepository.findById(id);
        if(optionalEmploye.isPresent())
        {
            employeRepository.deleteById(id);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Méthode findAll pour chercher tous les employés
     *
     * @return
     */
    @Override
    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    /**
     * Méthode updateNomEmployeById pour mettre à jour le nom d'un employe en utilisant son Id
     *
     * @param id
     * @param newNom
     * @return
     */
    @Override
    public Employe updateNomEmployeById(int id, String newNom) {
        Optional<Employe> optionalEmploye = employeRepository.findById(id);
        if (optionalEmploye.isPresent()) {
            Employe employe = optionalEmploye.get();
            employe.setNom(newNom);
            return employeRepository.save(employe);
        }
        return null;
    }

    /**
     * @param email
     * @return
     */
    @Override
    public boolean isEmailAlreadyInUse(String email) {
        Optional<Employe> existingEmploye = employeRepository.findByEmail(email);
        return existingEmploye.isPresent();
    }
}
