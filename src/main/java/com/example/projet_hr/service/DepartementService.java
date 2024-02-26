package com.example.projet_hr.service;



import com.example.projet_hr.model.Departement;

import java.util.List;
public interface DepartementService {
    /**
     * Méthode saveDepartement pour enregistrer un département dans la BD
     * @param departement
     * @return
     */
    Departement saveDepartement(Departement departement);
    /**
     * Méthode findDepartementById pour chercher un département par son Id
     * @param id
     * @return
     */
    Departement findDepartementById(int id);
    /**
     * Méthode deleteDepartementById pour supprimer un département de la liste des départements par son Id
     * @param id
     * @return
     */
    boolean deleteDepartementById(int id);
    /**
     * Méthode updateDepartementNameById pour mettre à jour le nom d'un département en utilisant son Id
     * @param id
     * @param newNomDepartement
     * @return
     */
    Departement updateDepartementNameById(int id, String newNomDepartement);

    /**
     * Méthode pour findAll chercher tous les départements de la liste
     * @return
     */
    List<Departement> findAll();

    /**
     * Méthode findDepartementByName pour chercher un département par son nom
     * @param name
     * @return
     */
    Departement findDepartementByName(String name);
    boolean departmentExists(int departementId);

}