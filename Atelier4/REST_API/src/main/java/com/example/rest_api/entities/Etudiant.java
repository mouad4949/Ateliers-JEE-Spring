package com.example.rest_api.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String classe;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    @JsonManagedReference // Manages the forward serialization
    private List<Absence> absences;
}
