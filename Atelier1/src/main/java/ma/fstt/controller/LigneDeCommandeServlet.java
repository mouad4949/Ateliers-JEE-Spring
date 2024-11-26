package ma.fstt.controller;

import ma.fstt.entities.LigneDeCommande;
import ma.fstt.entities.Commande;
import ma.fstt.entities.Produit;
import ma.fstt.service.LigneDeCommandeRepository;
import ma.fstt.dao.LigneDeCommandeDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
@WebServlet("/lignes-commande")
public class LigneDeCommandeServlet extends HttpServlet {

    private LigneDeCommandeRepository ligneDeCommandeRepository;

    @Override
    public void init() throws ServletException {
        ligneDeCommandeRepository = new LigneDeCommandeDao(); // Initialisation du repository
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String commandeIdStr = request.getParameter("commande_id");

        if (commandeIdStr == null || commandeIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Le paramètre commande_id est manquant.");
            return;
        }

        int commandeId;
        try {
            commandeId = Integer.parseInt(commandeIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Le paramètre commande_id n'est pas valide.");
            return;
        }

        if ("add".equals(action)) {
            // Redirection vers le formulaire d'ajout
            request.setAttribute("commande_id", commandeId);
            request.getRequestDispatcher("/WEB-INF/views/ajouter-ligne-commande.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            int produitId = Integer.parseInt(request.getParameter("produit_id"));
            ligneDeCommandeRepository.supprimerLigneDeCommande(commandeId, produitId);
            response.sendRedirect(request.getContextPath() + "/lignes-commande?commande_id=" + commandeId);
        } else {
            // Action nulle ou autre => afficher les lignes de commande
            List<LigneDeCommande> lignes = ligneDeCommandeRepository.obtenirLignesParCommande(commandeId);
            request.setAttribute("lignes", lignes);
            request.getRequestDispatcher("/WEB-INF/views/lignes-commande.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Récupération des données du formulaire
            int commandeId = Integer.parseInt(request.getParameter("commande_id"));
            int produitId = Integer.parseInt(request.getParameter("produit_id"));
            int quantite = Integer.parseInt(request.getParameter("quantite"));
            double prixTotal = Double.parseDouble(request.getParameter("prix_total"));

            // Création de la commande et du produit
            Commande commande = new Commande();
            commande.setId(commandeId);

            Produit produit = new Produit();
            produit.setId(produitId);

            // Création de la ligne de commande
            LigneDeCommande ligne = new LigneDeCommande(commande, produit, quantite, prixTotal);

            // Ajout de la ligne de commande à la base de données
            ligneDeCommandeRepository.ajouterLigneDeCommande(ligne);

            // Redirection vers la liste des lignes de commande
            response.sendRedirect(request.getContextPath() + "/lignes-commande?commande_id=" + commandeId);
        }
    }
}
