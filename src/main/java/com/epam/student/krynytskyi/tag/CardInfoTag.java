package com.epam.student.krynytskyi.tag;

import com.epam.student.krynytskyi.beans.card.CardInfo;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class CardInfoTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        CardInfo cardInfo = (CardInfo) getJspContext().findAttribute("cardInfo");
        if (cardInfo != null) {
            getJspContext().getOut().write("<a href=\"card\" id=\"car-info-state\">" +
                    "<i class=\"fa fa-shopping-cart\"></i><b>" +
                    "  Cart(" + cardInfo.getProductsNumber() + ")" +
                    " total cost: " + cardInfo.getTotalCost() + " uah</b></a>");
        } else {
            getJspContext().getOut().write("<a href=\"card\" id=\"car-info-state\">" +
                    "<i class=\"fa fa-shopping-cart\"></i>" +
                    "<b>Cart(0) total cost: 0 uah</b>" +
                    "</a>");
        }
    }
}