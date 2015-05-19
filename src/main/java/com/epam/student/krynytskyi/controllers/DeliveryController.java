package com.epam.student.krynytskyi.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delivery")
public class DeliveryController extends HttpServlet {

    public static final String DELIVERY_JSP_PATH = "/WEB-INF/pages/delivery.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(DELIVERY_JSP_PATH).forward(req, resp);
    }
}
