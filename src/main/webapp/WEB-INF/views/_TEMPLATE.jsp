<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="monApp">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Streaming</title>
        <script src="<c:url value="/JS/jquery-2.2.2.js"/>" type="text/javascript"></script>
        <link href="<c:url value="/CSS/semantic.min.css"/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value="/CSS/css.css"/>" rel="stylesheet" type="text/css"/>
        <script src="<c:url value="/CSS/semantic.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/JS/JS.js"/>" type="text/javascript"></script>
    </head>
    <body onload="onLoad()">
        
        <div class="ui container">
            <a href="<c:url value="/"/>">
                <div class="ui inverted segment" >
                    <h1 class="ui orange inverted centered header" >Jungle Speed</h1>
                </div>
            </a>

            <c:import url="_MENU.jsp"/>
            <div class="ui secondary  menu">
                <a class="active item" onclick="lister_parties()">Parties disponibles</a>
                <a class="item" onclick="mapartie(${idJoueur})">Ma partie en cours</a>
                <a class="item">Connexion</a>
                <a class="item">Classement</a>
            </div>

            <div class="ui divider"></div>
            <div class="ui active dimmer" id="dimmer">
                <div class="ui loader" id="loader"></div>
            </div>
            <br>
            <div id="content" class="ui container"> 
            </div>
            <div id="footer" >
                <c:import url="_FOOTER.jsp"/>
            </div>
        </div>
    </body>
</html>
