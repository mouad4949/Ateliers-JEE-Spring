<%@ page import="ma.fstt.entities.Client" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Ajouter une Commande</title>
</head>
<body>
<h1>Ajouter une Commande</h1>
<form action="${pageContext.request.contextPath}/commandes" method="post">
  <input type="hidden" name="action" value="add">

  <label for="date">Date :</label>
  <input type="date" id="date" name="date" required><br>

  <label for="client_id">Choisir un Client :</label>
  <select id="client_id" name="client_id" required>
    <option value="">-- Sélectionner un Client --</option>

    <%-- Récupération de la liste des clients depuis la requête --%>
    <%
      List<Client> clients = (List<Client>) request.getAttribute("clients");
      for (Client client : clients) {
    %>
    <option value="<%= client.getId() %>">
      <%=  client.getNom() %>
    </option>
    <%
      }
    %>
  </select><br>

  <button type="submit">Ajouter</button>
</form>
</body>
</html>
