package com.example.projet_hr.model;

/**
 * Classe EmploiDTO est une copie de la classe Emploi qui permet de transmettre des données entre le client
 * et le serveur.
 */
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmploiDTO {

    private int id;
    private String titreEmploi;
    private String nomDepartement;

}
