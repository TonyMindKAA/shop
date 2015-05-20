package com.epam.student.krynytskyi.controllers;

import com.epam.student.krynytskyi.beans.card.CardInfo;
import com.epam.student.krynytskyi.containers.CardContainer;
import com.epam.student.krynytskyi.util.CardManger;
import com.epam.student.krynytskyi.util.bean.creator.CardInfoCreator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/card/info")
public class ModificationCardController extends HttpServlet {
    private static final Logger log = Logger.getLogger(ModificationCardController.class);
    public static final String CARD_INFO_SESSION_ATTRIBUTE = "cardInfo";
    private CardContainer card;
    private CardInfoCreator cardInfoCreator = new CardInfoCreator();
    private CardManger cardManger = new CardManger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        card = (CardContainer) req.getSession().getAttribute("card");
        if (cardManger.manage(req, card)) {
            CardInfo cardInfo = cardInfoCreator.create(card);
            req.getSession().setAttribute(CARD_INFO_SESSION_ATTRIBUTE, cardInfo);
        } else {
            log.error("Can not manage card!");
            resp.setStatus(500);
        }
    }
}
