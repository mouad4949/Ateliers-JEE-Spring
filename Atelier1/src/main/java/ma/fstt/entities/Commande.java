package ma.fstt.entities;

import java.util.Date;
import java.util.List;

public class Commande {

    private int id;
    private Date date;
    private Client client;
    private List<LigneDeCommande> lignesDeCommande;

    // Constructeur
    public Commande() {}

    public Commande(Date date, Client client) {
        this.date = date;
        this.client = client;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<LigneDeCommande> getLignesDeCommande() {
        return lignesDeCommande;
    }

    public void setLignesDeCommande(List<LigneDeCommande> lignesDeCommande) {
        this.lignesDeCommande = lignesDeCommande;
    }
}
