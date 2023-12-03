package com.train.hotel.controller.filters;
import com.train.hotel.controller.Links;
import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@WebFilter(filterName = "AuthenticationFilter", urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

    private static final Logger log = Logger.getLogger(AuthenticationFilter.class);


    List<String> noRegNeeded;
    List<String> userAccessPages;
    private static final String AUTH_PAGE = Links.LOGIN_SERVLET;
    private static final String LOGIN_PAGE = "/Login";
    private static final String REG_PAGE = "/Registration";
    private static final String START_PAGE = "/Welcome";
    private static final String ROOMS_PAGE = "/Rooms";

    private static final String PAYMENT_PAGE = "/Payment";
    private static final String MAKE_ORDER_PAGE = "/MakeAnOrder";
    private static final String USER_ORDERS = "/UserOrders";



    public void destroy() {
    }

    /**
     * Redirects not authorised users from all pages, except of login, register, start and
     * page with authentication error message to this page.
     * @param req
     * @param resp
     * @param chain
     * @throws ServletException
     * @throws IOException
     */

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);


        String URI = request.getRequestURI();
        boolean isNoRegNeeded = false;
        for (String s : noRegNeeded) {
            if (URI.contains(s) && (!s.equals("/")) || noRegNeeded.contains(URI)) {
                isNoRegNeeded = true;
            }
        }
        boolean isUserAccessPages = false;
        for (String s : userAccessPages) {
            if (URI.contains(s) && (!s.equals("/")) || userAccessPages.contains(URI)) {
                isUserAccessPages = true;
            }
        }
        if (!isNoRegNeeded && (session == null || session.getAttribute("role") == null)){
            response.sendRedirect(AUTH_PAGE);
            log.debug("Authentication filter : User not login");
        }else if (!isUserAccessPages && (session != null && session.getAttribute("role").equals("user"))){
            response.sendRedirect(Links.WELCOME_SERVLET);
            log.debug("users don't have access to this page");
        } else
            chain.doFilter(req, resp); // Logged-in user found or no reg needed




    }

    /**
     * Sets up list of ignored by filter pages
     * @param config
     * @throws ServletException
     */
    public void init(FilterConfig config) throws ServletException {
        noRegNeeded = new ArrayList<>(5);
        noRegNeeded.add(START_PAGE);
        noRegNeeded.add(REG_PAGE);
        noRegNeeded.add(LOGIN_PAGE);
        noRegNeeded.add(AUTH_PAGE);
        noRegNeeded.add(ROOMS_PAGE);

        userAccessPages = new ArrayList<>(8);
        userAccessPages.add(START_PAGE);
        userAccessPages.add(REG_PAGE);
        userAccessPages.add(LOGIN_PAGE);
        userAccessPages.add(AUTH_PAGE);
        userAccessPages.add(ROOMS_PAGE);
        userAccessPages.add(PAYMENT_PAGE);
        userAccessPages.add(MAKE_ORDER_PAGE);
        userAccessPages.add(USER_ORDERS);

    }

}


