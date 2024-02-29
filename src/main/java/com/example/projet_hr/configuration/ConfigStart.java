package com.example.projet_hr.configuration;

import com.example.projet_hr.model.Departement;
import com.example.projet_hr.model.Emploi;
import com.example.projet_hr.model.Employe;
import com.example.projet_hr.repository.EmployeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Classe ConfigStart pour initialiser les BD avec des éléments
 */
@Component
public class ConfigStart implements CommandLineRunner
{
    /**
     * Injecter un employeRepository
     */
    private EmployeRepository employeRepository;

    /**
     * Déclarer un entitYManager pour commit
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Constructeur
     * @param employeRepository
     */
    public ConfigStart(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    /**
     * Méthode run pour exécuter le script
     * @param args
     * @throws Exception
     */
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (employeRepository.findAll().isEmpty())
        {
            createDepartementAndWithJobAndWithEmploye(employeRepository);
        }
    }

    /**
     * Méthode createEmployeWithJobs pour créer un département avec jobs et employés
     * @param employeRepository
     */
    private void createDepartementAndWithJobAndWithEmploye(EmployeRepository employeRepository)
    {
        Departement departement1 = new Departement();
        departement1.setNomDepartement("Comptabilité");
        Departement departement2 = new Departement();
        departement2.setNomDepartement("Informatique");
        Departement departement3 = new Departement();
        departement3.setNomDepartement("Logistique");

        // Créer un job associé un departement
        Emploi job1 = new Emploi();
        job1.setTitreEmploi("Comptable");
        job1.setDepartement(departement1);
        Emploi job2 = new Emploi();
        job2.setTitreEmploi("Programmeur");
        job2.setDepartement(departement2);
        Emploi job3 = new Emploi();
        job3.setTitreEmploi("Chauffeur");
        job3.setDepartement(departement3);


        // Créer un employe associé à un département et à un job
        Employe employe1 = new Employe("Eliot", "Mazzi", "marco@gmail.com", 6000,"15/06/2021");
        employe1.setDepartement(departement1);
        employe1.setEmploi(job1);
        Employe employe2 = new Employe("Jhon", "Nana", "nana@gmail.com", 4000, "01/04/2016");
        employe2.setDepartement(departement2);
        employe2.setEmploi(job2);
        Employe employe3 = new Employe("Pierre", "Joseph", "Joseph@gmail.com", 8500, "08/12/2019");
        employe3.setDepartement(departement3);
        employe3.setEmploi(job3);
        Employe employe4 = new Employe("Francis", "Martin", "martin@hotmail.com", 1950, "21/01/2024");
        employe4.setDepartement(departement2);
        employe4.setEmploi(job2);

        // Ajouter un job et un employe à un département
        departement1.ajouterEmploi(job1);
        departement1.ajouterEmploye(employe1);
        departement2.ajouterEmploi(job2);
        departement2.ajouterEmploye(employe2);
        departement2.ajouterEmploye(employe4);
        departement3.ajouterEmploi(job3);
        departement3.ajouterEmploye(employe3);

        // Sauvegarder tous les éléments avec confirmation
        entityManager.persist(departement1);
        entityManager.persist(job1);
        entityManager.persist(employe1);
        entityManager.persist(departement2);
        entityManager.persist(job2);
        entityManager.persist(employe2);
        entityManager.persist(departement3);
        entityManager.persist(job3);
        entityManager.persist(employe3);
        entityManager.persist(employe4);
    }
}