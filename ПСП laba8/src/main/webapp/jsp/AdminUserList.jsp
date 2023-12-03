<%@ page import="com.train.hotel.utility.ServletUtility" %>
<%@ include file="header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<br>
<h2><fmt:message key="admin.user.list" bundle="${lang}"/></h2>
<br>
<body>
<h2 : style="color: green"><%=ServletUtility.getSuccessMessage(request)%></h2>
<H2 : style="color: red"><%=ServletUtility.getErrorMessage(request)%></H2>
<table class="table table-striped" >
    <tr>
        <th scope="col">Id</th>
        <th scope="col"><fmt:message key="first.name" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="email" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="mobile.number" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="date.of.birth" bundle="${lang}"/></th>
        <th scope="col"><fmt:message key="action" bundle="${lang}"/></th>
    </tr>

    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.mobileNo}</td>
            <td>${user.dob}</td>
            <td>
                <a class="btn btn-info" href="RegistrationServlet?id=${user.id}"><fmt:message key="edit" bundle="${lang}"/></a>&nbsp;
                <a class="btn btn-danger" href="AdminUserListServlet?id=${user.id}"><fmt:message key="delete" bundle="${lang}"/></a>
            </td>
        </tr>
    </c:forEach>
</table>



<%--For displaying Page numbers.
The when condition does not display a link for the current page--%>
<style><%@include file="/css/userList.css"%></style>
<div class="container">
    <table id="pages" border="1" cellpadding="5" cellspacing="5">
        <tr>
            <td>
                <%--For displaying Previous link except for the 1st page --%>
                <c:if test="${currentPage != 1}">
                    <a href="AdminUserListServlet?page=${currentPage - 1}"><fmt:message key="previous" bundle="${lang}"/></a>
                </c:if>
            </td>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="AdminUserListServlet?page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <td>
                <%--For displaying Next link --%>
                <c:if test="${currentPage lt noOfPages}">
                    <a href="AdminUserListServlet?page=${currentPage + 1}"><fmt:message key="next" bundle="${lang}"/></a>
                </c:if>
            </td>
        </tr>
    </table>
</div>




</body>
</html>