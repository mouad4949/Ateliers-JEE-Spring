package container;


import entity.Etudinat;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface ProcessEtudiantEJBRemote {
    public void addEtudiant(Etudinat e);
    public Boolean deleteEtudiant(Long id);
    public Etudinat getById(Long id);

    public List<Etudinat> getAllEtudiants();

    public void update(Etudinat e);
}
