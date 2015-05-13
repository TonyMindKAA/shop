package com.epam.student.krynytskyi.tag;

import com.epam.student.krynytskyi.beans.ProductFormBean;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;


public class PaginationTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        Integer productNumber = (Integer) getJspContext().findAttribute("productNumber");
        ProductFormBean productFormBean = (ProductFormBean) getJspContext().findAttribute("productFormBean");
        int currentPage = Integer.parseInt(productFormBean.getCurrentPage());
        int numberItems = Integer.parseInt(productFormBean.getNumberItems());
        int pageNumbers = (Math.ceil(productNumber / numberItems) > 0) ? (int) Math.ceil(productNumber / numberItems) : 1;


        if (currentPage > 2 && pageNumbers - currentPage - 2 > 0) {
            getJspContext().getOut().write(
                    " <ul class=\"pagination\">" +
                            " <li><a class=\"paginationLi\">1</a></li>" +
                            " <li><a class=\"paginationLi\">...</a></li>" +
                            " <li><a class=\"paginationLi\">" + (currentPage - 1) + "</a></li>" +
                            " <li class=\"active\"><a class=\"paginationLi\">" + (currentPage) + "</a></li>" +
                            " <li><a class=\"paginationLi\">" + (currentPage + 1) + "</a></li>" +
                            " <li><a class=\"paginationLi\">...</a></li>" +
                            " <li><a class=\"paginationLi\">" + pageNumbers + "</a></li>" +
                            "</ul>");
            return;
        }
        if (currentPage > 4 && pageNumbers > 4) {
            String html = " <ul class=\"pagination\">";
            for (int i = 1; i <= 4; i++) {
                if (i == currentPage)
                    html += "<li class=\"active\"><a class=\"paginationLi\">" + (currentPage) + "</a></li>";
                html += "<li><a class=\"paginationLi\">" + i + "</a></li>";
            }
            html += " <li><a class=\"paginationLi\">...</a></li><li><a class=\"paginationLi\">" + pageNumbers + "</a></li></ul>";
            getJspContext().getOut().write(html);
        } else {
            String html = " <ul class=\"pagination\">";
            for (int i = 1; i <= pageNumbers; i++) {
                if (i == currentPage)
                    html += "<li class=\"active\"><a class=\"paginationLi\">" + (currentPage) + "</a></li>";
                html += "<li><a class=\"paginationLi\">" + i + "</a></li>";
            }
            html += "</ul>";
            getJspContext().getOut().write(html);
        }
    }
}
