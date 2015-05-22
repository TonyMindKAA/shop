package com.epam.student.krynytskyi.filter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.Vector;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class LocaleFilterParameterizedTest {

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

    String avaliableLocales = "en,ru";

    Vector<Locale> locales;
    Locale first;
    Locale second;
    Locale def;
    String requestLang;
    Locale sessionLang;

    Filter localeFilter;

    public LocaleFilterParameterizedTest(String requestLang, Locale sessionLang, Locale first, Locale second,
                                         Locale def) {
        this.first = first;
        this.second = second;
        this.def = def;
        this.requestLang = requestLang;
        this.sessionLang = sessionLang;
    }

    @Parameters
    public static Collection<Object[]> data() {
        Locale ru = new Locale("ru");
        Locale en = new Locale("en");
        Locale def = new Locale("en");

        Object[][] data = new Object[][] { {null, null, ru, en, def},
                { null, null, en, ru, def },
                { ru.getLanguage(), null, en, ru, def, },
                { "unknown", null, ru, en, def},
                {null, null, null, null, def},
                {"ru", null, new Locale("zh"), new Locale("ja"), def },
                {"zh", null, new Locale("zh"), new Locale("ja"), def},
                {null, ru, new Locale("zh"), new Locale("ja"), def},
                {"lb", ru, new Locale("zh"), new Locale("ja"), def},
                {"lb", null, new Locale("zh"), new Locale("ja"), def}};
        return Arrays.asList(data);
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        locales = new Vector<>();

        doReturn(context).when(config).getServletContext();
        doReturn("en").when(context).getInitParameter(Constants.LOCALE_DEFAULT);
        doReturn(avaliableLocales).when(context).getInitParameter(
                Constants.LOCALE_AVAILABLE);
        doReturn("31536000").when(context).getInitParameter(
                Constants.LOCALE_LIFETIME);
        doReturn(Constants.LOCALE_MODE_SESSION).when(context).getInitParameter(
                Constants.LOCALE_MODE);

        doReturn(session).when(request).getSession();
        doReturn("get").when(request).getMethod();
        doReturn(dispatcher).when(request).getRequestDispatcher(anyString());
        doReturn(locales.elements()).when(request).getLocales();

        doReturn(requestLang).when(request).getParameter("lang");
        doReturn(sessionLang).when(session).getAttribute("lang");

        localeFilter = new LocaleFilter();
        if(first != null && second != null){
            locales.add(first);
            locales.add(second);
        }
        doReturn(first).when(request).getLocale();
    }

    @After
    public void tearDown() throws Exception {
        reset(request, response, session, context, dispatcher, config);
        localeFilter = null;
        locales = null;
    }

    @Test
    public void localeTest() throws ServletException, IOException {
        class RequestLocale extends ArgumentMatcher<HttpServletRequest> {
            @Override
            public boolean matches(Object request) {
                Locale expected = null;

                if (requestLang != null
                        && avaliableLocales.contains(requestLang))
                    expected = new Locale(requestLang);
                else if (sessionLang != null)
                    expected = sessionLang;
                else if (first != null
                        && avaliableLocales.contains(first.getLanguage()))
                    expected = first;
                else if (second != null
                        && avaliableLocales.contains(second.getLanguage()))
                    expected = second;
                else
                    expected = def;

                return ((HttpServletRequest) request).getLocale().equals(
                        expected);
            }
        }

        localeFilter.init(config);
        localeFilter.doFilter(request, response, chain);
        verify(chain).doFilter(argThat(new RequestLocale()),
                notNull(ServletResponse.class));
    }

}