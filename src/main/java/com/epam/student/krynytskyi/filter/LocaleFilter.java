package com.epam.student.krynytskyi.filter;


import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocaleFilter implements Filter {

    private LocaleManager localeManager;
    // locale lifetime for cookie store mode
    private ServletContext context;
    private List<Locale> availableLocales;
    private Locale defaultLocale;

    private static final Logger log = Logger.getLogger(LocaleFilter.class);

    @Override
    public void init(FilterConfig config) throws ServletException {
        context = config.getServletContext();
        initLocales();
        initLocaleManager();
    }

    private void initLocaleManager() {
        String localeMode = context.getInitParameter(Constants.LOCALE_MODE);
        int cookielifetime = Integer.parseInt(context.getInitParameter(Constants.LOCALE_LIFETIME));

        if (localeMode == null) {
            log.warn("localeMode==null -> will be chosen sessionMode for localeManager");
            localeManager = new LocaleManagerSession();
        }
        else if (localeMode.equals(Constants.LOCALE_MODE_SESSION)) {
            localeManager = new LocaleManagerSession();
        }
        else if (localeMode.equals(Constants.LOCALE_MODE_COOKIE)) {
            localeManager = new LocaleManagerCookie(cookielifetime);
        }
        else if (localeMode != null && localeManager == null) {
            throw new IllegalArgumentException(
                    "Cannot determine correct localeMode");
        }

        log.debug("Selected localeManagerMode ->"
                + localeManager.getClass().getSimpleName());
    }

    private void initLocales() {
        availableLocales = new ArrayList<>();
        String availableLoc = context.getInitParameter(Constants.LOCALE_AVAILABLE);
        String defaultLoc = context.getInitParameter(Constants.LOCALE_DEFAULT);

        defaultLocale = new Locale(defaultLoc);

        StringTokenizer token = new StringTokenizer(availableLoc, ",");
        while (token.hasMoreTokens()) {
            availableLocales.add(new Locale(token.nextToken().trim()));
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        LocaleRequestWrapper reqWrapped = null;

        Locale locale = getRightLocale(request);
        localeManager.setLocale(locale, request, response);

        reqWrapped = new LocaleRequestWrapper(request, locale);
        chain.doFilter(reqWrapped, response);

        return;

    }


    private Locale getRightLocale(HttpServletRequest request) {
        Locale locale = null;

        // request locale
        String httpMethod = request.getMethod();
        String reqLocale = request.getParameter("lang");
        if (httpMethod.equalsIgnoreCase("get") && reqLocale != null) {
            locale = new Locale(reqLocale);
            if (availableLocales.contains(locale))
                return locale;
        }
        // session or cookie locale
        locale = localeManager.getLocale(request);
        if (locale != null)
            return locale;
        // accept locale
        Enumeration<Locale> reqLocales = request.getLocales();
        while (reqLocales.hasMoreElements()) {
            Locale reqLocTemp = reqLocales.nextElement();
            for (Locale avaLocTemp : availableLocales) {
                if (reqLocTemp.getLanguage().equals(avaLocTemp.getLanguage())) {
                    locale = avaLocTemp;
                    return locale;
                }
            }
        }
        // last chance
        return defaultLocale;
    }

    @Override
    public void destroy() {
    }
}