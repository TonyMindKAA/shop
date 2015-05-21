package com.epam.student.krynytskyi.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncodingCacheFilter implements Filter {

    public EncodingCacheFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String encoding = request.getServletContext().getInitParameter(Constants.ENCODING);

        if (request.getCharacterEncoding() == null)
            request.setCharacterEncoding(encoding);

        response.setCharacterEncoding(encoding);
        response.setHeader("cache-response-directive", "no-cache");

        chain.doFilter(request, response);
    }

}