package com.example.projet_hr.service;




import com.example.projet_hr.model.Emploi;

import java.util.List;
public interface EmploiService {
    /**
     * Méthode saveEmploi() qui sert à inserrer un emploi à la Base de données
     * @param emploi
     * @return : emploi
     */
    Emploi saveEmploi(Emploi emploi);
    /**
     * Méthode findEmploiById qui permet de chercher un emploi par son Id
     * @param id
     * @return : emploi
     */
    Emploi findEmploiById(int id);
    /**
     * Méthode deleteEmploiById qui permet de supprimer un emploi par son Id
     * @param id
     * @return
     */
    boolean deleteEmploiById(int id);
    /**
     * Méthode findAll pour chercher tous les emplois
     * @return
     */
    List<Emploi> findAll();
    /**
     * Méthode updateTitreEmploiById pour mettre à jour le titre d'un emploi en utilisant son Id
     * @param id
     * @param newTitreEmploi
     * @return
     */
    Emploi updateTitreEmploiById(int id, String newTitreEmploi);
    /**
     * Méthode findEmploiByTitreEmploi pour rechercher un emploi par son titre
     * @param titreEmploi
     * @return
     */
    Emploi findEmploiByTitreEmploi(String titreEmploi);
    List<Emploi> getAllEmplois();
}

