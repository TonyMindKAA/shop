package com.epam.student.krynytskyi.filter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.Vector;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.*;

public class LocaleFilterGeneralTest {

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    ServletContext context;
    @Mock
    FilterConfig config;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    FilterChain chain;

    Vector<Locale> locales;
    Locale ru;
    Locale en;
    Locale def;

    Filter localeFilter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        locales = new Vector<>();
        ru = new Locale("ru");
        en = new Locale("en");
        def = new Locale("en");

        doReturn(context).when(config).getServletContext();
        doReturn(context).when(request).getServletContext();
        doReturn("en").when(context).getInitParameter(Constants.LOCALE_DEFAULT);
        doReturn("en,ru").when(context).getInitParameter(Constants.LOCALE_AVAILABLE);
        doReturn("31536000").when(context).getInitParameter(Constants.LOCALE_LIFETIME);
        doReturn(Constants.LOCALE_MODE_SESSION).when(context).getInitParameter(Constants.LOCALE_MODE);

        doReturn(session).when(request).getSession();
        doReturn("get").when(request).getMethod();
        doReturn(dispatcher).when(request).getRequestDispatcher(anyString());
        doReturn(locales.elements()).when(request).getLocales();

        localeFilter = new LocaleFilter();
    }

    @After
    public void tearDown() throws Exception {
        reset(request, response, session, context, dispatcher, config);
        localeFilter = null;
        locales = null;
        en = null;
        en = null;
        ru = null;
    }

    @Test
    public void requestPostLocaleTest() throws ServletException, IOException {
        locales.add(ru);
        locales.add(en);
        doReturn(ru).when(request).getLocale();
        doReturn("en").when(request).getParameter("lang");
        doReturn("post").when(request).getMethod();

        localeFilter.init(config);
        localeFilter.doFilter(request, response, chain);
        verify(session).setAttribute("lang", ru);
    }

    @Test
    public void cookieModeTest() throws ServletException, IOException {
        class RequestLocale extends ArgumentMatcher<HttpServletRequest> {
            @Override
            public boolean matches(Object request) {
                return ((HttpServletRequest) request).getLocale().equals(ru);
            }
        }

        locales.add(ru);
        locales.add(en);
        doReturn(Constants.LOCALE_MODE_COOKIE).when(context).getInitParameter(Constants.LOCALE_MODE);
        doReturn("/").when(context).getContextPath();

        Cookie cookie = new Cookie("lang", ru.getLanguage());
        doReturn(new Cookie[]{cookie}).when(request).getCookies();
        doReturn(ru).when(request).getLocale();

        localeFilter.init(config);
        localeFilter.doFilter(request, response, chain);
        verify(chain).doFilter(argThat(new RequestLocale()), notNull(ServletResponse.class));
    }

    @Test
    public void localeModeNullTest() throws ServletException, IOException{
        doReturn(null).when(context).getInitParameter(Constants.LOCALE_MODE);
        locales.add(ru);
        locales.add(en);
        doReturn(ru).when(request).getLocale();
        doReturn("en").when(request).getParameter("lang");

        localeFilter.init(config);
        localeFilter.doFilter(request, response, chain);
        verify(session).setAttribute("lang", en);
    }

    @Test(expected=IllegalArgumentException.class)
    public void localeModeWrongTest() throws ServletException, IOException{
        doReturn("lalala").when(context).getInitParameter(Constants.LOCALE_MODE);
        locales.add(ru);
        locales.add(en);
        doReturn(ru).when(request).getLocale();
        doReturn("en").when(request).getParameter("lang");

        localeFilter.init(config);
        localeFilter.doFilter(request, response, chain);
        verify(session).setAttribute("lang", en);
    }
}
