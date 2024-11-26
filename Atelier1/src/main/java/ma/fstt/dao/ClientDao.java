package ma.fstt.dao;

import jakarta.enterprise.context.ApplicationScoped;
import ma.fstt.entities.Client;
import ma.fstt.service.ClientRepository;
import ma.fstt.utils.DatabaseUtils;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ClientDao implements ClientRepository {
    private Connection connection;

    public ClientDao() {
        // obtenir une connexion JDBC
        this.connection = DatabaseUtils.getConnection();
    }

    @Override
    public Client trouverById(String id) {
        // Exécuter une requête SQL pour récupérer un client
        String query = "SELECT * FROM Client WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Client(rs.getString("id"), rs.getString("nom"), rs.getString("email"),rs.getString("address"),rs.getString("telephone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Client> findAll() {
        String query = "SELECT * FROM Client";
        List<Client> clients = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                clients.add(new Client(rs.getString("id"), rs.getString("nom"), rs.getString("email"), rs.getString("address"), rs.getString("telephone")));
            }
            System.out.println("Nombre de clients trouvés : " + clients.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }


    public void ajouterClient(Client client) {
        String query = "INSERT INTO Client (nom, email, address, telephone) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getEmail());
            stmt.setString(3, client.getAddress());
            stmt.setString(4, client.getTelephone());

            // Debug pour vérifier si l'exécution passe ici
            System.out.println("Exécution de la requête : " + query);

            stmt.executeUpdate(); // Exécution de l'insertion
        } catch (SQLException e) {
            e.printStackTrace(); // Log de l'exception
        }
    }

    @Override
    public void supprimerClient(String id) {
        String query = "DELETE FROM Client WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifierClient(Client client) {
        String query = "UPDATE Client SET nom = ?, email = ?, address = ?, telephone = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getEmail());
            stmt.setString(3, client.getAddress());
            stmt.setString(4, client.getTelephone());
            stmt.setString(5, client.getId());

            stmt.executeUpdate(); // Exécute la mise à jour
        } catch (SQLException e) {
            e.printStackTrace(); // Log en cas d'erreur
        }
    }



}
