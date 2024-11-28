package ma.fstt.persistance;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Produit")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private Double prix;
    private int stock;
}
