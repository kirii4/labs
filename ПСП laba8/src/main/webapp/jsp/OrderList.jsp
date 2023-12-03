<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.train.hotel.utility.ServletUtility" %>
<%@ page import="com.train.hotel.model.entity.Order" %>
<%@ page import="com.train.hotel.model.entity.Order" %>
<%@include file="header.jsp"%>
<br>
<h2><fmt:message key="admin.users.orders" bundle="${lang}"/></h2>
<br>
<h4 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h4>
<h4 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h4>
<table class="table table-striped">

    <thead>
    <tr>
        <th scope="col">id</th>
        <th scope="col"><fmt:message key="user.id" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="amount.of.guests" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="type.of.apartments" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="date.of.settlement" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="date.of.departure" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="date.of.create.order" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="select.id.room" bundle="${lang}"/></th>

    </tr>
    </thead>
    <tbody>

    <c:forEach items="${list}" var ="order">
        <tr>
            <td>${order.idOrder}</td>
            <td>${order.idUser}</td>
            <td>${order.amountOfGuests}</td>
            <td>${order.typeOfRoom}</td>
            <td>${order.dateOfSettlement}</td>
            <td>${order.dateOfDeparture}</td>
            <td>${order.dateOfCreateOrder}</td>
            <td>
                <form action="OrderListServlet?idOrder=${order.idOrder}" method="post">
                    <select name="category">
                        <c:forEach items="${listCategory}" var="ClassOfRoom">
                            <option value="${ClassOfRoom.getIdRoom()}">
                                    ${ClassOfRoom.getIdRoom()}-${ClassOfRoom.getClassRoom()}-${ClassOfRoom.getGuests()}
                            </option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="<fmt:message key="submit" bundle="${lang}"/>" />
                </form>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>