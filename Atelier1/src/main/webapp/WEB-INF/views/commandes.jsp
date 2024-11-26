<%@ page import="ma.fstt.entities.Commande" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des Commandes</title>
</head>
<body>
<h1>Liste des Commandes</h1>
<table border="1">

    <tr>
        <th>ID</th>
        <th>Date</th>
        <th>Client</th>
        <th>Actions</th>
    </tr>
        <%
    // Récupérer la liste des clients passée par le servlet
    List<Commande> commandes = (List<Commande>) request.getAttribute("commandes");

    if (commandes != null) {
        // Itérer sur la liste des clients et afficher les données
        for (Commande commande : commandes) {
%>

    <tr>
        <td><%= commande.getId() %></td>
        <td><%= commande.getDate() %></td>
        <td><%= commande.getClient().getId() %></td>
        <td>
            <a href="${pageContext.request.contextPath}/commandes?action=edit&id=<%= commande.getId() %>">Modifier</a>
            <a href="${pageContext.request.contextPath}/commandes?action=delete&id=<%= commande.getId() %>" onclick="return confirm('Supprimer cette commande ?')">Supprimer</a>
        </td>
    </tr>
        <%
        }
    }
%>
</table>


<a href="${pageContext.request.contextPath}/commandes?action=add">Ajouter une nouvelle commande</a>
</body>
</html>
