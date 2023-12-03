<%@page import="com.train.hotel.utility.ServletUtility" %>

<%@include file="header.jsp"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title></title>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function() {
            $.datepicker.setDefaults({ // Common options
                dateFormat: 'mm/dd/yy',  minDate: 0});
            $('#datepickerSettle').datepicker({altField: '#altFormat_start', onSelect: function(dateStr) {
                    $('#datepickerDeparture').datepicker('option', 'minDate', $(this).datepicker('getDate') || 0);
                }});
            $('#datepickerDeparture').datepicker({altField: '#altFormat_end', onSelect: function(dateStr) {
                    $('#datepickerSettle').datepicker('option', 'maxDate', $(this).datepicker('getDate'));
                }});
        });

    </script>
</head>
<main class="make-an-order-form">
    <div style="margin-top: 270px;padding-bottom: 165px" class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">

                    <div class="card-header">
                        <fmt:message key="make.an.order" bundle="${lang}"/>

                        <h6 style="color: green;"></h6>
                        <h2 : style="color: green"><%=ServletUtility.getSuccessMessage(request)%></h2>
                        <H2 : style="color: red"><%=ServletUtility.getErrorMessage(request)%></H2>

                    </div>
                    <div class="card-body">

                        <form action="MakeAnOrderServlet?idUser=<%=userId%>&idRoom=<%=request.getParameter("idRoom")%>" method="post">
                            <%if (request.getParameter("operation") == null){%>
                            <div class="form-group row">
                                <label for="selectGuests" class="col-md-4 col-form-label text-md-right"><fmt:message key="amount.of.guests" bundle="${lang}"/></label>
                                <div class="col-md-6">
                                <select name="selectGuests" id="SelectGuests" required>
                                    <option disabled selected value><fmt:message key="select.an.option" bundle="${lang}"/></option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>

                                </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="selectTypeRoom" class="col-md-4 col-form-label text-md-right"><fmt:message key="type.of.apartments" bundle="${lang}"/></label>
                                <div class="col-md-6">
                                    <select name="selectTypeRoom" id="selectTypeRoom" required>
                                        <option disabled selected value><fmt:message key="select.an.option" bundle="${lang}"/></option>
                                        <option value="luxe">Luxe</option>
                                        <option value="comfort">Comfort</option>
                                        <option value="basic">Basic</option>
                                        <option value="econome">Econome</option>
                                    </select>
                                </div>
                            </div>
                            <%}%>
                            <div class="form-group row">
                                <label for="datepickerSettle" class="col-md-4 col-form-label text-md-right"><fmt:message key="date.of.settlement" bundle="${lang}"/></label>
                                <div class="col-md-6">
                                    <label for="datepickerSettle"></label>
                                    <input type="text"  id="datepickerSettle" name="datepickerSettle" pattern="^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="datepickerDeparture" class="col-md-4 col-form-label text-md-right" ><fmt:message key="date.of.departure" bundle="${lang}"/></label>
                                <div class="col-md-6">
                                    <label for="datepickerDeparture"></label>
                                    <input type="text"  id="datepickerDeparture" name="datepickerDeparture" pattern="^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$" required>
                                </div>
                            </div>
                            <div class="col-md-6 offset-md-4">
                                <input type="submit" class="btn btn-primary" name="operation" value="<fmt:message key="make.an.order" bundle="${lang}"/>">
                                <a href="" class="btn btn-link"> <fmt:message key="back" bundle="${lang}"/> </a>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>