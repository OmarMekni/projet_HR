package com.example.projet_hr.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class Employe {
    /**
     * @Id qui donne la clé primaire PK dans la table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String prenom;
    @Column(nullable = false)
    private String nom;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private double salaire;
    @Column(nullable = false, name = "date_embauche")
    private String dateEmbauche;

    /**
     * Constructeur de la classe Employe
     * @param prenom
     * @param nom
     * @param email
     * @param dateEmbauche
     * @param salaire
     */
    public Employe(String prenom, String nom, String email,String dateEmbauche, double salaire) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.dateEmbauche = dateEmbauche;
        this.salaire = salaire;
    }

    /**
     * @ManyToOne(): Plusieurs employés peuvent avoir un même emploi
     * @JoinColumn(name = "id_emploi"): Colonne de jointure de l'employé au emploi
     */
    @ManyToOne()
    @JoinColumn(name = "id_emploi")
    private Emploi emploi;
    /**
     * @ManyToOne(): Plusieurs employés peuvent être dans un même département
     * @JoinColumn(name = "departement_id") : Colonne de jointure de l'employé au département
     */
    @ManyToOne()
    @JoinColumn(name = "id_departement")
    private Departement departement ;
    public Employe(String prenom, String nom, String email, double salaire, String dateEmbauche) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.salaire = salaire;
        this.dateEmbauche = dateEmbauche;
    }
}
