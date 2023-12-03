package com.train.hotel.controller.servlets.user;

import com.train.hotel.controller.Links;
import com.train.hotel.model.dao.OrderDao;
import com.train.hotel.model.entity.UserOrderList;
import com.train.hotel.utility.DataUtility;
import com.train.hotel.utility.ServletUtility;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserOrdersServlet", value = "/UserOrdersServlet")
public class UserOrdersServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(UserOrdersServlet.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lang = (String) request.getSession().getAttribute("lang");


        long idOrder = DataUtility.getLong(request.getParameter("idOrder"));
        long idRoom = DataUtility.getLong(request.getParameter("idRoom"));

        String operation = "" + DataUtility.getString(request.getParameter("operation"));
        if(idOrder > 0 && operation.equalsIgnoreCase("null"))
            OrderDao.updateStatusWithRoom(idOrder,idRoom, "need to pay", "потрібна оплата", "booked", "заброньований");
        if(operation.equalsIgnoreCase("cancel"))
            OrderDao.DeleteOrder(idOrder);
        if(operation.equalsIgnoreCase("payed"))
            OrderDao.updateStatus(idOrder, "payed", "сплачений");


        List<UserOrderList> list= OrderDao.UserOrderlist((Long)request.getSession().getAttribute("idUser") , lang);

        ServletUtility.setList(list, request);
        ServletUtility.forward(Links.USER_ORDER_LIST_VIEW, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String lang = (String) request.getSession().getAttribute("lang");
        List<UserOrderList> list= OrderDao.UserOrderlist(DataUtility.getLong(request.getParameter("idUser")), lang);

        ServletUtility.setList(list, request);
        ServletUtility.forward(Links.USER_ORDER_LIST_VIEW, request, response);
    }
}
