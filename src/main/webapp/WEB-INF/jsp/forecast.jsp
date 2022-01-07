guild.jsp<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:portal title="Forecast - ${guild.name}">
<header>
    <nav class="contentNavbar">
        <ul>
            <li>
                <a href="../${guild.id}">Weather</a>
            </li>
            <c:if test="${forecast}">
                <li class="activeContent">
                    <a href="">Forecast</a>
                </li>
            </c:if>
            <c:if test="${settings}">
                <li>
                    <a href="./settings">Settings</a>
                </li>
            </c:if>
        </ul>
    </nav>
</header>
<section class="forecast">
</section>
</t:portal>
