<%--
  Created by IntelliJ IDEA.
  User: mouad
  Date: 11/25/24
  Time: 8:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="ma.fstt.entities.Client" %>  <!-- Assurez-vous d'importer la classe Client -->
<html>
<head>
    <title>Liste des Clients</title>
</head>
<body>

<h1>Liste des Clients</h1>
<a href="${pageContext.request.contextPath}/clients?action=add">Ajouter un client</a>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Email</th>
        <th>Adresse</th>
        <th>Téléphone</th>
        <th>Actions</th>
    </tr>

    <%
        // Récupérer la liste des clients passée par le servlet
        List<Client> clients = (List<Client>) request.getAttribute("clients");

        if (clients != null) {
            // Itérer sur la liste des clients et afficher les données
            for (Client client : clients) {
    %>

    <tr>
        <td><%= client.getId() %></td>
        <td><%= client.getNom() %></td>
        <td><%= client.getEmail() %></td>
        <td><%= client.getAddress() %></td>
        <td><%= client.getTelephone() %></td>
        <td>
            <a href="${pageContext.request.contextPath}/clients?action=edit&id=<%= client.getId() %>">Modifier</a>
            <a href="${pageContext.request.contextPath}/clients?action=delete&id=<%= client.getId() %>" onclick="return confirm('Supprimer ce client ?')">Supprimer</a>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>
