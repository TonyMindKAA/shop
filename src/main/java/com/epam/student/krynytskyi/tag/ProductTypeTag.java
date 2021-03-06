package com.epam.student.krynytskyi.tag;

import com.epam.student.krynytskyi.beans.ProductFacetQueryData;
import com.epam.student.krynytskyi.entity.ProductType;
import com.epam.student.krynytskyi.service.TypeService;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductTypeTag extends SimpleTagSupport {
    private static final Logger log = Logger.getLogger(ProductTypeTag.class);

    @Override
    public void doTag() throws JspException, IOException {
        ProductFacetQueryData productFormBean = (ProductFacetQueryData) getJspContext().findAttribute("productFormBean");
        List<ProductType> types = getAllTypes();
        List<String> checkedTypes = productFormBean.getProductTypes();

        StringBuilder html = new StringBuilder();
        html.append(" <div class=\"brands_products\"><!--brands_products-->\n" +
                "                            <h2>Types</h2>\n" +
                "                            <div class=\"panel-group category-products\" id=\"accordian\"><!--category-productsr-->\n");
        for (int i = 0; i < types.size(); i++) {
            if (checkedTypes.contains(types.get(i).getType()))
                html.append("<div class=\"panel panel-default\">\n" +
                        "                                    <div class=\"panel-heading\">\n" +
                        "                                        <h4 class=\"panel-title\">" +
                        "                                        <input name=\"type\" value=\"" + types.get(i).getType() + "\" type=\"checkbox\" checked>" +
                        "                                        <a>" + types.get(i).getType().toUpperCase() + "</a></h4>\n" +
                        "                                    </div>\n" +
                        "                                </div>");
            else
                html.append("<div class=\"panel panel-default\">\n" +
                        "                                    <div class=\"panel-heading\">\n" +
                        "                                        <h4 class=\"panel-title\">" +
                        "                                        <input name=\"type\" value=\"" + types.get(i).getType() + "\" type=\"checkbox\">" +
                        "                                        <a>" + types.get(i).getType().toUpperCase() + "</a></h4>\n" +
                        "                                    </div>\n" +
                        "                                </div>");
        }
        html.append("                            </div>\n" +
                "                             <!--/category-productsr-->\n" +
                "                        </div>");
        getJspContext().getOut().write(html.toString());
    }

    private List<ProductType> getAllTypes() {
        TypeService typeService = (TypeService) getJspContext().findAttribute("typeService");
        try {
            return typeService.getAll();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }
}