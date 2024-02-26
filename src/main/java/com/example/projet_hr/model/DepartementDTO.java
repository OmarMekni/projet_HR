package com.example.projet_hr.model;

import lombok.*;
/**
 * Classe DepartementDTO est une copie de la classe Departement qui permet de transmettre des données entre le client
 * et le serveur.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DepartementDTO
{
    private int id;
    private String nomDepartement;

}
