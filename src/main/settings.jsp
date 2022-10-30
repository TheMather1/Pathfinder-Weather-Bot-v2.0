<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<%--@elvariable id="guild" type="net.dv8tion.jda.api.entities.Guild"--%>
<t:server title="Settings - ${guild.name}" page="settings">
    <section class="settings">
        <%--@elvariable id="config" type="pathfinder.weatherBot.interaction.GuildConfig"--%>
        <sf:form modelAttribute="config" action="settings" method="post">
            <fieldset>
                <legend><h1>Settings</h1></legend>
                <label for="climate">Climate: </label>
                <sf:select path="climate">
                    <%--@elvariable id="climateOptions" type="java.util.List"--%>
                    <sf:options items="${climateOptions}"/>
                </sf:select>
                <br>
                <label for="elevation">Elevation: </label>
                <sf:select path="elevation">
                    <%--@elvariable id="elevationOptions" type="java.util.List"--%>
                    <sf:options items="${elevationOptions}"/>
                </sf:select>
                <br>
                <label for="desert">Desert: </label>
                <sf:checkbox path="desert" id="desert"/>
                <br>
                <label for="forecastRole">Forecast role: </label>
                <sf:select path="forecastRole">
                    <%--@elvariable id="roleOptions" type="java.util.List"--%>
                    <sf:options items="${roleOptions}"/>
                </sf:select>
                <br>
                <label for="outputChannel">Output channel: </label>
                <sf:select path="outputChannel">
                    <%--@elvariable id="channelOptions" type="java.util.List"--%>
                    <sf:options items="${channelOptions}"/>
                </sf:select>
                <br>
                <label for="timezone">Timezone: </label>
                <sf:select path="timezone">
                    <%--@elvariable id="timezoneOptions" type="java.util.List"--%>
                    <sf:options items="${timezoneOptions}"/>
                </sf:select>
                <br><br>
                <input class="inputButton" type="submit" value="Save changes">
            </fieldset>
        </sf:form>
    </section>
</t:server>
