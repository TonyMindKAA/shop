package com.epam.student.krynytskyi.tag;

import com.epam.student.krynytskyi.beans.ProductFacetQueryData;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by Anton_Krynytskyi on 5/15/2015.
 */
public class ProductTypeTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        ProductFacetQueryData productFormBean = (ProductFacetQueryData) getJspContext().findAttribute("productFormBean");
        // typeService
        // manufactureService

        StringBuilder html = new StringBuilder();
        html.append(" <div class=\"brands_products\"><!--brands_products-->\n" +
                "                            <h2>Brands</h2>\n" +
                "                            <div class=\"panel-group category-products\" id=\"accordian\"><!--category-productsr-->\n");
        for (int i = 0; i < 3; i++) {
            html.append("<div class=\"panel panel-default\">\n" +
                    "                                    <div class=\"panel-heading\">\n" +
                    "                                        <h4 class=\"panel-title\"><input name=\"manufactures\" value=\"NOKIA\" type=\"checkbox\" <c:out\n" +
                    "                                                value=\"${empty productFormBean.nokia ? ' ' : 'checked'}\"/>> <a href=\"#\">\n" +
                    "                                            NOKIA </a></h4>\n" +
                    "                                    </div>\n" +
                    "                                </div>");
        }



    }
}