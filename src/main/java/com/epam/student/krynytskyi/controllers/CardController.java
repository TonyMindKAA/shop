package com.epam.student.krynytskyi.controllers;


import com.epam.student.krynytskyi.containers.CardContainer;
import com.epam.student.krynytskyi.service.ProductService;
import com.epam.student.krynytskyi.util.JSONSerializer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/card")
public class CardController extends HttpServlet {

    private static final Logger log = Logger.getLogger(CardController.class);
    private JSONSerializer jsonSerializer = new JSONSerializer();
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = (ProductService) getServletContext().getAttribute("");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/card.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CardContainer card = (CardContainer) req.getSession().getAttribute("card");
        if(card == null){
            card = new CardContainer();
        }



        String s = req.getParameter("id");
        System.out.println(s);

        Integer product = new Integer("23");
        print(resp, product);
    }

    protected void print(HttpServletResponse response, Object o) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(jsonSerializer.getContentType());
        try {
            jsonSerializer.serialize(response.getOutputStream(), o);
        } catch (Exception e) {
            log.warn("Cannot serialize object", e);
            response.setContentType("text/html");
            response.sendError(500);
        }
    }
}