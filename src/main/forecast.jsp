guild.jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<t:server title="Forecast - ${guild.name}" page="forecast">
    <section class="forecast">
        <section id="today">
            <h1>Today</h1>
            <table>
                <tbody>
                <tr>
                    <th>Time</th>
                    <th>Clouds</th>
                    <th>Precipitation</th>
                    <th>Wind</th>
                    <th>Temperature</th>
                </tr>
                <c:forEach var="hour" items="${forecast.today.hours}">
                    <tr>
                        <td>${hour.time.toLocalTime()}</td>
                        <td>${hour.weather.clouds}</td>
                        <td>${hour.weather.precipitation.toString()}</td>
                        <td>${hour.weather.wind}</td>
                        <td>${hour.temp}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>
        <br>
        <section id="tomorrow">
            <h1>Tomorrow</h1>
            <table>
                <tbody>
                <tr>
                    <th>Time</th>
                    <th>Clouds</th>
                    <th>Precipitation</th>
                    <th>Wind</th>
                    <th>Temperature</th>
                </tr>
                <c:forEach var="hour" items="${forecast.tomorrow.hours}">
                    <tr>
                        <td>${hour.time.toLocalTime()}</td>
                        <td>${hour.weather.clouds}</td>
                        <td>${hour.weather.precipitation.toString()}</td>
                        <td>${hour.weather.wind}</td>
                        <td>${hour.temp}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>
        <br>
        <section id="nextDay">
            <h1>Day after</h1>
            <table>
                <tbody>
                <tr>
                    <th>Time</th>
                    <th>Clouds</th>
                    <th>Precipitation</th>
                    <th>Wind</th>
                    <th>Temperature</th>
                </tr>
                <c:forEach var="hour" items="${forecast.dayAfterTomorrow.hours}">
                    <tr>
                        <td>${hour.time.toLocalTime()}</td>
                        <td>${hour.weather.clouds}</td>
                        <td>${hour.weather.precipitation.toString()}</td>
                        <td>${hour.weather.wind}</td>
                        <td>${hour.temp}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>
        <c:if test="${isModerator}">
            <br>
            <button class="inputButton tableSubmit" onclick="window.location.href='forecast/delete';">Reset forecast
            </button>
        </c:if>
    </section>
</t:server>
