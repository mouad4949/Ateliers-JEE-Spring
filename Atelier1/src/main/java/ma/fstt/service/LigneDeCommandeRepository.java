package ma.fstt.service;

import ma.fstt.entities.LigneDeCommande;

import java.util.List;

public interface LigneDeCommandeRepository {
    void ajouterLigneDeCommande(LigneDeCommande ligneDeCommande);
    List<LigneDeCommande> obtenirLignesParCommande(int commandeId) ;
    void mettreAJourLigneDeCommande(LigneDeCommande ligne);
    void supprimerLigneDeCommande(int commandeId, int produitId);
}
