package com.example.projet_hr.model;

import com.example.projet_hr.model.Departement;
import com.example.projet_hr.model.Employe;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Classe Emploi qui sera la table départment dans la BD
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Emploi {
    /**
     * @Id qui donne la clé primaire PK dans la table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true, name = "titre_emploi")
    private String titreEmploi;
    @OneToMany(mappedBy = "emploi", cascade = CascadeType.ALL)
    private List<Employe> employes;

    /**
     * @ManyToOne(): Plusieurs emplois peuvent être dans le même departement
     * @JoinColumn(name = "emploi_id"): Colonne de jointure du emploi au departement
     */
    @ManyToOne()
    @JoinColumn(name = "id_departement")
    private Departement departement;

    /**
     * Méthode get(): pour récupérer un département
     * @return : retourner un département
     */
    public Departement getDepartement() {
        return departement;
    }

    /**
     * Méthode set(): pour modifier un département
     * @param departement
     */
    public void setDepartement(Departement departement) {
        this.departement = departement;
    }


}
