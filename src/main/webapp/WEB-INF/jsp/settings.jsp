<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<t:portal title="Settings - ${guild.name}">
    <header>
        <nav class="contentNavbar">
            <ul>
                <li>
                    <a href="../${guild.id}">Weather</a>
                </li>
                <c:if test="${forecast}">
                    <li>
                        <a href="./forecast">Forecast</a>
                    </li>
                </c:if>
                <c:if test="${settings}">
                    <li class="activeContent">
                        <a href="">Settings</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </header>
    <section class="settings">
        <sf:form modelAttribute="config" action="settings" method="post">
            <fieldset>
                <legend><h1>Settings</h1></legend>
                <label for="climate">Climate: </label>
                <sf:select path="climate">
                    <sf:options items="${climateOptions}"/>
                </sf:select>
                <br>
                <label for="elevation">Elevation: </label>
                <sf:select path="elevation">
                    <sf:options items="${elevationOptions}"/>
                </sf:select>
                <br>
                <label for="desert">Desert: </label>
                <sf:checkbox path="desert" id="desert"/>
                <br>
                <label for="forecastRole">Forecast role: </label>
                <sf:select path="forecastRole">
                    <sf:options items="${roleOptions}"/>
                </sf:select>
                <br>
                <label for="outputChannel">Output channel: </label>
                <sf:select path="outputChannel">
                    <sf:options items="${channelOptions}"/>
                </sf:select>
                <br><br>
                <input type="submit" value="Save changes">
            </fieldset>
        </sf:form>
    </section>
</t:portal>
