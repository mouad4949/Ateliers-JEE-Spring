package ma.fstt.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.dao.ClientDao;
import ma.fstt.dao.CommandeDao;
import ma.fstt.entities.Commande;
import ma.fstt.entities.Client;
import ma.fstt.service.ClientRepository;
import ma.fstt.service.CommandeRepository;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/commandes")
public class CommandeServlet extends HttpServlet {
    private ClientRepository clientDao;
    private CommandeRepository commandeDao;

    @Override
    public void init() throws ServletException {
        commandeDao = new CommandeDao();
        clientDao = new ClientDao();// Initialisation du DAO pour Commande
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            // Afficher toutes les commandes
            List<Commande> commandes = commandeDao.obtenirToutesLesCommandes();
            request.setAttribute("commandes", commandes);
            request.getRequestDispatcher("/WEB-INF/views/commandes.jsp").forward(request, response);
        } else if ("edit".equals(action)) {
            // Modifier une commande
            int commandeId = Integer.parseInt(request.getParameter("id"));
            Commande commande = commandeDao.obtenirCommandeParId(commandeId);

            // Récupérer la liste des clients
            List<Client> clients = clientDao.findAll();

            // Ajouter les objets à la requête pour les passer à la JSP
            request.setAttribute("commande", commande);  // Assurez-vous que cet attribut est bien passé
            request.setAttribute("clients", clients);

            // Redirection vers la JSP pour modifier la commande
            request.getRequestDispatcher("/WEB-INF/views/edit-commande.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            // Supprimer une commande
            int id = Integer.parseInt(request.getParameter("id"));
            commandeDao.supprimerCommande(id);
            response.sendRedirect(request.getContextPath() + "/commandes");
        } else if ("add".equals(action)) {
            // Récupérer la liste des clients existants
            List<Client> clients = clientDao.findAll();
            request.setAttribute("clients", clients); // Passer les clients à la vue
            request.getRequestDispatcher("/WEB-INF/views/add-commande.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Ajouter une nouvelle commande
            String dateStr = request.getParameter("date");
            String clientId = request.getParameter("client_id");
            Client client = new Client();
            client.setId(clientId); // Vous pouvez récupérer plus de données du client si nécessaire

            Commande commande = new Commande();
            commande.setDate(Date.valueOf(dateStr));
            commande.setClient(client);

            commandeDao.ajouterCommande(commande);
            response.sendRedirect(request.getContextPath() + "/commandes");

        } else if ("update".equals(action)) {
            // Mettre à jour une commande existante
            int id = Integer.parseInt(request.getParameter("id"));
            String dateStr = request.getParameter("date");
            String clientId = request.getParameter("client_id");
            Client client = new Client();
            client.setId(clientId);

            Commande commande = new Commande();
            commande.setId(id);
            commande.setDate(Date.valueOf(dateStr));
            commande.setClient(client);

            commandeDao.mettreAJourCommande(commande);
            response.sendRedirect(request.getContextPath() + "/commandes");
        }
    }
}
