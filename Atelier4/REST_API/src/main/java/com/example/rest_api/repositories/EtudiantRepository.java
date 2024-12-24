package com.example.rest_api.repositories;


import com.example.rest_api.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    List<Etudiant> findByNomContainingOrPrenomContaining(String nom, String prenom);
    List<Etudiant> findByClasse(String classe);
}