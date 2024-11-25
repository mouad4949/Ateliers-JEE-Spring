package ma.fstt.service;

import ma.fstt.entities.Produit;

import java.util.List;

public interface ProduitRepository {

    void ajouterProduit(Produit produit);
    void mettreAJourProduit(Produit produit);
    void supprimerProduit(int id);
    List<Produit> obtenirTousLesProduits();
}
