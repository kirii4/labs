package com.train.hotel.controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import org.apache.log4j.Logger;

@WebFilter(filterName = "EncodingFilter", urlPatterns = "/*")
public class EncodingFilter implements Filter {
    private static final Logger log = Logger.getLogger(EncodingFilter.class);
    private String encoding = "UTF-8";

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        log.debug("Encoding filter");
        req.setCharacterEncoding(encoding);
        chain.doFilter(req, resp);
    }
    @Override
    public void init(FilterConfig config){
        String encodingParam = config.getInitParameter("encoding");
        if (encodingParam != null) {
            encoding = encodingParam;
        }

    }

}