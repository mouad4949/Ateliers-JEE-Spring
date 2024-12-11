package container;

import entity.Etudinat;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;


@Transactional
@Stateless
public class ProcessEtudiantEJBImpl implements ProcessEtudiantEJBRemote,ProcessEtudiantEJBImplLocal{
    @PersistenceContext(unitName = "Etudiant")
    private EntityManager entityManager;
    @Override
    public void addEtudiant(Etudinat e) {
        entityManager.persist(e);

    }

    @Override
    public Boolean deleteEtudiant(Long id) {
        Etudinat etudiant = entityManager.find(Etudinat.class, id);
        if (etudiant != null) {
            entityManager.remove(etudiant);
            return true;
        }
        return false;
    }

    @Override
    public Etudinat getById(Long id) {
        return entityManager.find(Etudinat.class, id);
    }

    @Override
    public List<Etudinat> getAllEtudiants() {
        return entityManager.createQuery("SELECT e FROM Etudinat e", Etudinat.class).getResultList();

    }

    @Override
    public void update(Etudinat e) {
        entityManager.merge(e);
    }
}
