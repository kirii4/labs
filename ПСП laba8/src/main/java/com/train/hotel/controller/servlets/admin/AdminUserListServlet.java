package com.train.hotel.controller.servlets.admin;

import com.train.hotel.controller.Links;
import com.train.hotel.model.dao.UserDao;
import com.train.hotel.model.entity.User;
import com.train.hotel.utility.DataUtility;
import com.train.hotel.utility.ServletUtility;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminUserListServlet", value = "/AdminUserListServlet")
public class AdminUserListServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(AdminUserListServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("AdminUserListServlet");
        long id= DataUtility.getLong(request.getParameter("id"));
        if(id>0) {
            UserDao.delete(id);
            ServletUtility.setSuccessMessage(DataUtility.locale_message("data.deleted.successfully", request.getSession().getAttribute("lang")), request);
        }

        int page = 1;
        int recordsPerPage = 8;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        UserDao user = new UserDao();
        List<User> list = user.viewAllUsers((page-1)*recordsPerPage, recordsPerPage);
        int noOfRecords = user.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("userList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        ServletUtility.forward(Links.ADMIN_USER_LIST_VIEW, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
