<%@ page import="com.train.hotel.model.entity.Payment" %>
<%@ page import="com.train.hotel.model.dao.OrderDao" %>
<%@ page import="com.train.hotel.utility.DataUtility" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@include file="header.jsp"%>
<style><%@include file="/css/paymentstyle.css"%></style>
<html>
<head>
<title></title>
</head>
<body>
    <%  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Payment payment = OrderDao.PaymentAttributes(DataUtility.getLong(request.getParameter("idOrder")), (String) session.getAttribute("lang"));
        long priceForAllDay = DataUtility.nDaysBetweenTwoDate(dateFormat.format(payment.getDateOfSettlement()), dateFormat.format(payment.getDateOfDeparture())) * payment.getPrice();
    %>

<div class='container'>
    <div class="card mx-auto col-md-4 col-6 mt-3 p-0"> <img class='mx-auto pic' src="${pageContext.request.contextPath}<%=payment.getImage()%>"/>
        <div class="card-title d-flex px-4">
            <p class="item text-muted"><fmt:message key="type.of.apartments" bundle="${lang}"/>: <label class="register"></label> <%=payment.getClassRoom()%></p>
            <p class="item text-muted"><fmt:message key="price.per.day" bundle="${lang}"/>: <label class="register"></label> <%=payment.getPrice()%>&#8372</p>
        </div>
        <div class="card-title d-flex px-4">
            <p class="item text-muted"><fmt:message key="date.of.settlement" bundle="${lang}"/>: <label class="register"></label> <%=dateFormat.format(payment.getDateOfSettlement())%></p>
            <p class="item text-muted"><fmt:message key="date.of.departure" bundle="${lang}"/>:  <label class="register"></label> <%=dateFormat.format(payment.getDateOfDeparture())%></p>
        </div>
        <div class="card-title d-flex px-4">
            <p class="item text-muted"><fmt:message key="price.for" bundle="${lang}"/> <label class="register"></label> <%=DataUtility.nDaysBetweenTwoDate(dateFormat.format(payment.getDateOfSettlement()), dateFormat.format(payment.getDateOfDeparture()))%> <fmt:message key="days" bundle="${lang}"/></p>
            <p class="item text-muted"><%=priceForAllDay%>&#8372</p>
        </div>

        <div class="card-body">
            <p class="text-muted"><fmt:message key="payment.details" bundle="${lang}"/></p>
            <div class="numbr mb-3"> <i class=" col-1 fas fa-credit-card text-muted p-0"></i>
                <input class="col-10 p-0" type="text" placeholder="<fmt:message key="card.number" bundle="${lang}"/>">
            </div>
            <div class="line2 col-lg-12 col-12 mb-4"> <i class="col-1 far fa-calendar-minus text-muted p-0"></i>
                <input class="cal col-5 p-0" type="text" placeholder="<fmt:message key="mm.yy" bundle="${lang}"/>">
                <i class="col-1 fas fa-lock text-muted"></i>
                <input class="cvc col-5 p-0" type="text" placeholder="<fmt:message key="cvc" bundle="${lang}"/>">
            </div>
        </div>
        <div class="footer text-center p-0">
            <div class="col-lg-12 col-12 p-0">
                    <a class="order"  href="UserOrdersServlet?idUser=<%=userId%>&idOrder=<%=request.getParameter("idOrder")%>&operation=<%="payed"%>"><fmt:message key="order.now" bundle="${lang}"/></a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
