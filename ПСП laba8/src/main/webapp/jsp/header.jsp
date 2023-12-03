<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.train.hotel.controller.Links" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" var="lang"/>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</head>
<body>

    <%String userSession = (String)session.getAttribute("user");%>
    <%String userRole = (String)session.getAttribute("role");%>
    <%Long userId = (Long) session.getAttribute("idUser");%>


    <nav class="navbar navbar-expand-lg navbar-light bg-secondary">
    <a class="navbar-brand" href="<%=Links.WELCOME_SERVLET%>"><fmt:message key="hotel" bundle="${lang}"/></a>


    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNav">


        <ul class="navbar-nav">
            <a class="nav-link" href="<%=Links.ROOMS_SERVLET%>"><fmt:message key="apartments" bundle="${lang}"/></a>
            <a class="nav-link" href="<%=Links.MAKE_AN_ORDER_SERVLET%>"><fmt:message key="make.an.order" bundle="${lang}"/></a>


            <a class="nav-link" href="?locale=uk">УКР</a>
            <a class="nav-link" href="?locale=en">EN</a>


        <%if(userSession == null) {%>


                <a style="position:absolute; right: 130px" class="nav-link" href="<%=Links.LOGIN_SERVLET%>"><fmt:message key="login" bundle="${lang}"/></a>
                <a style="position:absolute; right: 0"  class="nav-link" href="<%=Links.REGISTRATION_SERVLET%>"><fmt:message key="registration" bundle="${lang}"/></a>


        <%}else{%>

            <%if(userRole.equalsIgnoreCase("admin")) {%>
                <a style="color: #212529; position:absolute; right: 800px"  class="nav-link" href="<%=Links.ADMIN_USER_LIST_SERVLET%>"><fmt:message key="admin.user.list" bundle="${lang}"/></a>
                <a style="color: #212529; position:absolute; right: 1000px" class="nav-link" href="<%=Links.ORDER_LIST_SERVLET%>"><fmt:message key="admin.users.orders" bundle="${lang}"/></a>

            <%}%>
                <a style="color: #212529; position:absolute; right: 205px" class="nav-link" href="<%=Links.USER_ORDER_LIST_SERVLET%>"><fmt:message key="user.my.orders" bundle="${lang}"/></a>
                <a style="color: #212529; position:absolute; right: 150px" class="nav-link" href="<%=Links.LOGIN_SERVLET + "?operation=logout" %>"><fmt:message key="logout" bundle="${lang}"/></a>
                <a style="color: #212529; position:absolute; right: 0" class="nav-link disable" href=""><fmt:message key="user" bundle="${lang}"/>: <%=userSession%></a>
        </ul>
        <%}%>
    </div>
</nav>
</body>