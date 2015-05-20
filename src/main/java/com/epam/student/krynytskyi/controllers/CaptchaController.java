package com.epam.student.krynytskyi.controllers;


import com.epam.student.krynytskyi.beans.captcha.CaptchaBean;
import com.epam.student.krynytskyi.provider.CaptchaProvider;
import com.github.cage.Cage;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CaptchaController extends HttpServlet {
    private static final Logger log = Logger.getLogger(CaptchaController.class);
    private static final long serialVersionUID = 1L;
    private CaptchaProvider captchaProvider;

    @Override
    public void init() throws ServletException {
        captchaProvider = (CaptchaProvider) getServletContext().getAttribute("captchaProvider");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        writeCaptchaToStream(req, resp);
    }

    private void writeCaptchaToStream(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Cage cage = new Cage();
        ServletOutputStream outputStream = resp.getOutputStream();
        CaptchaBean captcha = captchaProvider.getCaptcha(req);
        cage.draw(captcha.getCaptchaValue(), outputStream);
        outputStream.close();
    }
}
