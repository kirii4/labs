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

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = "" + request.getParameter("operation");
        try{
            if(operation.equalsIgnoreCase("logout")){
                HttpSession session=request.getSession(false);
                session.invalidate();

            }
        }catch (Exception e){
            e.printStackTrace();
            log.error(e);
        }
        ServletUtility.forward(Links.LOGIN_VIEW, request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login=request.getParameter("login");
        String pwd=request.getParameter("password");

        User user = UserDao.UserLogin(login,pwd);

        if(user != null) {
            HttpSession session=request.getSession(true);
            //Set attribute for session
            session.setAttribute("idUser", user.getId());
            session.setAttribute("user", user.getFirstName());
            session.setAttribute("role", user.getRole());
            ServletUtility.redirect(Links.WELCOME_SERVLET, request, response);
        }else {
            ServletUtility.setErrorMessage(DataUtility.locale_message("invalid.user", request.getSession().getAttribute("lang")), request);
            ServletUtility.forward(Links.LOGIN_VIEW, request, response);
        }
    }

}
