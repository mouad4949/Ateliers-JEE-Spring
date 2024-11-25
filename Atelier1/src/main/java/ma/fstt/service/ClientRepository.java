package ma.fstt.service;

import ma.fstt.entities.Client;

import java.util.List;

public interface ClientRepository {
    Client trouverById(String id);
    void ajouterClient(Client client);
    void supprimerClient(String id);
    void modifierClient(Client client);
    List<Client> findAll();
}
