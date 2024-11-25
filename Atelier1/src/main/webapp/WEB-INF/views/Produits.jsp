<%@ page import="ma.fstt.entities.Produit" %><%--
  Created by IntelliJ IDEA.
  User: mouad
  Date: 11/26/24
  Time: 12:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Liste des Produits</title>
</head>
<body>
<h1>Liste des Produits</h1>
<a href="/produits?action=add">Ajouter un produit</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Prix</th>
        <th>Quantité en Stock</th>
        <th>Actions</th>
    </tr>
    <%
        List<Produit> produits = (List<Produit>) request.getAttribute("produits");
        if (produits != null) {
            for (Produit produit : produits) { %>
    <tr>
        <td><%= produit.getId() %></td>
        <td><%= produit.getNom() %></td>
        <td><%= produit.getPrix() %></td>
        <td><%= produit.getQuantite_en_stock() %></td>
        <td>
            <a href="/produits?action=edit&id=<%= produit.getId() %>">Modifier</a>
            <a href="/produits?action=delete&id=<%= produit.getId() %>" onclick="return confirm('Supprimer ce produit ?')">Supprimer</a>
        </td>
    </tr>
    <%  }
    } %>
</table>
</body>
</html>