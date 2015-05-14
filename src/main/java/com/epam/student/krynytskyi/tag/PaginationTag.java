package com.epam.student.krynytskyi.tag;

import com.epam.student.krynytskyi.beans.product.ProductFormBean;
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
        ProductService productService = getProductService();
        int productNumber = getProductNumber(productFormBean, productService);
        int currentPage = Integer.parseInt(productFormBean.getCurrentPage());
        int numberItems = Integer.parseInt(productFormBean.getNumberItems());
        int pageNumbers = countPageNumbers(productNumber, numberItems);
        renderPaginationBlock(currentPage, pageNumbers, productNumber );
    }

    private int countPageNumbers(int productNumber, int numberItems) {
        double pages = Math.ceil((double)productNumber / (double)numberItems);
        return (pages > 0) ? (int)  pages : 1;
    }

    private void renderPaginationBlock(int currentPage, int pageNumbers, int productNumber ) throws IOException {
        if (pageNumbers >= currentPage && productNumber  != 0) {
            if (currentPage > 2 && (pageNumbers - currentPage) - 2 > 0) {
                renderFirstFourPage(currentPage, pageNumbers);
            } else if (currentPage > 4) {
                renderMiddleRange(currentPage, pageNumbers);
            } else {
                renderBeforeFourthPage(currentPage, pageNumbers);
            }
        }
    }

    private int getProductNumber(ProductFormBean productFormBean, ProductService productService) {
        int productNumber = 0;
        try {
            productNumber = productService.countProducts(productFormBean);
        } catch (Exception e) {
            log.debug("Can not get product number. " + e.getMessage());
        }
        return productNumber;
    }

    private ProductService getProductService() {
        PageContext context = (PageContext) getJspContext();
        ServletRequest request = (HttpServletRequest) context.getRequest();
        ServletContext servletContext = request.getServletContext();
        return (ProductService) servletContext.getAttribute("productService");
    }

    private void renderMiddleRange(int currentPage, int pageNumbers) throws IOException {
        String html = " <ul class=\"pagination\"><li><a class=\"paginationLi\">1</a></li><li><a class=\"paginationLi\">...</a></li>";
        for (int i = pageNumbers - 3; i <= pageNumbers; i++) {
            if (i == currentPage)
                html += "<li class=\"active\"><a class=\"paginationLi\">" + (currentPage) + "</a></li>";
            else
                html += "<li><a class=\"paginationLi\">" + i + "</a></li>";
        }
        getJspContext().getOut().write(html);
    }

    private void renderFirstFourPage(int currentPage, int pageNumbers) throws IOException {
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

    private void renderBeforeFourthPage(int currentPage, int pageNumbers) throws IOException {
        String html = " <ul class=\"pagination\">";
        for (int i = 1; i <= pageNumbers-1; i++) {
            if (i == currentPage)
                html += "<li class=\"active\"><a class=\"paginationLi\">" + (currentPage) + "</a></li>";
            else
                html += "<li><a class=\"paginationLi\">" + i + "</a></li>";
        }
        if (pageNumbers - 1 > currentPage)
            html += " <li><a class=\"paginationLi\">...</a></li><li><a class=\"paginationLi\">" + pageNumbers + "</a></li></ul>";
        if (pageNumbers >= currentPage && !(pageNumbers - 1 > currentPage)) {
            if(pageNumbers == currentPage)
                html += " <li class=\"active\"><a class=\"paginationLi\">" + pageNumbers + "</a></li></ul>";
            else
                html += " <li><a class=\"paginationLi\">" + pageNumbers + "</a></li></ul>";
        }
        getJspContext().getOut().write(html);
    }
}
