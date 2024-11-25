package ma.fstt.entities;

public class Produit {
    private int id;
    private String nom;
    private int prix;
    private int quantite_en_stock;

    public Produit(int id, String nom, int prix, int quantite_en_stock) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantite_en_stock = quantite_en_stock;
    }

    public Produit() {
    }

    public Produit(String nom, int prix, int quantite_en_stock) {
        this.nom = nom;
        this.prix = prix;
        this.quantite_en_stock = quantite_en_stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQuantite_en_stock() {
        return quantite_en_stock;
    }

    public void setQuantite_en_stock(int quantite_en_stock) {
        this.quantite_en_stock = quantite_en_stock;
    }
}
