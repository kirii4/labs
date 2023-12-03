package com.train.hotel.controller.servlets.admin;

import com.train.hotel.controller.Links;
import com.train.hotel.model.dao.RoomDao;
import com.train.hotel.model.dao.OrderDao;

import com.train.hotel.model.entity.Order;
import com.train.hotel.model.entity.Room;
import com.train.hotel.utility.DataUtility;
import com.train.hotel.utility.ServletUtility;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderListServlet", value = "/OrderListServlet")
public class OrderListServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(OrderListServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Order> list= OrderDao.AdminOrderList();
        ServletUtility.setList(list, request);

        String lang = (String) request.getSession().getAttribute("lang");

        List<Room> listCatagory = RoomDao.listSelect(lang);

        request.setAttribute("listCategory", listCatagory );
        ServletUtility.forward(Links.ORDER_LIST_VIEW, request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        long idRoom = Long.parseLong(request.getParameter("category"));
        long idOrder = Long.parseLong(request.getParameter("idOrder"));

        long i = OrderDao.adminOrderChange(idRoom,idOrder);


        List<Order> list= OrderDao.AdminOrderList();

        ServletUtility.setList(list, request);


        String lang = (String) request.getSession().getAttribute("lang");

        List<Room> listCatagory = RoomDao.listSelect(lang);
        request.setAttribute("listCategory", listCatagory );

        if(i>0) {
            ServletUtility.setSuccessMessage(DataUtility.locale_message("successfully", request.getSession().getAttribute("lang")), request);

        }else {
            ServletUtility.setErrorMessage(DataUtility.locale_message("something.bad", request.getSession().getAttribute("lang")), request);

        }

        ServletUtility.forward(Links.ORDER_LIST_VIEW, request, response);
    }
}
