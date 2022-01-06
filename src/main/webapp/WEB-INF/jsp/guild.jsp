<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en"
      xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Pathfinder WeatherBot</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/portal.css"/>">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
<div class="wrapper">
    <div class="window">
        <div class="container">
            <div id="navbar" class="sidebar">
                <div class="scrollerWrapper">
                    <div class="scroller">
                        <div class="sidebarInner">
                            <div class="sidebarContainer">
                                <div class="serverContainer">
                                    <p class="sidebarHeading">Servers</p>
                                    <ul class="sidebarList">
                                        <c:forEach var="server" items="${servers}">
                                            <li>
                                                <a class="sidebarLink<c:if test="${guild == server.value}"> activeLink</c:if>"
                                                   href="./<c:out value="${server.value}"/>">
                                                    <c:out value="${server.key}"/>
                                                </a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="content">
                <header>
                    <nav class="contentNavbar">
                        <ul>
                            <li class="activeContent">
                                <a href="./<c:out value="${guild}"/>">Weather</a>
                            </li>
                            <c:if test="${forecast}">
                                <li>
                                    <a href="./<c:out value="${guild}"/>/forecast">Forecast</a>
                                </li>
                            </c:if>
                            <c:if test="${config}">
                                <li>
                                    <a href="./<c:out value="${guild}"/>/config">Configure</a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </header>
                <section class="guildInfo">
                    <span class="temp">
                        <h1>Temperature: </h1>
                        ${temp}
                    </span>
                    <span class="clouds">
                        <h1>Clouds: </h1>
                        ${clouds}
                    </span>
                    <span class="wind">
                        <h1>Wind: </h1>
                        ${wind}
                    </span>
                    <span class="precip">
                        <h1>Precipitation: </h1>
                        ${precip}
                    </span>
                </section>
            </div>
        </div>
    </div>
</div>
</body>
</html>
