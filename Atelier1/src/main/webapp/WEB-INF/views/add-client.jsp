<%--
  Created by IntelliJ IDEA.
  User: mouad
  Date: 11/25/24
  Time: 8:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter un Client</title>
</head>
<body>
<h1>Ajouter un Client</h1>
<form action="/clients" method="POST">
    <input type="hidden" name="action" value="add" />
    <label for="nom">Nom:</label>
    <input type="text" id="nom" name="nom" required /><br><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required /><br><br>

    <label for="address">Adresse:</label>
    <input type="text" id="address" name="address" required /><br><br>

    <label for="telephone">Téléphone:</label>
    <input type="text" id="telephone" name="telephone" required /><br><br>

    <button type="submit">Ajouter</button>
</form>
<a href="/clients">Retour à la liste des clients</a>
</body>
</html>

