package com.example.projet_hr.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe Departement qui sera la table département dans la Base de donnee
 */
@Entity
@Getter
@Setter
@ToString(exclude = {"employees", "jobs"})
@NoArgsConstructor
@AllArgsConstructor
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String nomDepartement;
    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
    private List<Employe> employes;
    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
    private List<Emploi> emplois;
    /**
     * Méthode ajouterEmploye() pour ajouter un employé sur la liste des employés
     * @param employe
     */
    public void ajouterEmploye(Employe employe)
    {
        if(employes == null)
        {
            employes = new ArrayList<>();
        }
        employes.add(employe);
    }
    /**
     * Méthode ajouterEmploi() pour ajouter un emploi sur la liste des jobs
     * @param emploi
     */
    public void ajouterEmploi(Emploi emploi)
    {
        if(emplois == null)
        {
            emplois = new ArrayList<>();
        }
        emplois.add(emploi);
    }

    @ModelAttribute("departement")
    public Departement getDepartement() {
        return new Departement();
    }
}