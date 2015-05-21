package com.epam.student.krynytskyi.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public interface LocaleManager {
    public void setLocale(Locale locale, HttpServletRequest request, HttpServletResponse response);

    public Locale getLocale(HttpServletRequest request);
}
