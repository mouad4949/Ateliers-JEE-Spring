package container;


import entity.Etudinat;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface ProcessEtudiantEJBImplLocal {
    public void addEtudiant(Etudinat e);
    public Boolean deleteEtudiant(Long id);
    public Etudinat getById(Long id);

    public List<Etudinat> getAllEtudiants();

    public void update(Etudinat e);
}
