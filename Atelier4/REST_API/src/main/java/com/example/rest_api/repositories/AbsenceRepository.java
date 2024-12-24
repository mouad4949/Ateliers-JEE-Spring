package com.example.rest_api.repositories;

import com.example.rest_api.entities.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    List<Absence> findByEtudiantId(Long etudiantId);
    List<Absence> findByDateAbsenceBetween(Date debut, Date fin);
}