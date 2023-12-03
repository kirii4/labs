<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.train.hotel.utility.ServletUtility" %>
<%@page import="com.train.hotel.utility.DataUtility" %>
<%@ page import="com.train.hotel.model.entity.UserOrderList" %>
<%@ page import="com.train.hotel.model.entity.UserOrderList" %>
<%@ page import="java.util.Date" %>
<%@include file="header.jsp"%>
<br>
<h2><fmt:message key="user.my.orders" bundle="${lang}"/></h2>
<br>
<h4 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h4>
<h4 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h4>
<table class="table table-striped">

    <thead>
    <tr>
        <th scope="col"><fmt:message key="order.id" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="type.of.apartments" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="amount.of.guests" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="date.of.settlement" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="date.of.departure" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="status" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="action" bundle="${lang}"/></th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var ="userOrderList">
    <tr>
        <th scope="row">${userOrderList.idOrder}</th>
        <td>${userOrderList.clasRoom}</td>
        <td>${userOrderList.guests}</td>
        <c:set var="dateS" scope="request" value="${userOrderList.dateOfSettlement}"> </c:set>
        <% Date dateOfSettlement = (Date)request.getAttribute("dateS");%>
        <c:set var="dateD" scope="request" value="${userOrderList.dateOfDeparture}"> </c:set>
        <% Date dateOfDeparture = (Date) request.getAttribute("dateD");%>
        <td><%=DataUtility.getDateString(dateOfSettlement)%></td>
        <td><%=DataUtility.getDateString(dateOfDeparture)%></td>
        <td>${userOrderList.paymentStatus}</td>
        <td>
            <c:set var="paymentS" scope="request" value="${userOrderList.paymentStatus}"> </c:set>
            <% String paymentStatus = (String) request.getAttribute("paymentS");%>
            <c:set var="orderId" scope="request" value="${userOrderList.idOrder}"> </c:set>
            <% long idOrder = (Long) request.getAttribute("orderId");%>
            <c:set var="roomId" scope="request" value="${userOrderList.idRoom}"> </c:set>
            <% long idRoom = (Long) request.getAttribute("roomId");%>

            <form action="UserOrdersServlet" method="post">
            <%if(paymentStatus.equalsIgnoreCase("waiting to confirmation") || paymentStatus.equalsIgnoreCase("\u043f\u043e\u0442\u0440\u0456\u0431\u043d\u0435\u0020\u043f\u0456\u0434\u0442\u0432\u0435\u0440\u0434\u0436\u0435\u043d\u043d\u044f")){%>
            <a class="btn btn-info" href="UserOrdersServlet?idUser=<%=userId%>&idOrder=<%=idOrder%>&idRoom=<%=idRoom%>"><fmt:message key="confirm" bundle="${lang}"/></a>&nbsp;
            <a class="btn btn-danger" href="UserOrdersServlet?idUser=<%=userId%>&idOrder=<%=idOrder%>&operation=<%="cancel"%>"><fmt:message key="cancel" bundle="${lang}"/></a>
            <%}else if(paymentStatus.equalsIgnoreCase("payed") || paymentStatus.equalsIgnoreCase("\u0441\u043f\u043b\u0430\u0447\u0435\u043d\u0438\u0439")){%>
                <p class="btn btn-success" disabled><fmt:message key="done" bundle="${lang}"/></p>&nbsp;
            <%}else if(paymentStatus.equalsIgnoreCase("need to pay") || paymentStatus.equalsIgnoreCase("\u043f\u043e\u0442\u0440\u0456\u0431\u043d\u0430\u0020\u043e\u043f\u043b\u0430\u0442\u0430")){%>
                <a class="btn btn-warning" href="PaymentServlet?idUser=<%=userId%>&idOrder=<%=idOrder%>"><fmt:message key="pay" bundle="${lang}"/></a>&nbsp;
            <%}%>
            </form>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
