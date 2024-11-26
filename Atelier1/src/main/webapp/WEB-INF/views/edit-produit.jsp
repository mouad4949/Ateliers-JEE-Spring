<%--
  Created by IntelliJ IDEA.
  User: mouad
  Date: 11/26/24
  Time: 2:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Modifier le Produit</h1>
<form action="${pageContext.request.contextPath}/produits" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${produit.id}">

    <label for="nom">Nom :</label>
    <input type="text" id="nom" name="nom" value="${produit.nom}" required><br>

    <label for="prix">Prix :</label>
    <input type="number" id="prix" name="prix" value="${produit.prix}" required><br>

    <label for="quantite_en_stock">Quantité en stock :</label>
    <input type="number" id="quantite_en_stock" name="quantite_en_stock" value="${produit.quantite_en_stock}" required><br>


    <button type="submit">Mettre à jour</button>
</form>
</body>
</html>
