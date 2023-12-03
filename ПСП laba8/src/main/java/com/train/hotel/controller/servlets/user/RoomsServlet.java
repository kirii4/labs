package com.train.hotel.controller.servlets.user;

import com.train.hotel.controller.Links;
import com.train.hotel.model.dao.RoomDao;
import com.train.hotel.model.entity.Room;
import com.train.hotel.utility.ServletUtility;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RoomsServlet", value = "/RoomsServlet")
public class RoomsServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(RoomsServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lang = (String) request.getSession().getAttribute("lang");

        List<Room> list= RoomDao.list("id_class_of_room", lang);

        ServletUtility.setList(list, request);


        ServletUtility.forward(Links.ROOMS_VIEW, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String lang = (String) request.getSession().getAttribute("lang");
        String sort = request.getParameter("sort");

        List<Room> list= RoomDao.list(sort, lang);
        ServletUtility.setList(list, request);

        ServletUtility.forward(Links.ROOMS_VIEW, request, response);
    }
}
