package com.example.projet_hr.repository;


import com.example.projet_hr.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeRepository extends JpaRepository<Employe, Integer> {
    /**
     * Optionnel<Employe> permet de retourner une valeur non null
     * Methode findByEmail pour rechercher un employe par son email
     * @param email
     * @return
     */
    Optional<Employe> findByEmail(String email);

}
