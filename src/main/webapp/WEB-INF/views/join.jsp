<%-- 
    Document   : join
    Created on : 30 mars 2016, 16:44:15
    Author     : admin
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <input type="hidden" name="partieId" value="${partieId}"/>
    <label>Pseudo du joueur : </label>
    <input type="text" name="nom" value="${joueur.login}"/>
    <br><br>
    <button class="ui button" onclick="join()">Valider</button>