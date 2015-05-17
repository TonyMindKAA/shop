package com.epam.student.krynytskyi.tag;

import com.epam.student.krynytskyi.beans.ProductFacetQueryData;
import com.epam.student.krynytskyi.entity.Manufacture;
import com.epam.student.krynytskyi.service.ManufactureService;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManufactureTag  extends SimpleTagSupport {
    private static final Logger log = Logger.getLogger( ManufactureTag.class);

    @Override
    public void doTag() throws JspException, IOException {
        ProductFacetQueryData productFormBean = (ProductFacetQueryData) getJspContext().findAttribute("productFormBean");
        List<Manufacture> manufacture = getAllManufactures();
        List<String> checkedManufactures = productFormBean.getProductManufactures();

        StringBuilder html = new StringBuilder();
        html.append(" <div class=\"brands_products\"><!--brands_products-->\n" +
                "                            <h2>Brands</h2>\n" +
                "                            <div class=\"panel-group category-products\" id=\"accordian\"><!--category-productsr-->\n");
        for (int i = 0; i < manufacture.size(); i++) {
            if (checkedManufactures.contains(manufacture.get(i).getManufacture()))
                html.append("<div class=\"panel panel-default\">\n" +
                        "                                    <div class=\"panel-heading\">\n" +
                        "                                        <h4 class=\"panel-title\">" +
                        "                                        <input name=\"manufacture\" value=\"" + manufacture.get(i).getManufacture() + "\" type=\"checkbox\" checked>" +
                        "                                        <a>" + manufacture.get(i).getManufacture().toUpperCase() + "</a></h4>\n" +
                        "                                    </div>\n" +
                        "                                </div>");
            else
                html.append("<div class=\"panel panel-default\">\n" +
                        "                                    <div class=\"panel-heading\">\n" +
                        "                                        <h4 class=\"panel-title\">" +
                        "                                        <input name=\"manufacture\" value=\"" + manufacture.get(i).getManufacture() + "\" type=\"checkbox\">" +
                        "                                        <a>" + manufacture.get(i).getManufacture().toUpperCase() + "</a></h4>\n" +
                        "                                    </div>\n" +
                        "                                </div>");
        }
        html.append("                            </div>\n" +
                "                             <!--/category-productsr-->\n" +
                "                        </div>");
        getJspContext().getOut().write(html.toString());
    }

    private List<Manufacture> getAllManufactures() {
        ManufactureService manufactureService = (ManufactureService) getJspContext().findAttribute("manufactureService");
        try {
            return manufactureService.getAll();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }
}