<%@ page import="ma.fstt.entities.LigneDeCommande" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lignes de Commande</title>
</head>
<body>
<h1>Lignes de Commande</h1>

<!-- Lien pour ajouter une nouvelle ligne -->
<a href="${pageContext.request.contextPath}/lignes-commande?commande_id=${param.commande_id}&action=add">Ajouter une nouvelle ligne</a>

<!-- Liste des lignes de commande -->
<h3>Liste des Lignes de Commande</h3>
<table border="1">
    <thead>
    <tr>
        <th>Produit ID</th>
        <th>Quantité</th>
        <th>Prix Total</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<LigneDeCommande> lignes = (List<LigneDeCommande>) request.getAttribute("lignes");
        for (LigneDeCommande ligne : lignes) {
    %>
    <tr>
        <td><%= ligne.getProduit().getId() %></td>
        <td><%= ligne.getQuantite() %></td>
        <td><%= ligne.getSousTotal() %></td>
        <td>
            <a href="${pageContext.request.contextPath}/lignes-commande?commande_id=<%= ligne.getCommande().getId() %>&action=delete&produit_id=<%= ligne.getProduit().getId() %>">Supprimer</a>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
