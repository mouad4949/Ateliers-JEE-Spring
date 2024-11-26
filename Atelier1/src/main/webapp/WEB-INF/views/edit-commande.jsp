<%@ page import="ma.fstt.entities.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="ma.fstt.entities.Commande" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Modifier la Commande</title>
</head>
<body>
<h1>Modifier la Commande</h1>
<form action="${pageContext.request.contextPath}/commandes" method="post">
  <input type="hidden" name="action" value="update">
  <input type="hidden" name="id" value="${commande.id}">  <!-- Assurez-vous que commande est bien passé -->

  <label for="date">Date :</label>
  <input type="date" id="date" name="date" value="${commande.date}" required><br>

  <label for="client_id">Client :</label>
  <select id="client_id" name="client_id" required>
    <%
      // Récupérer l'attribut clients passé depuis la servlet
      List<Client> clients = (List<Client>) request.getAttribute("clients");
      Commande commande = (Commande) request.getAttribute("commande"); // Récupérer l'attribut commande
      for (Client client : clients) {
        boolean selected = (client.getId() == commande.getClient().getId());
    %>
    <option value="<%= client.getId() %>" <%= selected ? "selected" : "" %>><%= client.getNom() %></option>
    <% } %>
  </select><br>

  <button type="submit">Mettre à jour</button>
</form>
</body>
</html>
