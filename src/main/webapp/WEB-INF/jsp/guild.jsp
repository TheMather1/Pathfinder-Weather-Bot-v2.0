<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

            </div>
        </div>
    </div>
</div>
</body>
</html>
