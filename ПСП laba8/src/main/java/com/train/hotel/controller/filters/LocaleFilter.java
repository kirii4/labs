package com.train.hotel.controller.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LocaleFilter", urlPatterns = "/*")
public class LocaleFilter implements Filter {

    private static final Logger log = Logger.getLogger(LocaleFilter.class);

    @Override
    public void destroy() {
    }
    @Override
    public void init(FilterConfig config) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.debug("Locale filter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        String locale= request.getParameter("locale");
        String defaultLocale = "en";

        if(locale != null){
            session.setAttribute("lang", locale);
        }
        else if (session.getAttribute("lang")==null){
            session.setAttribute("lang",defaultLocale);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
