package ma.fstt.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Data;
import ma.fstt.persistance.Client;

import java.util.List;
@Named
@RequestScoped
@Data
public class ClientsBeans {
    private String nom;
    private String addresse;
    private String telephone;

    private EntityManagerFactory emf ;

    private EntityManager em ;

    public ClientsBeans(){
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }


    public void save(){
        Client clt = new Client();
        clt.setNom(nom);
        clt.setTelephone(telephone);
        clt.setAddresse(addresse);

        em.getTransaction().begin();
        System.out.println("COMIITING");
        em.persist(clt);
        em.getTransaction().commit();

        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "listClients.xhtml");

        //#return "listEtudiants.xhtml?faces-redirect=true";

    }

    public List<Client> lister (){

        List<Client> malist  = em.createQuery(" select clt from Client as clt").getResultList();

        return malist ;
    }
    public void supprimer(Client clt) {
        em.getTransaction().begin();
        clt = em.merge(clt); // Assure que l'entité est gérée
        em.remove(clt);
        em.getTransaction().commit();
    }

    

}
