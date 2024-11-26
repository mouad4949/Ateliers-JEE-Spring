package ma.fstt.dao;

import ma.fstt.entities.LigneDeCommande;
import ma.fstt.entities.Commande;
import ma.fstt.entities.Produit;
import ma.fstt.service.LigneDeCommandeRepository;
import ma.fstt.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LigneDeCommandeDao implements LigneDeCommandeRepository {

    private Connection connection;

    public LigneDeCommandeDao() {
        // Assurez-vous de fournir une connexion à la base de données
        this.connection = DatabaseUtils.getConnection();
    }

    // Ajouter une ligne de commande
    @Override
    public void ajouterLigneDeCommande(LigneDeCommande ligne) {
        String sql = "INSERT INTO ligne_commande (commande_id, produit_id, quantite, prix_total) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ligne.getCommande().getId());
            statement.setInt(2, ligne.getProduit().getId());
            statement.setInt(3, ligne.getQuantite());
            statement.setDouble(4, ligne.getSousTotal());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obtenir toutes les lignes de commande pour une commande donnée
    @Override
    public List<LigneDeCommande> obtenirLignesParCommande(int commandeId) {
        List<LigneDeCommande> lignes = new ArrayList<>();
        String sql = "SELECT * FROM ligne_commande WHERE commande_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, commandeId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Commande commande = new Commande(); // Assurez-vous que vous avez une méthode pour récupérer une commande par ID
                commande.setId(resultSet.getInt("commande_id"));

                Produit produit = new Produit(); // Idem pour le produit
                produit.setId(resultSet.getInt("produit_id"));

                int quantite = resultSet.getInt("quantite");
                double prixTotal = resultSet.getDouble("prix_total");

                LigneDeCommande ligne = new LigneDeCommande(commande, produit, quantite, prixTotal);
                lignes.add(ligne);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lignes;
    }

    // Mettre à jour une ligne de commande
    @Override
    public void mettreAJourLigneDeCommande(LigneDeCommande ligne) {
        String sql = "UPDATE ligne_commande SET quantite = ?, prix_total = ? WHERE commande_id = ? AND produit_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ligne.getQuantite());
            statement.setDouble(2, ligne.getSousTotal());
            statement.setInt(3, ligne.getCommande().getId());
            statement.setInt(4, ligne.getProduit().getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer une ligne de commande
    @Override
    public void supprimerLigneDeCommande(int commandeId, int produitId) {
        String sql = "DELETE FROM ligne_commande WHERE commande_id = ? AND produit_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, commandeId);
            statement.setInt(2, produitId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
