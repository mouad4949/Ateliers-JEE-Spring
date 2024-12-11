package entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Etudinat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id_etudiant")
    private Long id;

    @JoinColumn(name = "nom")
    private String nom;


    @JoinColumn(name = "prenom")
    private String prenom;

    @JoinColumn(name = "cne")
    private String cne;

    @JoinColumn(name = "adresse")
    private String adresse;

    @JoinColumn(name = "niveau")
    private String niveau;






}
