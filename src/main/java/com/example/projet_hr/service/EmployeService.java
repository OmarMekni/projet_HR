package com.example.projet_hr.service;



import com.example.projet_hr.model.Employe;

import java.util.List;

public interface EmployeService {

    /**
     * Méthode saveEmploye pour enregistrer un employé dans la BD
     * @param employe
     * @return
     */
    Employe saveEmploye(Employe employe);

    /**
     * Méthode findEmployeById pour chercher un employe par son Id
     * @param id
     * @return
     */
    Employe findEmployeById(int id);

    /**
     * Méthode deleteEmployeById pour supprimer un employe par son Id
     * @param id
     * @return
     */
    boolean deleteEmployeById(int id);

    /**
     * Méthode findAll pour chercher tous les employés
     * @return
     */
    List<Employe> findAll();

    /**
     * Méthode updateNomEmployeById pour mettre à jour le nom d'un employe en utilisant son Id
     * @param id
     * @param newNom
     * @return
     */
    Employe updateNomEmployeById(int id, String newNom);

    boolean isEmailAlreadyInUse(String email);
}
