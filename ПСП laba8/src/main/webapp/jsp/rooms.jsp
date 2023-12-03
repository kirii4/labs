<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.train.hotel.utility.ServletUtility" %>
<%@ page import="com.train.hotel.model.entity.Room" %>
<%@include file="header.jsp"%>
<html>
<h4 style="color: red;"><%=ServletUtility.getErrorMessage(request)%><%!%></h4>
<h4 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h4>

<body>
<table class="table table-striped">

    <thead>
    <form action="RoomsServlet" method="post">
    <tr>
        <th scope="col">
            <label for="sort"><fmt:message key="sort.by" bundle="${lang}"/>:</label>
            <select name="sort" id="sort">
                <option disabled><fmt:message key="sort.choose" bundle="${lang}"/></option>
                <option value="id_class_of_room"><fmt:message key="sort.type.of.apartments" bundle="${lang}"/></option>
                <option value="guests"><fmt:message key="sort.amount.of.guests" bundle="${lang}"/></option>
                <option value="price"><fmt:message key="sort.price" bundle="${lang}"/></option>
                <option value="status_en"><fmt:message key="sort.status" bundle="${lang}"/></option>
            </select>
            <input type="submit" value="<fmt:message key="submit" bundle="${lang}"/>" />
        <th scope="col"><fmt:message key="type.of.apartments" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="amount.of.guests" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="price.per.day" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="status" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="action" bundle="${lang}"/></th>
    </tr>
    </form>

    </thead>

    <tbody>

    <c:forEach items="${list}" var ="room">
    <tr>
        <td><img src="${pageContext.request.contextPath}${room.image}" alt="" height=300 width=300></td>
        <td>${room.classRoom}</td>
        <td>${room.guests}</td>
        <td>${room.price}&#8372</td>

        <c:set var="statRoom" scope="request" value="${room.status}"> </c:set>
        <% String statusRoom = (String)request.getAttribute("statRoom");%>

        <% if (statusRoom.equalsIgnoreCase("available") || statusRoom.equalsIgnoreCase("\u0434\u043e\u0441\u0442\u0443\u043f\u043d\u0438\u0439")){%>
        <td style="color: green"><%=statusRoom%></td>
        <% }else {%>
        <td style="color: red"><%=statusRoom%></td>
        <%}%>
        <td>
            <%if (statusRoom.equalsIgnoreCase("available") || statusRoom.equalsIgnoreCase("\u0434\u043e\u0441\u0442\u0443\u043f\u043d\u0438\u0439")){%>
            <form action="MakeAnOrderServlet" method="post">
                <a class="btn btn-secondary" href="MakeAnOrderServlet?operation=<%="book"%>"><fmt:message key="reserve.it" bundle="${lang}"/></a>&nbsp;
            </form>
            <%}else{%>
            <button type="button" class="btn btn-secondary" href="#" disabled><fmt:message key="reserved" bundle="${lang}"/></button>
            <%}%>
        </td>

    </tr>


    </c:forEach>

    </tbody>
</table>
</body>
</html>