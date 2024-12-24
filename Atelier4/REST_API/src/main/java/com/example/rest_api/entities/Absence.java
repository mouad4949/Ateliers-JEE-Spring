package com.example.rest_api.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date dateAbsence;

    private String justification;
    private Boolean justified;
    private String description;

    @ManyToOne
    @JsonBackReference // Ignores the back-reference during serialization
    private Etudiant etudiant;
}