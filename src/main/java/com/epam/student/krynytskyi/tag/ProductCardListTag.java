package com.epam.student.krynytskyi.tag;

import com.epam.student.krynytskyi.beans.card.CardItem;
import com.epam.student.krynytskyi.containers.CardContainer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

public class ProductCardListTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        CardContainer card = (CardContainer) getJspContext().findAttribute("card");
        StringBuffer html = new StringBuffer("\t\t\t<div class=\"table-responsive cart_info\">\n" +
                "\t\t\t\t<table class=\"table table-condensed\">\n" +
                "\t\t\t\t\t<thead>\n" +
                "\t\t\t\t\t<tr class=\"cart_menu\">\n" +
                "\t\t\t\t\t\t<td class=\"image\">Item</td>\n" +
                "\t\t\t\t\t\t<td class=\"description\"></td>\n" +
                "\t\t\t\t\t\t<td class=\"price\">Price</td>\n" +
                "\t\t\t\t\t\t<td class=\"quantity\">Quantity</td>\n" +
                "\t\t\t\t\t\t<td class=\"total\">Total</td>\n" +
                "\t\t\t\t\t\t<td></td>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t</thead>\n" +
                "\t\t\t\t\t<tbody>\n");
        if (card != null) {
            List<CardItem> cardItems = card.getAll();
            for (int i = 0; i < cardItems.size(); i++) {
                long price = cardItems.get(i).getProduct().getPrice();
                long numberItems = cardItems.get(i).getProductNumbers();
                html.append("\t\t\t\t\t\t<tr>\n" +
                        "\t\t\t\t\t\t\t<td class=\"cart_product\">\n" +
                        "\t\t\t\t\t\t\t\t<a href=\"\"><img src=\"resources/products/" + cardItems.get(i).getProduct().getProduct().getImg() + "\" alt=\"\"></a>\n" +
                        "\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t<td class=\"cart_description\">\n" +
                        "\t\t\t\t\t\t\t\t<h4><a>" + cardItems.get(i).getProduct().getProduct().getName() + "</a></h4>\n" +
                        "\t\t\t\t\t\t\t\t<p>Web ID: " + cardItems.get(i).getProduct().getProduct().getId() + "</p>\n" +
                        "\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t<td class=\"cart_price\">\n" +
                        "\t\t\t\t\t\t\t\t<p>" + price + " uah</p>\n" +
                        "\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t<td class=\"cart_quantity\">\n" +
                        "\t\t\t\t\t\t\t\t<div class=\"cart_quantity_button\">\n" +
                        "\t\t\t\t\t\t\t\t\t<a class=\"cart_quantity_up\" href=\"\"> + </a>\n" +
                        "\t\t\t\t\t\t\t\t\t<input class=\"cart_quantity_input\" type=\"text\" name=\"quantity\" value=\"" + numberItems + "\" autocomplete=\"off\" size=\"2\" readonly>\n" +
                        "\t\t\t\t\t\t\t\t\t<a class=\"cart_quantity_down\" href=\"\"> - </a>\n" +
                        "\t\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t<td class=\"cart_total\">\n" +
                        "\t\t\t\t\t\t\t\t<p class=\"cart_total_price\">" + numberItems * price + " uah</p>\n" +
                        "\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t<td class=\"cart_delete\">\n" +
                        "\t\t\t\t\t\t\t\t<a class=\"cart_quantity_delete\" href=\"\"><i class=\"fa fa-times\"></i></a>\n" +
                        "\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t</tr>\n");
            }

            html.append("\t\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t\t<td class=\"cart_product\">\n" +
                    "\t\t\t\t\t\t\t\t<a></a>\n" +
                    "\t\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t\t\t<td class=\"cart_description\">\n" +
                    "\t\t\t\t\t\t\t\t<h4><a><b>In total:</b></a></h4>\n" +
                    "\t\t\t\t\t\t\t\t<p></p>\n" +
                    "\t\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t\t\t<td class=\"cart_price\">\n" +
                    "\t\t\t\t\t\t\t\t<p></p>\n" +
                    "\t\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t\t\t<td class=\"cart_quantity\">\n" +
                    "\t\t\t\t\t\t\t\t<div class=\"cart_quantity_button\">\n" +
                    "\t\t\t\t\t\t\t\t\t<div class=\"cart_quantity_button_card\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<input id=\"cart_quantity_input_result\" class=\"cart_quantity_input\" type=\"text\" name=\"quantity\" value=\"" + card.size() + "\" autocomplete=\"off\" size=\"2\" readonly>\n" +
                    "\t\t\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t\t\t<td class=\"cart_total\">\n" +
                    "\t\t\t\t\t\t\t\t<p id=\"cart_total_price_result\" class=\"cart_total_price\">" + card.calculationPurchases() + " uah</p>\n" +
                    "\t\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t\t\t<td>\n" +
                    "\t\t\t\t\t\t\t\t<a></a>\n" +
                    "\t\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t\t</tr>\n");
            html.append("\t\t\t\t\t</tbody>\n" +
                    "\t\t\t\t</table>\n" +
                    "\t\t\t</div>\n");
            getJspContext().getOut().write(html.toString());
        } else {
            getJspContext().getOut().write("<p>Card is empty</p><a href=\"products\">to products list</a>");
        }
    }
}