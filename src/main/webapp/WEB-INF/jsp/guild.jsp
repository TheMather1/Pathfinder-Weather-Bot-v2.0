<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:portal title="${guild.name}">
<header>
    <nav class="contentNavbar">
        <ul>
            <li class="activeContent">
                <a href="">Weather</a>
            </li>
            <c:if test="${forecast}">
                <li>
                    <a href="./${guild.id}/forecast">Forecast</a>
                </li>
            </c:if>
            <c:if test="${settings}">
                <li>
                    <a href="./${guild.id}/settings">Settings</a>
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
</t:portal>
