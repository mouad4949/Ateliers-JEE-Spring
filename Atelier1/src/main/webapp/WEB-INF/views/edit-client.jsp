<%--
  Created by IntelliJ IDEA.
  User: mouad
  Date: 11/25/24
  Time: 8:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit client</title>
</head>
<body>
<h1>Modifier Client</h1>
<form action="/clients" method="post">
  <input type="hidden" name="action" value="update">
  <input type="hidden" name="id" value="${client.id}">
  <label>Nom :</label>
  <input type="text" name="nom" value="${client.nom}" required><br>
  <label>Email :</label>
  <input type="email" name="email" value="${client.email}" required><br>
  <label>Adresse :</label>
  <input type="text" name="address" value="${client.address}" required><br>
  <label>Téléphone :</label>
  <input type="text" name="telephone" value="${client.telephone}" required><br>
  <button type="submit">Modifier</button>
</form>
</body>
</html>
