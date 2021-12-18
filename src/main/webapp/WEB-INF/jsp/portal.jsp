<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en"
      xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Pathfinder WeatherBot</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="resources/css/portal.css"/>">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
<div id="navbar" class="sidebar scrollerWrapper">
    <div class="sidebarInner">
        <p class="sidebarHeading">Servers</p>
        <ul class="sidebarList">
            <c:forEach var="server" items="${servers}">
                <li>
                    <a class="sidebarLink" href="portal/<c:out value="${server.value}"/>"><c:out
                            value="${server.key}"/></a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
