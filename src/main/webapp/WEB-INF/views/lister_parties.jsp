<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 id="title">Liste des parties disponibles</h1>
<table class="ui table">
    <thead>
    <th>Nom</th>
    <th>Nombre joueurs</th>
    <th>Rejoindre</th>
    <th>Supprimer</th>
</thead>
<c:forEach items="${liste}" var="partie">
    <tr>
        <td>${partie.nom} ${partie.id}</td>
        <td>${partie.joueurs} </td>
        <td><c:if test="${partie.joueurs.size() == 2 }"><button class="ui disabled button" onclick="pre_join(${partie.id})">Rejoindre</button></c:if>
            <c:if test="${partie.joueurs.size() < 2 }"><button class="ui active button" onclick="pre_join(${partie.id})">Rejoindre</button></c:if></td>
    <td><img width="20" height="20" onclick="delete_partie(${partie.id})" src="<c:url value='/IMG/delete.png'/>"/></td>
</tr>
</c:forEach> 
</table>
