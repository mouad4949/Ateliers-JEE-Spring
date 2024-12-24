package com.example.rest_api.service;

import com.example.rest_api.entities.Etudiant;

import java.util.List;

public interface EtudiantService {
    List<Etudiant> getAllEtudiants();
    Etudiant getEtudiantById(Long id);
    Etudiant saveEtudiant(Etudiant etudiant);
    void deleteEtudiant(Long id);
    List<Etudiant> searchEtudiants(String keyword);
}