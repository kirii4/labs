package com.train.hotel.controller.servlets.user;

import com.train.hotel.controller.Links;
import com.train.hotel.model.entity.User;
import com.train.hotel.model.dao.UserDao;
import com.train.hotel.utility.DataUtility;
import com.train.hotel.utility.ServletUtility;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", value = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(RegistrationServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = DataUtility.getLong(request.getParameter("id"));

        if(id>0) {
            User bean = UserDao.FindByPk(id);
            request.setAttribute("bean", bean);
        }

        ServletUtility.forward(Links.USER_VIEW, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User.UserBuilder()
                .setFirstName(request.getParameter("firstName"))
                .setLastName(request.getParameter("lastName"))
                .setLogin(request.getParameter("login"))
                .setPassword(request.getParameter("password"))
                .setDob(DataUtility.getDate(request.getParameter("dob")))
                .setMobileNo(request.getParameter("mobile"))
                .setId(DataUtility.getLong(request.getParameter("id")))
                .build();

        if(user.getId()>0) {
            //To Update the records
            long i = UserDao.UpdateUser(user);
            if(i>0) {
                ServletUtility.setSuccessMessage(DataUtility.locale_message("user.success.update", request.getSession().getAttribute("lang")), request);
            }else {
                ServletUtility.setErrorMessage(DataUtility.locale_message("something.bad", request.getSession().getAttribute("lang")), request);
            }

        }else {
            //To add the new record
            long i = UserDao.addUser(user);
            if(i>0) {
                ServletUtility.setSuccessMessage(DataUtility.locale_message("user.register.successfully", request.getSession().getAttribute("lang")), request);
            }else {
                ServletUtility.setErrorMessage(DataUtility.locale_message("something.bad", request.getSession().getAttribute("lang")), request);
            }
        }
        ServletUtility.redirect(Links.LOGIN_SERVLET, request, response);
    }

}
