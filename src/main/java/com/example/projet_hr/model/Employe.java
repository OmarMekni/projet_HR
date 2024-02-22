package com.example.projet_hr.model;

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
    private Date dateEmbauche;

    Dispose d’un menu contextuel
            Composer


}
