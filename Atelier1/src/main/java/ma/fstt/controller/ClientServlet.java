package ma.fstt.controller;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.dao.ClientDao;
import ma.fstt.entities.Client;

import java.io.IOException;
import java.util.List;

@WebServlet("/clients")
public class ClientServlet extends HttpServlet {

    private ClientDao clientDAO;
    @Override
    public void init() throws ServletException {
        clientDAO = new ClientDao(); // Initialisation manuelle du DAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            // Récupérer tous les clients
            List<Client> clients = clientDAO.findAll();
            request.setAttribute("clients", clients); // Ajouter l'attribut "clients" à la requête
            request.getRequestDispatcher("/WEB-INF/views/clients.jsp").forward(request, response);
        } else if ("edit".equals(action)) {
            // Modifier un client
            String id = request.getParameter("id"); // Récupérer l'ID depuis le paramètre
            Client client = clientDAO.trouverById(id); // Récupérer le client correspondant
            request.setAttribute("client", client);   // Passer le client dans la requête
            request.getRequestDispatcher("/WEB-INF/views/edit-client.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            // Supprimer un client
            String id = request.getParameter("id");
            clientDAO.supprimerClient(id);
            response.sendRedirect(request.getContextPath() + "/clients");
        } else if ("add".equals(action)) {
            // Afficher la page d'ajout
            request.getRequestDispatcher("/WEB-INF/views/add-client.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Ajouter un client
            String nom = request.getParameter("nom");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String telephone = request.getParameter("telephone");

            // Debug pour vérifier les valeurs reçues
            System.out.println("Nom: " + nom);
            System.out.println("Email: " + email);
            System.out.println("Address: " + address);
            System.out.println("Telephone: " + telephone);

            Client client = new Client(null, nom, email, address, telephone);
            clientDAO.ajouterClient(client); // Appelle le DAO pour l'insertion
            response.sendRedirect(request.getContextPath() + "/clients");
        }
        else if ("update".equals(action)) {
            // Modifier un client
            String id = request.getParameter("id"); // Récupérer l'ID transmis
            String nom = request.getParameter("nom");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String telephone = request.getParameter("telephone");

            // Créer un objet client avec les nouvelles valeurs
            Client client = new Client(id, nom, email, address, telephone);
            clientDAO.modifierClient(client); // Appeler la méthode de modification dans le DAO

            response.sendRedirect(request.getContextPath() + "/clients"); // Rediriger vers la liste des clients
        }
    }
}
