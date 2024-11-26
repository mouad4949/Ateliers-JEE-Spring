package ma.fstt.dao;

import ma.fstt.entities.Client;
import ma.fstt.entities.Commande;
import ma.fstt.service.CommandeRepository;
import ma.fstt.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommandeDao implements CommandeRepository {
    private Connection connection;

    public CommandeDao() {
        this.connection = DatabaseUtils.getConnection();
    }

    @Override
    public void ajouterCommande(Commande commande) {
        String query = "INSERT INTO Commande (date, client_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(commande.getDate().getTime()));
            stmt.setString(2, commande.getClient().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mettreAJourCommande(Commande commande) {
        String query = "UPDATE Commande SET date = ?, client_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(commande.getDate().getTime()));
            stmt.setString(2, commande.getClient().getId());
            stmt.setInt(3, commande.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerCommande(int id) {
        String query = "DELETE FROM Commande WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Commande> obtenirToutesLesCommandes() {
        String query = "SELECT * FROM Commande";
        List<Commande> commandes = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                Date date = rs.getDate("date");
                Client client = new Client();  // Vous devez récupérer le client à partir de sa table
                client.setId(rs.getString("client_id"));
                Commande commande = new Commande(date, client);
                commande.setId(id);
                commandes.add(commande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandes;
    }
    @Override
    public Commande obtenirCommandeParId(int id) {
        String query = "SELECT * FROM Commande WHERE id = ?";
        Commande commande = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Date date = rs.getDate("date");
                Client client = new Client();  // Vous devez récupérer le client à partir de sa table
                client.setId(rs.getString("client_id"));
                commande = new Commande(date, client);
                commande.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commande;
    }
}
