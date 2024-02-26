package com.example.projet_hr.repository;


import com.example.projet_hr.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {

    /**
     * Optionnel<Employe> permet de retourner une valeur non null
     * Methode findByName pour rechercher un departement par son  nom
     * @param nomDepartement
     * @return
     */
    Optional<Departement> findByNomDepartement(String nomDepartement);

//    Optional<Departement> findByName(String name);
//    Optional<Departement> findDepartementByName(String name);
}
