package com.train.hotel.controller.listener;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@WebListener // register it as you wish
public class ContainerContextClosedHandler implements ServletContextListener {
    private static final Logger log = Logger.getLogger(ContainerContextClosedHandler.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // nothing to do
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.debug("WebListener works");
        Enumeration<Driver> drivers = DriverManager.getDrivers();

        Driver driver;

        // clear drivers
        while(drivers.hasMoreElements()) {
            try {
                driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);

            } catch (SQLException ex) {
                // deregistration failed, might want to do something, log at the very least
            }
        }
        // MySQL driver leaves around a thread. This static method cleans it up.
        AbandonedConnectionCleanupThread.checkedShutdown();



    }

}