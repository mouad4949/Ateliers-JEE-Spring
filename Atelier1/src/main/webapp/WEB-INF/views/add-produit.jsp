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
    <title>Ajouter un Produit</title>
</head>
<body>
<h1>Ajouter un Produit</h1>
<form action="${pageContext.request.contextPath}/produits" method="post">
    <input type="hidden" name="action" value="add">

    <label for="nom">Nom :</label>
    <input type="text" id="nom" name="nom" required><br>

    <label for="prix">Prix :</label>
    <input type="number" id="prix" name="prix" required><br>

    <label for="quantite_en_stock">Quantité en stock :</label>
    <input type="number" id="quantite_en_stock" name="quantite_en_stock" required><br>

    <button type="submit">Ajouter</button>
</form>

<a href="${pageContext.request.contextPath}/produits">Retour à la liste des produits</a>

</body>
</html>

