package com.epam.student.krynytskyi.filter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class LocaleManagerCookie implements LocaleManager {
    private int cookieLifetime;

    public LocaleManagerCookie(int cookieLifetime) {
        this.cookieLifetime = cookieLifetime;
    }

    @Override
    public void setLocale(Locale locale, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("lang", locale.getLanguage());
        cookie.setMaxAge(cookieLifetime);
        cookie.setPath(request.getServletContext().getContextPath());
        response.addCookie(cookie);
    }

    @Override
    public Locale getLocale(HttpServletRequest request) {
        Locale locale = null;
        Cookie[] cookies = request.getCookies();

        if(cookies == null)
            return locale;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("lang")) {
                locale = new Locale(cookie.getValue());
            }
        }

        return locale;
    }
}
