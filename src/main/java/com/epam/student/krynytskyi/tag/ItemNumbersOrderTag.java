package com.epam.student.krynytskyi.tag;

import com.epam.student.krynytskyi.beans.ProductFacetQueryData;
import com.epam.student.krynytskyi.db.constant.ProductOrderConst;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class SelectItem {
    private String value;
    private String text;

    public SelectItem(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public SelectItem setValue(String value) {
        this.value = value;
        return this;
    }

    public String getText() {
        return text;
    }

    public SelectItem setText(String text) {
        this.text = text;
        return this;
    }
}

public class ItemNumbersOrderTag extends SimpleTagSupport {
    private static final Logger log = Logger.getLogger(ItemNumbersOrderTag.class);
    @Override
    public void doTag() throws JspException, IOException {
        List<SelectItem> orderItems = getOrderItems();
        List<SelectItem> numbersElementsOnPageItems = getNumbersElementsOnPageItems();

        ProductFacetQueryData productFormBean = (ProductFacetQueryData) getJspContext().findAttribute("productFormBean");

        String html = " <div class=\"col-sm-3\">\n" +
                "                    <div id=\"order-result-castum\">\n" +
                "                        <div id=\"order-shop-main\">\n" +
                "                            <div id=\"order-shop-main-title\">Order:</div>\n" +
                "                            <div id=\"order-shop-main-select\">\n" +
                "                                <select id=\"order-shop-main-select-tag\">";
        for (SelectItem orderItem: orderItems) {
            if(productFormBean.getOrder().equals(orderItem.getValue()))
                html += "<option selected value=\""+orderItem.getValue()+"\">"+orderItem.getText()+"</option>\n";
            else
                html += "<option value=\""+orderItem.getValue()+"\">"+orderItem.getText()+"</option>\n";
        }
        html +="                                </select>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <div id=\"result-shop-main\">\n" +
                "                            <div id=\"result-shop-main-title\">Results:</div>\n" +
                "                            <select id=\"result-shop-select\">";

        for (SelectItem pageItems : numbersElementsOnPageItems ) {
            if(productFormBean.getNumberItems().equals(pageItems.getValue()))
                html += "<option selected value=\""+pageItems.getValue()+"\">"+pageItems.getText()+"</option>\n";
            else
                html += "<option value=\""+pageItems.getValue()+"\">"+pageItems.getText()+"</option>\n";
        }
        html +=" </select>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>";
        log.debug(html);
        getJspContext().getOut().write(html);
    }

    private List<SelectItem> getNumbersElementsOnPageItems() {
        List<SelectItem> numbersElementsOnPageItems = new ArrayList<>();
        numbersElementsOnPageItems.add(new SelectItem("5","5 items/page"));
        numbersElementsOnPageItems.add(new SelectItem("15","15 items/page"));
        numbersElementsOnPageItems.add(new SelectItem("25","25 items/page"));
        return numbersElementsOnPageItems;
    }

    private List<SelectItem> getOrderItems() {
        List<SelectItem> orderItems = new ArrayList<>();
        orderItems.add(new SelectItem(ProductOrderConst.PRICE_LOW_HEIGHT, "by price low to height"));
        orderItems.add(new SelectItem(ProductOrderConst.PRICE_HEIGHT_LOW,"priceHeightLow"));
        orderItems.add(new SelectItem(ProductOrderConst.MANUFACTURE_A_TO_Z,"manufacturerAToZ"));
        orderItems.add(new SelectItem(ProductOrderConst.MANUFACTURE_Z_TO_A,"manufacturerZToA"));
        orderItems.add(new SelectItem(ProductOrderConst.TYPE_A_TO_Z,"typeAToZ"));
        orderItems.add(new SelectItem(ProductOrderConst.TYPE_Z_TO_A,"typeZToA"));
        return orderItems;
    }
}