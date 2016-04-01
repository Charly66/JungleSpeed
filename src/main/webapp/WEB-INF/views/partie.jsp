<%-- 
    Document   : partie
    Created on : 31 mars 2016, 10:08:45
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="ui internally celled grid">
  <div class="row">
    <div class="five wide column">
      ${joueur1.login}
    </div>
    <div class="six wide column" id="timer">
        <div>Timer : </div><span id="compt"></span>
    </div>
    <div class="five wide column">
      ${joueur2.login}
    </div>
  </div>
  <div class="row">
    <div class="five wide column">
        <div style="background-color: ${carteJoueur1}"> jfzeohfhozegho</div>
    </div>
    <div class="six wide column">
        <img src="<c:url value='/IMG/button.jpg'/>" class="ui middle aligned centered image" width="300" alt=""/>
    </div>
    <div class="five wide column">
        <div style="background-color: ${carteJoueur2}"> jfzeohfhozegho</div>
    </div>
  </div>
</div>

