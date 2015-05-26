package com.epam.student.krynytskyi.filter;

import com.epam.student.krynytskyi.entity.User;
import com.epam.student.krynytskyi.util.bean.creator.AnonymousUserCreator;
import com.epam.student.krynytskyi.xml.model.RoleXml;
import com.epam.student.krynytskyi.xml.model.Security;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessFilter implements Filter {
    private static final Logger log = Logger.getLogger(AccessFilter.class);
    private AnonymousUserCreator anonymousUserCreator = new AnonymousUserCreator();
    private Security security;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        security = (Security) filterConfig.getServletContext().getAttribute("security");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        request = (HttpServletRequest) servletRequest;
        response = (HttpServletResponse) servletResponse;
        setLastUrlToSession();
        User user = getUserFromSession();
        if (isAccess(user))
            filterChain.doFilter(servletRequest, servletResponse);
    }

    private void setLastUrlToSession() {
        String lastUrl = getLastUrl(request.getHeader("referer"));
        if(lastUrl != null && !lastUrl.equals("login")){
            request.getSession().setAttribute("lastUrl", lastUrl);
        }
    }

    private String getLastUrl(String lastUrl) {
        if (lastUrl != null) {
            String url = lastUrl.replaceAll("http://", "");
            String substring = url.substring(url.indexOf("/") + 1);
            int startURl = substring.indexOf("/");
            return substring.substring(startURl+1);
        }
        return lastUrl;
    }

    private boolean isAccess(User user) throws IOException {
        for (RoleXml roleXml : security.getRoleXmls()) {
            if (roleXml.getRole().equals(user.getRole().getRole())) {
                for (String accessUrl : roleXml.getUrls()) {
                    if (checkUriCoincidence(accessUrl, request.getRequestURI())) {
                        response.sendRedirect(roleXml.getRedirectTo());
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private User getUserFromSession() {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            user = anonymousUserCreator.create();
        }
        return user;
    }

    boolean checkUriCoincidence(String unAcccess, String requestUri) {
        boolean matches = requestUri.matches(unAcccess);
        return matches;
    }

    @Override
    public void destroy() {
    }
}
