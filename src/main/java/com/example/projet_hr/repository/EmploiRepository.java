package com.example.projet_hr.repository;

import com.example.projet_de_synthese_rh.model.Emploi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmploiRepository extends JpaRepository<Emploi, Integer> {

    /**
     * Methode findByTitle pour rechercher un job par son titre
     * @return : Emploi
     */
//    Optional<Emploi> findByTitle(String titreEmploi);
    Optional<Emploi> findByTitreEmploi(String titreEmploi);
}
