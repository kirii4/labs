<%@ page import="com.train.hotel.utility.ServletUtility" %>
<body>
<%@ include file="header.jsp"%>
<main class="login-form">
    <div style="margin-top: 270px;padding-bottom: 165px" class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <fmt:message key="login" bundle="${lang}"/>

                        <h6 style="color: green;"></h6>
                        <h4 : style="color: green"><%=ServletUtility.getSuccessMessage(request)%></h4>
                        <H4 : style="color: red"><%=ServletUtility.getErrorMessage(request)%></H4>

                    </div>
                    <div class="card-body">
                        <form action="<%=Links.LOGIN_SERVLET%>" method="post">
                            <div class="form-group row">
                                <label for="login"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message key="email" bundle="${lang}"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="login"  class="form-control" placeholder="<fmt:message key="enter.email" bundle="${lang}"/>"
                                           name="login" pattern="^[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$"
                                           title="email should only contain email addres. e.g. danylo.vinovskyi@gmail.com"
                                           required >

                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="password"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message key="Password" bundle="${lang}"/></label>
                                <div class="col-md-6">
                                    <input type="password" id="password" class="form-control" placeholder="<fmt:message key="enter.password" bundle="${lang}"/>"
                                           name="password"
                                    >

                                </div>
                            </div>
                            <div class="col-md-6 offset-md-4">
                                <input type="submit" class="btn btn-primary" name="operation" value="<fmt:message key="login" bundle="${lang}"/>">
                                <a href="" class="btn btn-link"> <fmt:message key="forgot.password" bundle="${lang}"/> </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
