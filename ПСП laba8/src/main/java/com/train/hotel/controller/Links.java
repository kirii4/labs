package com.train.hotel.controller;

public interface Links {
    String APP_CONTEXT = "/hotel";
    String PAGE_FOLDER = "/jsp";

    String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
    String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";
    String WELCOME_VIEW = PAGE_FOLDER + "/welcome.jsp";
    String ADMIN_USER_LIST_VIEW = PAGE_FOLDER + "/AdminUserList.jsp";
    String ROOMS_VIEW = PAGE_FOLDER + "/rooms.jsp";
    String MAKE_AN_ORDER_VIEW = PAGE_FOLDER + "/MakeAnOrder.jsp";
    String ORDER_LIST_VIEW = PAGE_FOLDER + "/OrderList.jsp";
    String USER_ORDER_LIST_VIEW = PAGE_FOLDER + "/UserOrders.jsp";
    String PAYMENT_VIEW = PAGE_FOLDER + "/PaymentPage.jsp";


    String LOGIN_SERVLET = APP_CONTEXT + "/LoginServlet";
    String REGISTRATION_SERVLET = APP_CONTEXT + "/RegistrationServlet";
    String WELCOME_SERVLET = APP_CONTEXT + "/WelcomeServlet";
    String ADMIN_USER_LIST_SERVLET = APP_CONTEXT + "/AdminUserListServlet";
    String ROOMS_SERVLET = APP_CONTEXT + "/RoomsServlet";
    String MAKE_AN_ORDER_SERVLET = APP_CONTEXT + "/MakeAnOrderServlet";
    String ORDER_LIST_SERVLET = APP_CONTEXT + "/OrderListServlet";
    String USER_ORDER_LIST_SERVLET = APP_CONTEXT + "/UserOrdersServlet";
    String PAYMENT_SERVLET = APP_CONTEXT + "/PaymentServlet";







}
