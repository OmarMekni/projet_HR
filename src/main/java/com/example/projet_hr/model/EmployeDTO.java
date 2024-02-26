package com.example.projet_hr.model;


import lombok.*;

/**
 * Classe EmployeDTO est une copie de la classe Employe qui permet de transmettre des données entre le client
 * et le serveur.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeDTO
{
    private int id;
    private String prenom;
    private String nom;
    private String email;
    private double salaire;
    private String dateEmbauche;
    private String titreEmploi;
    private String nomDepartement;

}
