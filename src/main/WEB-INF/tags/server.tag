<%@ tag description="Portal page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="page" required="true" %>
<%@attribute name="title" required="true" %>

<t:portal title="${title}">
    <%--@elvariable id="guild" type="net.dv8tion.jda.api.entities.Guild"--%>
    <header>
        <nav class="contentNavbar">
            <ul>
                <li <c:if test="${page == 'guild'}">class="activeContent"</c:if>>
                    <a href="/portal/${guild.id}">Weather</a>
                </li>
                <%--@elvariable id="canForecast" type="boolean"--%>
                <c:if test="${canForecast}">
                    <li <c:if test="${page == 'forecast'}">class="activeContent"</c:if>>
                        <a href="/portal/${guild.id}/forecast">Forecast</a>
                    </li>
                </c:if>
                <%--@elvariable id="isModerator" type="boolean"--%>
                <c:if test="${isModerator}">
                    <li <c:if test="${page == 'settings'}">class="activeContent"</c:if>>
                        <a href="/portal/${guild.id}/settings">Settings</a>
                    </li>
                    <li <c:if test="${page == 'events'}">class="activeContent"</c:if>>
                        <a href="/portal/${guild.id}/events">Events</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </header>
    <div class="content">
        <jsp:doBody/>
    </div>
</t:portal>
