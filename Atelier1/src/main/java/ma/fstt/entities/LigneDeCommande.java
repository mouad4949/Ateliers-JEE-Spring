package ma.fstt.entities;

public class LigneDeCommande {

    private Commande commande;
    private Produit produit;
    private int quantite;
    private double sousTotal;

    // Constructeur
    public LigneDeCommande() {}

    public LigneDeCommande(Commande commande, Produit produit, int quantite, double sousTotal) {
        this.commande = commande;
        this.produit = produit;
        this.quantite = quantite;
        this.sousTotal = sousTotal;
    }

    // Getters et Setters
    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getSousTotal() {
        return sousTotal;
    }

    public void setSousTotal(double sousTotal) {
        this.sousTotal = sousTotal;
    }
}
