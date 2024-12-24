package com.example.rest_api.service;

import com.example.rest_api.entities.Absence;

import java.util.Date;
import java.util.List;

public interface AbsenceService {
    List<Absence> getAllAbsences();
    Absence getAbsenceById(Long id);
    Absence saveAbsence(Absence absence);
    void deleteAbsence(Long id);
    List<Absence> getAbsencesByEtudiant(Long etudiantId);
    List<Absence> getAbsencesByPeriode(Date debut, Date fin);
}