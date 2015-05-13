package com.epam.student.krynytskyi.tag;

import com.epam.student.krynytskyi.beans.ProductFormBean;
import com.epam.student.krynytskyi.service.ProductService;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;


public class PaginationTag extends SimpleTagSupport {
    private static final Logger log = Logger.getLogger(PaginationTag.class);
    @Override
    public void doTag() throws JspException, IOException {
        ProductFormBean productFormBean = (ProductFormBean) getJspContext().findAttribute("productFormBean");
        PageContext context = (PageContext) getJspContext();
        ServletRequest request = (HttpServletRequest) context.getRequest();
        ServletContext servletContext = request.getServletContext();
        ProductService productService = (ProductService)servletContext.getAttribute("productService");


        //int productNumbers = (Integer) getJspContext().getAttribute("productNumber");

        int productNumber = 0;
        try {
            productNumber = productService.countProducts(productFormBean);
        } catch (Exception e) {
            log.debug("Can not get product number. "+e.getMessage());
        }
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
                else
                    html += "<li><a class=\"paginationLi\">" + i + "</a></li>";
            }
            html += "</ul>";
            getJspContext().getOut().write(html);
        }
    }
}
