<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<%--@elvariable id="guild" type="net.dv8tion.jda.api.entities.Guild"--%>
<t:server title="Events - ${guild.name}" page="events">
    <section class="settings">
        <%--@elvariable id="eventForm" type="pathfinder.weatherBot.frontend.EventForm"--%>
        <sf:form modelAttribute="eventForm" action="events" method="post">
            <table aria-label="Scheduled weather events.">
                <tr>
                    <th>Type</th>
                    <th>Start</th>
                    <th>End</th>
                    <th>Active</th>
                </tr>
                <c:forEach var="event" varStatus="s" items="${eventForm.events}">
                    <tr>
                        <td>
                            <label>
                                <input type="text" readonly="readonly" name="events[${s.index}].name"
                                       value="${event.name}"/>
                            </label>
                        </td>
                        <td>
                            <label>
                                <input type="datetime-local" readonly="readonly" name="events[${s.index}].start" size="17"
                                       value="${event.start}"/>
                            </label>
                        </td>
                        <td>
                            <label>
                                <input type="datetime-local" readonly="readonly" name="events[${s.index}].end" size="17"
                                       value="${event.end}"/>
                            </label>
                        </td>
                        <td>
                            <label>
                                <input type="checkbox" name="events[${s.index}].active" style="display: block; margin: 0 auto;"
                                       <c:if test="${event.active}">checked="checked"</c:if>/>
                            </label>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <input class="inputButton tableSubmit" type="submit" value="Save changes"/>
        </sf:form>
    </section>
</t:server>
