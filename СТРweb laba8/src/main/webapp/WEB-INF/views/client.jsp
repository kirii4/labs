<%@ page import="by.ermakov.lab8.model.Ticket" %>
<%@ page import="java.util.List" %>
<%@ page import="by.ermakov.lab8.service.Dao" %>
<%@ page import="by.ermakov.lab8.service.DaoImpl" %>
<%@ page import="by.ermakov.lab8.model.Plane" %>
<%@ page import="by.ermakov.lab8.model.Person" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../css/style.css" />
    <link rel="stylesheet" type="text/css" href="../css/MainFrozen.css">
    <title>Tickets</title>

    <!-- Bootstrap core CSS -->
    <link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css"
          rel="stylesheet">


</head>
<body>
<header>
<nav role="navigation" class="navbar navbar-default">

    <div class="navbar-collapse">
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="LoginServlet">Login</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="LogoutServlet">Logout</a></li>
        </ul>

    </div>

</nav>
    <H2 style="position: absolute; margin-left: 40%; margin-right: 50%; width: max-content; color: coral">Welcome ${name}</H2>
</header>
<div class="container">
    <%! private Dao dao = new DaoImpl(); %>
    <%! private List<Plane> planes; %>
    <table border="3" cellspacing="1" style="min-height: 500px">
        <caption>Список самолетов</caption>
        <tr><td>Номер рейса</td><td>Город вылета</td><td>Город прилета</td><td>Дата вылета</td>
            <td>Компания</td><td>Всего билетов</td><td>Свободно мест</td><td>Купить</td></tr>

        <%
            planes = dao.getPlanes();
            for(Plane plane: planes) {
                String button;
                if (plane.getTicketsNotBooked() >0){
                    button = "<button id='booking' value='"+plane.getId()+"'>Купить билет</button>";
                }else button= "<button id='booking' disabled>Купить билет</button>";
                out.println("<tr id='"+plane.getId()+"'><td>" + plane.getNum() + "</td><td>" + plane.getCityFrom() + "</td><td>" + plane.getCityTo()
                        + "</td><td>" + plane.getDate() + "</td><td>" + plane.getCompany() + "</td><td>"+plane.getTicketsAll()
                        +"</td><td>"+plane.getTicketsNotBooked()+"</td><td>"+button+"</td></tr>");
            };
        %>
    </table>
</div>
<footer class="footer">
    <div class="Polny_Konez">
        2003-2023 Eugen&Co. Powered by <a class="konez4" href="https://ru.wargaming.net/ru">Lesta.org</a> ©<br>Никакие права не защищены. Не воруйте пожалуйта мой сайт!
    </div>
</footer>
<dialog class="modal">
    <p><button id="closeDialog">X</button></p>
    <p id="check"></p>
    <form action="ClientServlet" method="POST">
        <input id="confirm" name="id" value="" type="hidden"/>
        <button type="submit">Подтвердить</button>
    </form>
</dialog>
<script>
    var dialog = document.querySelector('dialog');
    document.querySelectorAll('#booking').forEach(b => b.addEventListener('click', checking));
    function checking(e) {
        var id = this.value;
        var check = document.querySelector('#check');
        var planes = document.getElementById(id);
        planes = planes.childNodes;
        check.textContent = "Вы подтверждаете покупку билета на рейс " + planes[0].textContent +" "+ planes[1].textContent +"-"+planes[2].textContent +" вылет в " +planes[3].textContent +"?";
        document.querySelector("#confirm").value=id;
        dialog.show(); // Показываем диалоговое окно
    }
    document.querySelector('#closeDialog').onclick = function() {
        dialog.close(); // Прячем диалоговое окно
    }
</script>
<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
