package ma.fstt.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.dao.ProduitDao;
import ma.fstt.entities.Produit;

import java.io.IOException;
import java.util.List;

@WebServlet("/produits")
public class ProduitServlet extends HttpServlet {

    private ProduitDao produitDao;

    @Override
    public void init() throws ServletException {
        produitDao = new ProduitDao(); // Initialisation du DAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            // Récupérer tous les produits
            List<Produit> produits = produitDao.obtenirTousLesProduits();
            request.setAttribute("produits", produits); // Ajouter les produits comme attribut de requête
            request.getRequestDispatcher("/WEB-INF/views/Produits.jsp").forward(request, response);
        } else if ("edit".equals(action)) {
            // Récupérer un produit pour modification
            int id = Integer.parseInt(request.getParameter("id"));
            Produit produit = produitDao.obtenirTousLesProduits().stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .orElse(null);
            request.setAttribute("produit", produit);
            request.getRequestDispatcher("/WEB-INF/views/edit-produit.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            // Supprimer un produit
            int id = Integer.parseInt(request.getParameter("id"));
            produitDao.supprimerProduit(id);
            response.sendRedirect(request.getContextPath() + "/produits");
        } else if ("add".equals(action)) {
            // Afficher la page pour ajouter un produit
            request.getRequestDispatcher("/WEB-INF/views/add-produit.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            // Ajouter un nouveau produit
            String nom = request.getParameter("nom");
            int prix = Integer.parseInt(request.getParameter("prix"));
            int quantiteEnStock = Integer.parseInt(request.getParameter("quantite_en_stock"));
            Produit produit = new Produit(nom, prix, quantiteEnStock);
            produitDao.ajouterProduit(produit);
            response.sendRedirect(request.getContextPath() + "/produits");
        } else if ("update".equals(action)) {
            String idStr = request.getParameter("id");
            String nom = request.getParameter("nom");
            String prixStr = request.getParameter("prix");
            String quantiteStr = request.getParameter("quantite_en_stock");

            if (idStr == null || nom == null || prixStr == null || quantiteStr == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Données incomplètes");
                return;
            }

            try {
                int id = Integer.parseInt(idStr);
                int prix = Integer.parseInt(prixStr);
                int quantiteEnStock = Integer.parseInt(quantiteStr);

                Produit produit = new Produit(id, nom, prix, quantiteEnStock);
                produitDao.mettreAJourProduit(produit);
                response.sendRedirect(request.getContextPath() + "/produits");
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Format de nombre invalide");
                e.printStackTrace();
            }
        }

    }
}