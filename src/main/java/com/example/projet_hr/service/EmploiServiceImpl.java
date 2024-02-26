package com.example.projet_hr.service;

import com.example.projet_hr.exeption.NotFoundException;
import com.example.projet_hr.model.Emploi;
import com.example.projet_hr.repository.EmploiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class EmploiServiceImpl implements EmploiService {
    /**
     * @Autowired permet de créer un nouvel objet sans utilisation new
     */
    @Autowired
    private EmploiRepository emploiRepository;
    /**
     * Méthode saveEmploi() qui sert à inserrer un emploi à la Base de données
     *
     * @param emploi
     * @return : emploi
     */
    @Override
    public Emploi saveEmploi(Emploi emploi) {
        return emploiRepository.save(emploi);
    }
    /**
     * Méthode findEmploiById qui permet de chercher un emploi par son Id
     *
     * @param id
     * @return : emploi
     */
    @Override
    public Emploi findEmploiById(int id) {
        Emploi emploi = null;
        Optional<Emploi> optionalEmploi = emploiRepository.findById(id);
        if(optionalEmploi.isPresent())
        {
            emploi = optionalEmploi.get();
        }
        else
        {
            throw new NotFoundException("L'emploi avec l'Id " + id + " n'existe pas.");
        }
        return emploi;
    }
    /**
     * Méthode deleteEmploiById qui permet de supprimer un emploi par son Id
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteEmploiById(int id) {
        Emploi emploi = null;
        Optional<Emploi> optionalEmploi = emploiRepository.findById(id);
        if(optionalEmploi.isPresent())
        {
            emploiRepository.deleteById(id);
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * Méthode findAll pour chercher tous les emplois
     *
     * @return
     */
    @Override
    public List<Emploi> findAll() {
        return emploiRepository.findAll();
    }
    /**
     * Méthode updateTitreEmploiById pour mettre à jour le titre d'un emploi en utilisant son Id
     *
     * @param id
     * @param newTitreEmploi
     * @return
     */
    @Override
    public Emploi updateTitreEmploiById(int id, String newTitreEmploi) {
        Emploi emploi = findEmploiById(id);
        emploi.setTitreEmploi(newTitreEmploi);
        return emploiRepository.save(emploi);
    }
    /**
     * Méthode findEmploiByTitreEmploi pour rechercher un emploi par son titre
     *
     * @param titreEmploi
     * @return
     */
    @Override
    public Emploi findEmploiByTitreEmploi(String titreEmploi) {
        Optional<Emploi> optionalEmploi = emploiRepository.findByTitreEmploi(titreEmploi);
        return optionalEmploi.orElse(null);
    }
    /**
     * @return
     */
    @Override
    public List<Emploi> getAllEmplois() {
        return emploiRepository.findAll();
    }

}
