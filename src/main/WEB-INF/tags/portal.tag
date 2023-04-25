<%@ tag description="Portal page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="title" required="true" %>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${title} - Pathfinder WeatherBot</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/portal.css"/>">
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
                                <div class="userContainer">
                                    <%--@elvariable id="avatar" type="String"--%>
                                    <%--@elvariable id="username" type="String"--%>
                                    <img class="avatar" src="${avatar}" alt="${username}">
                                    ${username}
                                    <a class="logoutLink" href="<c:url value="/logout"/>">
                                        logout
                                    </a>
                                </div>
                                <br>
                                <div class="serverContainer">
                                    <p class="sidebarHeading">Servers</p>
                                    <ul class="sidebarList">
                                        <%--@elvariable id="servers" type="java.util.Map<String, long>"--%>
                                        <c:forEach var="server" items="${servers}">
                                            <li>
                                                <%--@elvariable id="guild" type="net.dv8tion.jda.api.entities.Guild"--%>
                                                <a class="sidebarLink<c:if test="${guild.id == server.value}"> activeLink</c:if>"
                                                   href="/portal/<c:out value="${server.value}"/>">
                                                    <c:out value="${server.key}"/>
                                                </a>
                                            </li>
                                        </c:forEach>
                                        <li>
                                            <a class="sidebarLink botInvite"
                                               target="_blank"
                                               rel="noopener noreferrer"
                                               href="https://discord.com/api/oauth2/authorize?client_id=619946100723548171&permissions=3072&scope=applications.commands%20bot">
                                                + Add Server
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="contentWrapper">
                <jsp:doBody/>
            </div>
        </div>
    </div>
</div>
</body>
</html>
