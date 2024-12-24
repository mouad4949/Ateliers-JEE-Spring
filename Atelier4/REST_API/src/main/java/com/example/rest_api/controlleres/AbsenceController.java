package com.example.rest_api.controlleres;

import com.example.rest_api.entities.Absence;
import com.example.rest_api.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/absences")

public class AbsenceController {
    @Autowired
    private AbsenceService absenceService;

    @GetMapping
    public List<Absence> getAllAbsences() {
        return absenceService.getAllAbsences();
    }

    @GetMapping("/{id}")
    public Absence getAbsenceById(@PathVariable Long id) {
        return absenceService.getAbsenceById(id);
    }

    @PostMapping
    public Absence createAbsence(@RequestBody Absence absence) {
        return absenceService.saveAbsence(absence);
    }

    @PutMapping("/{id}")
    public Absence updateAbsence(@PathVariable Long id, @RequestBody Absence absence) {
        absence.setId(id);
        return absenceService.saveAbsence(absence);
    }

    @DeleteMapping("/{id}")
    public void deleteAbsence(@PathVariable Long id) {
        absenceService.deleteAbsence(id);
    }

    @GetMapping("/etudiant/{etudiantId}")
    public List<Absence> getAbsencesByEtudiant(@PathVariable Long etudiantId) {
        return absenceService.getAbsencesByEtudiant(etudiantId);
    }
}