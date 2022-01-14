<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:server title="${guild.name}" page="guild">
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
</t:server>
