package ma.fstt.dao;

import ma.fstt.entities.Produit;
import ma.fstt.service.ProduitRepository;
import ma.fstt.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDao implements ProduitRepository {

    private Connection connection;

    public ProduitDao() {
        this.connection= DatabaseUtils.getConnection();
    }

    @Override
    public void mettreAJourProduit(Produit produit) {
        String query = "UPDATE Produit SET nom = ?, prix = ?, quantite_en_stock = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, produit.getNom());
            stmt.setInt(2, produit.getPrix());
            stmt.setInt(3, produit.getQuantite_en_stock());
            stmt.setInt(4, produit.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ajouterProduit(Produit produit) {
        String query = "INSERT INTO Produit (nom, prix, quantite_en_stock) VALUES (?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, produit.getNom());
            stmt.setInt(2, produit.getPrix());
            stmt.setInt(3, produit.getQuantite_en_stock());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Produit> obtenirTousLesProduits(){
        String query = "SELECT * FROM Produit";
        List<Produit> produits = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                produits.add(new Produit(rs.getInt("id"), rs.getString("nom"), rs.getInt("prix"), rs.getInt("quantite_en_stock")));
            }
            System.out.println("Nombre de clients trouvés : " + produits.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produits;
    }

    @Override
    public void supprimerProduit(int id){
        String query = "DELETE FROM Produit WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
