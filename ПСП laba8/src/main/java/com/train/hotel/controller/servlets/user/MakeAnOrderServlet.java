package com.train.hotel.controller.servlets.user;

import com.train.hotel.controller.Links;
import com.train.hotel.model.entity.Order;
import com.train.hotel.model.dao.OrderDao;
import com.train.hotel.utility.DataUtility;
import com.train.hotel.utility.ServletUtility;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "MakeAnOrderServlet", value = "/MakeAnOrderServlet")
public class MakeAnOrderServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(MakeAnOrderServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletUtility.forward(Links.MAKE_AN_ORDER_VIEW, request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDate = LocalDate.now();
        String dateToday = dtf.format(localDate);

        String paymentStatus = "waiting to approve";
        String typeOfRoom = "" + request.getParameter("selectTypeRoom");
        if(typeOfRoom.equalsIgnoreCase("null"))
            paymentStatus = "need to pay";


        Order order = new Order.OrderBuilder()
                .setIdUser((Long)request.getSession().getAttribute("idUser"))
                .setDateOfSettlement(DataUtility.getDate(request.getParameter("datepickerSettle")))
                .setDateOfDeparture(DataUtility.getDate(request.getParameter("datepickerDeparture")))
                .setIdRoom(DataUtility.getLong(request.getParameter("idRoom")))
                .setPaymentStatus(paymentStatus)
                .setIdOrder(DataUtility.getLong(request.getParameter("idOrder")))
                .setDateOfCreateOrder(DataUtility.getDate(dateToday))
                .setTypeOfRoom(typeOfRoom)
                .setAmountOfGuests(DataUtility.getLong(request.getParameter("selectGuests")))
                .build();
        long i = OrderDao.addOrder(order);

        if(i>0) {
            ServletUtility.setSuccessMessage(DataUtility.locale_message("success", request.getSession().getAttribute("lang")), request);

        }else {
            ServletUtility.setErrorMessage(DataUtility.locale_message("something.bad", request.getSession().getAttribute("lang")), request);
        }
        ServletUtility.redirect(Links.USER_ORDER_LIST_SERVLET, request,response);
    }
}
