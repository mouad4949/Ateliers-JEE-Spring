package com.example.rest_api.service;

import com.example.rest_api.entities.Absence;
import com.example.rest_api.repositories.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class AbsenceServiceImpl implements AbsenceService {
    @Autowired
    private AbsenceRepository absenceRepository;

    @Override
    public List<Absence> getAllAbsences() {
        return absenceRepository.findAll();
    }

    @Override
    public Absence getAbsenceById(Long id) {
        return absenceRepository.findById(id).orElse(null);
    }

    @Override
    public Absence saveAbsence(Absence absence) {
        return absenceRepository.save(absence);
    }

    @Override
    public void deleteAbsence(Long id) {
        absenceRepository.deleteById(id);
    }

    @Override
    public List<Absence> getAbsencesByEtudiant(Long etudiantId) {
        return absenceRepository.findByEtudiantId(etudiantId);
    }

    @Override
    public List<Absence> getAbsencesByPeriode(Date debut, Date fin) {
        return absenceRepository.findByDateAbsenceBetween(debut, fin);
    }
}