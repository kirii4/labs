<%@ include file="header.jsp"%>
<%@ page import="com.train.hotel.utility.ServletUtility" %>
<%@ page import="com.train.hotel.utility.DataUtility" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><fmt:message key="user.registration" bundle="${lang}"/></title>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function () {
            $("#datepicker").datepicker();
        });
    </script>
</head>
<body>
<main class="login-form">
    <div style="margin-top: 200px;padding-bottom: 90px" class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <jsp:useBean id="bean" class="com.train.hotel.model.entity.User" scope="request"></jsp:useBean>
                    <div class="card-header">
                        <%if(bean.getLogin() != null){%>
                            <fmt:message key="update.user" bundle="${lang}"/>
                        <%}else {%>
                            <fmt:message key="user.registration" bundle="${lang}"/>
                        <%}%>

                    </div>

                    <div class="card-body">
                        <h3 style="color: green"> <%=ServletUtility.getSuccessMessage(request)%> </h3>
                        <H3 style="color: red"> <%=ServletUtility.getErrorMessage(request)%> </H3>


                        <%
                            String fname = "";
                            String lname = "";
                            String login = "";
                            String pass = "";
                            String dob = "";
                            String mob = "";
                            if(bean.getLogin() != null){
                                fname = bean.getFirstName();
                                lname = bean.getLastName();;
                                login = bean.getLogin();
                                pass = bean.getPassword();
                                dob = DataUtility.getDateString(bean.getDob());
                                mob = bean.getMobileNo();
                            }

                        %>
                        <form action="<%=Links.REGISTRATION_SERVLET%>" method="post">

                            <input type="hidden" name="id" value="<%=DataUtility.getStringData(bean.getId())%>">

                            <div class="form-group row">
                                <label for="email_address"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message key="first.name" bundle="${lang}"/></label>
                                <div class="col-md-6">
                                    <input type="text"   class="form-control" placeholder="<fmt:message key="enter.first.name" bundle="${lang}"/>"
                                           name="firstName" value="<%=fname%>"
                                           pattern="^[a-zA-Z\u0430-\u044f\u0410-\u042f\u0456\u0457\u0406\u0407\s]+"
                                           title="First Name should contain cyrillic or latin letters"
                                           required
                                    >
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="email_address"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message key="last.name" bundle="${lang}"/></label>
                                <div class="col-md-6">
                                    <input type="text"   class="form-control" placeholder="<fmt:message key="enter.last.name" bundle="${lang}"/>"
                                           name="lastName" value="<%=lname%>"
                                           pattern="^[a-zA-Z\u0430-\u044f\u0410-\u042f\u0456\u0457\u0406\u0407\s]+"
                                           title="Last Name should contain cyrillic or latin letters"
                                           required
                                    >
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="email_address"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message key="email" bundle="${lang}"/></label>
                                <div class="col-md-6">
                                    <input type="text"   class="form-control" placeholder="<fmt:message key="enter.email" bundle="${lang}"/>"
                                           name="login" value="<%=login%>" pattern="^[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$"
                                           title="email should only contain email addres. e.g. danylo.vinovskyi@gmail.com"
                                           required>
                                </div>
                            </div>


                            <div class="form-group row">
                                <label for="email_address"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message key="Password" bundle="${lang}"/></label>
                                <div class="col-md-6">
                                    <input type="password"   class="form-control" placeholder="<fmt:message key="enter.password" bundle="${lang}"/>"
                                           name="password" value="<%=pass%>"
                                           pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\S])[A-Za-z0-9\S]{8,20}$"
                                           title="password should only contain at least 1 big letter, 1 numbers and at least 8 letters e.g. Danylo1v"
                                           required
                                    >

                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="email_address"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message key="date.of.birth" bundle="${lang}"/></label>
                                <div class="col-md-6">
                                    <label for="datepicker"></label>
                                    <input type="text"  id="datepicker" class="form-control" placeholder="<fmt:message key="enter.dob" bundle="${lang}"/>"
                                           name="dob" value="<%=dob%>"
                                           pattern="^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$"
                                           title="Date should only be in format mm/DD/yyyy"
                                           required
                                    >

                                </div>
                            </div>


                            <div class="form-group row">
                                <label for="email_address"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message key="mobile.number" bundle="${lang}"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="email_address"  class="form-control" placeholder="<fmt:message key="enter.mobile.number" bundle="${lang}"/>"
                                           name="mobile" value="<%=mob%>"
                                           pattern="^[+0-9]{10,13}"
                                           title="Mobile number should be only like this +380955596551,0986087607, 380986087603"
                                           required
                                    >
                                </div>
                            </div>


                            <%if(bean.getId() > 0){%>
                            <div class="col-md-6 offset-md-4">
                                <input type="submit" class="btn btn-primary" name="operation" value="<fmt:message key="update" bundle="${lang}"/>">
                            </div>
                            <%}else{%>
                            <div class="col-md-6 offset-md-4">
                                <input type="submit" class="btn btn-primary" name="operation" value="<fmt:message key="registration" bundle="${lang}"/>">
                            </div>
                            <%}%>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div >
</main>
</body>
</html>
